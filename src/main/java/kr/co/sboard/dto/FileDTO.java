package kr.co.sboard.dto;

import kr.co.sboard.controller.entity.FileEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {


    private  int fno;
    private  int ano;
    private  String oriName;
    private  String newName;
    private  int download;
    private LocalDateTime rdate;

    public FileEntity toEntity() {

        return FileEntity.builder()
                .fno(fno)
                .ano(ano)
                .oriName(oriName)
                .newName(newName)
                .download(download)
                .rdate(rdate)
                .build();

    }


}
