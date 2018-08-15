package com.ahnndroid.springbootqnamustacheexample.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

	/**
	 * 회원가입 페이지 접속
	 * @return
	 */
	@GetMapping("/signupForm")
	public String signupForm() {
		return "/user/signup_form";
	}
}
