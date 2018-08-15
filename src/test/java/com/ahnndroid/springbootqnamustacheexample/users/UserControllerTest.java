package com.ahnndroid.springbootqnamustacheexample.users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
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
import com.ahnndroid.springbootqnamustacheexample.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootQnaMustacheExampleApplication.class, properties = "classpath:application.properties", webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	
	private static final int USER_COUNT = 3;
	
	@Autowired
	private MockMvc mockMvc;
	
	private MockHttpServletRequestBuilder mockHttpRequest;
	
	private static List<User> testUsers = new ArrayList<User>();
	
	@BeforeClass
	public static void setup() {
		testUsers = initTestUsers();
	}
	
	@Test
	public void test001_정상___회원가입_페이지_접속() throws Exception {
		
		this.mockHttpRequest = get("/users/signupForm");
		
		this.mockMvc.perform(this.mockHttpRequest)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("/user/signup_form"))
					.andExpect(xpath("//head/title").string("Sign-up"));
	}
	
	@Test
	public void test002_정상___회원가입_처리() throws Exception {
		
		List<User> tmpUsers = UserControllerTest.testUsers;
		
		for (int i = 0; i < tmpUsers.size(); i++) {
			User tmpUser = tmpUsers.get(i);
			
			this.mockHttpRequest = post("/users/signup")
									.param("userId", tmpUser.getUserId())
									.param("password", tmpUser.getPassword())
									.param("name", tmpUser.getName())
									.param("email", tmpUser.getEmail());
			
			this.mockMvc.perform(this.mockHttpRequest)
						.andDo(print())
						.andExpect(status().is3xxRedirection())
						.andExpect(redirectedUrl("/users"));
		}
		
	}
	
	@Test
	public void test003_정상___가입_사용자_리스트_조회() throws Exception {
		
		this.mockHttpRequest = get("/users");
		
		Map<String, Object> modelMap = this.mockMvc.perform(this.mockHttpRequest)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("/user/list"))
					.andExpect(model().attributeExists("users"))
					.andReturn().getModelAndView().getModelMap();
		
		@SuppressWarnings("unchecked")
		List<User> modelUsers = (List<User>) modelMap.get("users");
		assertThat(modelUsers.size(), equalTo(UserControllerTest.USER_COUNT));
	}
	
		
	//////////////////////////////////////////////////////////////////////////////////
	///////////////       [BEGIN] 테스트 유틸 내부 메소드 목록         ///////////////
	/**
	 * 테스트용 사용자 정보 리스트 생성을 위한 유틸 메소드
	 */
	private static List<User> initTestUsers() {
		List<User> tmpUsers = new ArrayList<User>();
		User tmpUser;
		
		for (int i = 0; i < USER_COUNT; i++) {
			tmpUser = User.builder()
							.userId("" + i)
							.password("" + i)
							.name("" + i)
							.email("" + i + "@foo.bar")
							.build();
			
			tmpUsers.add(tmpUser);
		}
		
		return tmpUsers;
	}
	///////////////     [FINISHED] 테스트 유틸 내부 메소드 목록        ///////////////
	//////////////////////////////////////////////////////////////////////////////////
}
