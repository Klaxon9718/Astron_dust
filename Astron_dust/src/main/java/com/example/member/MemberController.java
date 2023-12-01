package com.example.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
    // 성공 여부 반환
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestParam("UID") String UID, @RequestParam("password") String password, @RequestParam("checkPassword") String checkPassword) {
      try {
        memberService.signUp(UID, password, checkPassword);
        return new ResponseEntity<>(Map.of("message", "회원가입이 완료되었습니다."), HttpStatus.OK);
      } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
      }
    }
    
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("UID") String UID, @RequestParam("password") String password, HttpServletRequest request) {
      try {
        boolean isAuthenticated = memberService.authenticate(UID, password);
        if (isAuthenticated) {
        	// UID를 세션 생성
        	HttpSession session = request.getSession();
            session.setAttribute("UID", UID); 
            // 로그인 성공 전달
          return new ResponseEntity<>(HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
      } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
      }
    }
    
   



}

