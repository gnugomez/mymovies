package com.gnugomez.mymovies.app.movie.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;

@Service
public class MoviesDataRepository {
    WebClient client;
    String defaultLanguage = "en-US";

    public MoviesDataRepository() {
        this.client = WebClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNWI3ZTg5ODc0ZWQ4NTliYzIxNmQ4YTY0ZDEzNDFhOCIsInN1YiI6IjVmZTExNjE0MmIyMTA4MDAzZmU3OTZlNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VDBndA0b162M9Z4_7gPWs_8FLdBfJq7jkDMGTFJFmH8")
                .defaultHeader("Content-Type", "application/json;charset=utf-8")
                .build();
    }

    public Map getPopularMovies(Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/popular")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    // Get a list of oficial movie genres
    public Map getMovieGenres(Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/genre/movie/list")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    // Get the top rated movies on TMDB
    public Map getTopRatedMovies(Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/top_rated")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build()
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    // Get specific movie details
    public Map getMovieDetails(int movieId, Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    // Get specific movie credits
    public Map getMovieCredits(int movieId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/credits")
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
    
    // Get specific movie images
    public Map getMovieImages(int movieId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/images")
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
    
    // Get specific movie keywords
    public Map getMovieKeywords(int movieId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/keywords")
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
    
    // Get specific movie recommendations
    public Map getMovieRecommendations(int movieId, Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/recommendations")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
    
    // Get Specific movie similar movies
    public Map getMovieSimilarMovies(int movieId, Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/similar")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
    
}
