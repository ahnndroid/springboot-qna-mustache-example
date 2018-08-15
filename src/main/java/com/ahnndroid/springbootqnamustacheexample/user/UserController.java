package com.ahnndroid.springbootqnamustacheexample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	/**
	 * 사용자 정보 수정
	 * @return
	 */
	@GetMapping("/{id}/form")
	public String updateUserForm(@PathVariable Long id, Model model) {		
		model.addAttribute("user", this.userRepository.findById(id).get());		
		return "/user/update_user_form";
	}
	
	@PutMapping("/{id}")
	public String updateUser(@PathVariable Long id, User updateUser, Model Model) {
		User targetUser = this.userRepository.findById(id).get();
		
		if (targetUser.getId() == updateUser.getId()) {
			targetUser.setName(updateUser.getName());
			targetUser.setEmail(updateUser.getEmail());
			
			this.userRepository.save(targetUser);
		}
		
		return "redirect:/users";
	}
	
}
