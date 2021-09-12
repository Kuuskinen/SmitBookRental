package com.example.smitbookrental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class SmitBookRentalApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testingTest() throws Exception {
		mockMvc.perform(get("/allBooks")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));

		// /new_book
//		mockMvc.perform(get("/allBooks")).andDo(print())
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(jsonPath("$[0].name").value("Cosmos"));
	}

	/*
	@Test
	void addBookTest() throws Exception {
		mockMvc.perform(post("/new_book")).andDo(print())
				.andExpect(status().isOk());
	}
	 */
}
