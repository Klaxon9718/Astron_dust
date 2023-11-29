package com.example.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/checkUID")
    public ResponseEntity<Boolean> checkUID(@RequestParam("UID") String UID) {
        boolean isExist = memberService.checkUID(UID);
        return new ResponseEntity<>(isExist, HttpStatus.OK);
    }
    
    // 값을 받아서 signUP함수로 전달
    @PostMapping("/signup")
    public String signUp(@RequestParam("UID") String UID, @RequestParam("password") String password, @RequestParam("checkPassword") String checkPassword) {
    	memberService.signUp(UID, password, checkPassword);
        return "redirect:/login";
    }
}

