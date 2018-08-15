package com.ahnndroid.springbootqnamustacheexample.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.ahnndroid.springbootqnamustacheexample.SpringbootQnaMustacheExampleApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootQnaMustacheExampleApplication.class, properties = "classpath:application.properties", webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private MockHttpServletRequestBuilder mockHttpRequest;
	
	@Test
	public void test001_정상___회원가입_페이지_접속() throws Exception {
		
		this.mockHttpRequest = get("/users/signupForm");
		
		this.mockMvc.perform(this.mockHttpRequest)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("/user/signup_form"))
					.andExpect(xpath("//head/title").string("Sign-up"));
	}
}
