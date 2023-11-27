package com.example.page.signup;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {

	@PostMapping("/getNewUser")
	public String createUser(@RequestParam String user_id, @RequestParam String password) {
	    // ID와 비밀번호를 데이터베이스에 저장하는 로직
	    // ...
	    
	    return "redirect:/";
	}

}
