package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.infrastructure.providers.MovieDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MovieGenereControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieDataProvider movieDataProvider;

    String username = "user";
    String password = "user";

    @BeforeEach
    void setUp() {
        when(movieDataProvider.getMovieGenres(any())).thenReturn(Mono.just(new HashMap<>()));
    }

    @Test
    void popular() throws Exception {
        mockMvc.perform(get("/genere/movie/list").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }
}