package kr.co.sboard.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.controller.entity.TermsEntity;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.service.TermsService;
import kr.co.sboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private TermsService service;
    @Autowired
    private UserService service2;
    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/user/terms")
    public String terms(Model model){
        TermsEntity terms  = service.selectTerms();
        model.addAttribute(terms);
        return "/user/terms";
    }

    @GetMapping("/user/register")
    public String register(){
        return "/user/register";
    }
    @PostMapping("/user/register")
    public String register(HttpServletRequest request, UserDTO dto){

        dto.setRegip(request.getRemoteAddr());

        service2.inputUser(dto);
        return "redirect:/user/login";
    }
}
