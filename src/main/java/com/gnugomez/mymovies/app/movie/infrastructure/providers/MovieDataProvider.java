package com.gnugomez.mymovies.app.movie.infrastructure.providers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Optional;

@Service
@Getter
public class MovieDataProvider {
    private final WebClient client;
    private final String defaultLanguage = "en-US";

    public MovieDataProvider(@Value("${tmdb.readToken}") String readToken) {
        this.client = WebClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("Authorization", "Bearer " + readToken)
                .defaultHeader("Content-Type", "application/json;charset=utf-8")
                .build();
    }

    public Mono<HashMap<String, Object>> getPopularMovies(Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/popular")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get a list of oficial movie genres
    public Mono<HashMap<String, Object>> getMovieGenres(Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/genre/movie/list")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get the top rated movies on TMDB
    public Mono<HashMap<String, Object>> getTopRatedMovies(Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/top_rated")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get specific movie details
    public Mono<HashMap<String, Object>> getMovieDetails(Long movieId, Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get specific movie credits
    public Mono<HashMap<String, Object>> getMovieCredits(Long movieId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/credits")
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get specific movie images
    public Mono<HashMap<String, Object>> getMovieImages(Long movieId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/images")
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get specific movie keywords
    public Mono<HashMap<String, Object>> getMovieKeywords(Long movieId) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/keywords")
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get specific movie recommendations
    public Mono<HashMap<String, Object>> getMovieRecommendations(Long movieId, Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/recommendations")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    // Get Specific movie similar movies
    public Mono<HashMap<String, Object>> getMovieSimilarMovies(Long movieId, Optional<String> language) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/similar")
                        .queryParam("language", language.orElse(this.defaultLanguage))
                        .build(movieId)
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

}
