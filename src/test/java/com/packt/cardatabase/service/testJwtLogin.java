package com.packt.cardatabase.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class testJwtLogin {
  @Autowired private MockMvc mockMvc;

  @Test
  public void testAutentication() throws Exception {
    this.mockMvc
        .perform(post("/login").content("{\"username\":\"user\",\"password\":\"password\"}"))
        .andDo(print())
        .andExpect(status().isOk());
    this.mockMvc
        .perform(post("/login").content("{\"username\":\"user\",\"password\":\"wrong_password\"}"))
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }
}
