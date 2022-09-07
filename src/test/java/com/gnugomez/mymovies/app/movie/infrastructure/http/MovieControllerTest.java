package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificDataId;
import com.gnugomez.mymovies.app.movie.infrastructure.persistence.MovieUserSpecificDataRepository;
import com.gnugomez.mymovies.app.movie.infrastructure.providers.MoviesDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MovieUserSpecificDataRepository movieUserSpecificDataRepository;

    @MockBean
    MoviesDataProvider moviesDataProvider;

    String username = "user";
    String password = "user";

    @BeforeEach
    void setUp() {
        when(moviesDataProvider.getPopularMovies(any())).thenReturn(Mono.just(new HashMap<>()));
        when(moviesDataProvider.getTopRatedMovies(any())).thenReturn(Mono.just(new HashMap<>()));
        when(moviesDataProvider.getMovieDetails(anyLong(), any())).thenReturn(Mono.just(new HashMap<>()));
        when(moviesDataProvider.getMovieCredits(anyLong())).thenReturn(Mono.just(new HashMap<>()));
        when(moviesDataProvider.getMovieImages(anyLong())).thenReturn(Mono.just(new HashMap<>()));
        when(moviesDataProvider.getMovieCredits(anyLong())).thenReturn(Mono.just(new HashMap<>()));
        when(moviesDataProvider.getMovieRecommendations(anyLong(), any())).thenReturn(Mono.just(new HashMap<>()));
        when(moviesDataProvider.getMovieSimilarMovies(anyLong(), any())).thenReturn(Mono.just(new HashMap<>()));
    }

    @Test
    void popular_Ok() throws Exception {
        mockMvc.perform(get("/movies/popular").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void topRated_Ok() throws Exception {
        mockMvc.perform(get("/movies/top_rated").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void movieDetails_Ok() throws Exception {
        mockMvc.perform(get("/movies/1").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void movieCredits_Ok() throws Exception {
        mockMvc.perform(get("/movies/1/credits").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void movieImages_Ok() throws Exception {
        mockMvc.perform(get("/movies/1/images").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void movieKeywords_Ok() throws Exception {
        mockMvc.perform(get("/movies/1/keywords").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void movieRecommendations_Ok() throws Exception {
        mockMvc.perform(get("/movies/1/recommendations").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    void movieSimilar_Ok() throws Exception {
        mockMvc.perform(get("/movies/1/similar").with(httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    // Update integration test with body favourite true and retrieves the object from the repository to check if have changed
    @Test
    void updateMovie_Ok() throws Exception {
        MovieUserSpecificData movieUserSpecificData = new MovieUserSpecificData(null, true, 3, "manolo manda");

        mockMvc.perform(patch("/movies/1")
                        .with(httpBasic(username, password))
                        .content(writeAsJsonString(movieUserSpecificData))
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        Optional<MovieUserSpecificData> movieUserSpecificDataFromRepository = movieUserSpecificDataRepository.findById(new MovieUserSpecificDataId(1L,1L));

        Assert.isTrue(movieUserSpecificDataFromRepository.isPresent(), "The movieUserSpecificData should be present in the repository");
        Assert.isTrue(movieUserSpecificDataFromRepository.get().getFavorite(), "The movie should be favourite");
        Assert.isTrue(movieUserSpecificDataFromRepository.get().getRating() == 3, "The movie should have a rating of 3");
        Assert.isTrue(movieUserSpecificDataFromRepository.get().getNotes().equals("manolo manda"), "The movie should have the notes 'manolo manda'");
    }

    public static String writeAsJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}