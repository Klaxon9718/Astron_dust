package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	
    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "/signup";
    }
    
    @GetMapping("/main")
    public String main() {
        return "/main";
    }
    
    @GetMapping("/account")
    public String account() {
        return "/account";
    }
    
    @GetMapping("/accountnotice")
    public String accountnotice() {
        return "/accountnotice";
    }
    
    @GetMapping("/deleteaccount")
    public String deleteaccount() {
        return "/deleteaccount";
    }
    
    @GetMapping("/goodbye")
    public String goodbye() {
        return "/goodbye";
    }
    
    @GetMapping("/makenotice")
    public String makenotice() {
        return "/makenotice";
    }
    
    @GetMapping("/nasapictures")
    public String nasapictures() {
        return "/nasapictures";
    }
    
    @GetMapping("/noticeboard")
    public String noticeboard() {
        return "/noticeboard";
    }
    
    @GetMapping("/observe")
    public String observe() {
        return "/observe";
    }
    
    @GetMapping("/onecontent")
    public String onecontent() {
        return "/onecontent";
    }
    
 
}
