package com.example.userPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/checkUID")
    public ResponseEntity<Boolean> checkUID(@RequestParam("UID") String UID) {
        boolean isExist = userService.checkUID(UID);
        return new ResponseEntity<>(isExist, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String UID, @RequestParam String password, @RequestParam String checkPassword) {
        userService.signUp(UID, password, checkPassword);
        return "redirect:/login";
    }
}
