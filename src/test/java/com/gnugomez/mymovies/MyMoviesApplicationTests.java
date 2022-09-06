package com.gnugomez.mymovies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

@SpringBootTest
@AutoConfigureMockMvc
class MyMoviesApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("user")
                .password("user");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("user"));
    }

}
