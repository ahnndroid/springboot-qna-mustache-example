package com.ahnndroid.springbootqnamustacheexample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahnndroid.springbootqnamustacheexample.user.domain.User;
import com.ahnndroid.springbootqnamustacheexample.user.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * 회원가입 페이지 접속
	 * @return
	 */
	@GetMapping("/signupForm")
	public String signupForm() {
		return "/user/signup_form";
	}
	
	/**
	 * 회원가입 처리
	 * @return
	 */
	@PostMapping("/signup")
	public String signup(User signupUser) {
		this.userRepository.save(signupUser);
		return "redirect:/users";
	}
	
	/**
	 * 가입 사용자 리스트 조회
	 * @return
	 */
	@GetMapping("")
	public String getUsers(Model model) {
		model.addAttribute("users", this.userRepository.findAll());
		return "/user/list";
	}
	
}
