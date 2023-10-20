package kr.co.sboard.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {


    @GetMapping(value = {"/","/index"})
    public String index(Authentication authentication){

        if(authentication.isAuthenticated()){
            return "forword:/article/list";
        }else {
            return "forword:/user/login";
        }

    }





}
