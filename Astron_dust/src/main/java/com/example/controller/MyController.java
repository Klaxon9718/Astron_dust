package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.member.Member;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
    public String main(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String UID = (String) session.getAttribute("UID"); // 세션에서 UID를 가져옵니다.

        // 세션 없을 경우
        if (UID == null) { 
            return "redirect:/login";
        }

        model.addAttribute("UID", UID); // UID를 모델에 추가합니다. 이렇게 하면 뷰에서 UID를 사용할 수 있습니다.
        return "/main"; // main 뷰를 반환합니다.
    }

    
    @GetMapping("/account")
    public String account(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String UID = (String) session.getAttribute("UID"); // 세션에서 UID를 가져옵니다.

        // 세션 없을 경우
        if (UID == null) { 
            return "redirect:/login";
        }

        model.addAttribute("UID", UID); // UID를 모델에 추가합니다. 이렇게 하면 뷰에서 UID를 사용할 수 있습니다.
        return "/account"; // main 뷰를 반환합니다.
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
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 삭제
        }
        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }

 
}
