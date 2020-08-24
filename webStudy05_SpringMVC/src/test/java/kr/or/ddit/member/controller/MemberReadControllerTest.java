package kr.or.ddit.member.controller;

import static org.junit.Assert.fail;

import javax.inject.Inject;
import javax.swing.text.View;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.util.ViewMatcher;

import kr.or.ddit.WebAppTestContext;

@RunWith(SpringRunner.class)
@WebAppTestContext
public class MemberReadControllerTest {
	@Inject
	WebApplicationContext context;
	MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/member/memberList.do").param("page", "2")   
													.param("serachType","address")
													.param("searchWord","대전")
				).andExpect(status().isOk())
				 .andExpect(model().attributeExists("pagingVO"))
				 .andExpect(view().name("member/memberList"))
				 .andDo(log()) // 로그 사용 
				 .andReturn(); //종료
		
	}

	@Test
	public void testView() throws Exception {
		mockMvc.perform(get("/member/memberView.do"))
		.andExpect(status().isBadRequest())
		.andDo(log())
		.andReturn();
	
	}

}
