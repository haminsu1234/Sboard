package kr.co.sboard.controller.article;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.controller.entity.ArticleEntity;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.service.ArticelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class ArticleController {

    @Autowired
    private ArticelService service;
    @GetMapping("/article/list")
    public String list(Model model, PageRequestDTO pageRequestDTO){
        PageResponseDTO articles = service.selectArticles2(pageRequestDTO);

        log.info("pageResponseDTO pg : "+articles.getPg());
        log.info("pageResponseDTO size : "+articles.getSize());
        log.info("pageResponseDTO total : "+articles.getTotal());
        log.info("pageResponseDTO start : "+articles.getStart());
        log.info("pageResponseDTO end : "+articles.getEnd());
        log.info("pageResponseDTO prev : "+articles.getDtoList());
        log.info("pageResponseDTO next : "+articles.getDtoList());

        model.addAttribute("articles",articles);
        return "/article/list";
    }
    @GetMapping("/article/write")
    public String write(@ModelAttribute  PageRequestDTO pageRequestDTO){
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest request, ArticleDTO dto){

        dto.setRegip(request.getRemoteAddr());

        service.insertArticle(dto);
        return "redirect:/article/list?cate="+dto.getCate();
    }

    @GetMapping("/article/view")
    public String view( int no, Model model){

       ArticleEntity entity =service.selectArticle(no);


       // model.addAttribute("entity",entity);


        return "/article/view";


    }

}
