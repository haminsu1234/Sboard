package kr.co.sboard.service;

import kr.co.sboard.controller.entity.ArticleEntity;
import kr.co.sboard.controller.entity.FileEntity;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class ArticelService {


    private final ArticleRepository repo;
    private final FileRepository repo2;

    private final ModelMapper modelMapper;


    public void insertArticle(ArticleDTO dto){
        ArticleEntity entity = dto.toEntity();
        repo.save(entity);
        FileDTO fileDTO=fileUpload(dto);
        if(fileDTO != null) {
            fileDTO.setAno(entity.getNo());
            insertFile(fileDTO);
            entity.setFile(repo2.countByAno(fileDTO.getAno()));
            if(entity.getCate() == null) {
                entity.setCate(dto.getCate()); //cate값이 없으면 free로
            }
            entity.setUseyn("Y"); //useyn의 기본값
            repo.save(entity);
        }
    }


    public Page<ArticleEntity> selectArticles(){//이렇게하면 거 뭐냐 댓글까지 다나옴 애초에 댓글이랑 아티클이랑 분리하면 상관없을것같긴한데 그러면 참조하는 횟수가많아져서 db에 부담가니까 이게좋나?

        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC,"no");

        return repo.findAll(pageable);
    }

    public PageResponseDTO selectArticles2(PageRequestDTO pageRequestDTO){
        //Pageable pageable = PageRequest.of(pg-1,10, Sort.Direction.DESC,"no");
        Pageable pageable=pageRequestDTO.getPageable("no");

        Page<ArticleEntity> result =repo.findByUseynAndParentAndCate("Y",0, pageRequestDTO.getCate(), pageable);

        List<ArticleDTO> dtoList=result.getContent()
                                        .stream()
                                        .map(entity -> modelMapper.map(entity, ArticleDTO.class) )
                                        .toList();
        //stream = 통로라는데 사용법을 봐야지 알듯
        //map - list에 있는 각각의 객체에대해 동일한 연산처리를 진행함

       int totalElement =(int) result.getTotalElements();


        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElement).build();
    }

    public List<FileEntity> selectFile(int no){
          List<FileEntity> file = Collections.singletonList(repo2.findById(no).get());
        return file;
    }

    public void insertFile(FileDTO dto){

        FileEntity entity = dto.toEntity();
        repo2.save(entity);
    }

    public void countAno(FileDTO dto){
        FileEntity entity = dto.toEntity();
    }

    public ArticleEntity selectArticle(int no){
       return repo.findById(no).get();
    }

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    public FileDTO fileUpload(ArticleDTO dto){
        MultipartFile mf =dto.getFname();

        if(!mf.isEmpty()){
            //파일첨부 했을시
            String path =new File(filePath).getAbsolutePath();

            String oriName =mf.getOriginalFilename(); //파일원본이름
            String ext = oriName.substring(oriName.lastIndexOf(".")); //파일 확장자
            String newName = UUID.randomUUID().toString()+ext; //uuid 로 랜덤값 변경 + 확장자

            try {
                mf.transferTo(new File(path, newName)); //파일을 경로 + 바뀐이름으로 저장함
            } catch (IOException e) {
                log.error(e.getMessage());
            }

            return FileDTO.builder()
                    .oriName(oriName)
                    .newName(newName)
                    .build(); //dto를 새로 생성해줌 + 뭐 db랑 관련된 변수명 지을때는 db 보고 생각 아니면 컬럼이랑 달라서 나중에 헷갈림

        }
        return null;
    }


}
