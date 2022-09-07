package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.application.MovieFinder;
import com.gnugomez.mymovies.app.movie.application.MovieUpdater;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.infrastructure.providers.MovieDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    MovieDataProvider movieDataProvider;

    MovieFinder movieFinder;

    MovieUpdater movieUpdater;

    @GetMapping("/popular")
    public Mono<HashMap<String, Object>> popular(@PathParam("language") Optional<String> language) {
        return movieDataProvider.getPopularMovies(language);
    }

    @GetMapping("/top_rated")
    public Mono<HashMap<String, Object>> topRated(@PathParam("language") Optional<String> language) {
        return movieDataProvider.getTopRatedMovies(language);
    }

    @GetMapping("/{movieId}")
    public Mono<HashMap<String, Object>> movieDetails(@PathVariable("movieId") Long movieId, @PathParam("language") Optional<String> language) {
        return movieFinder.find(movieId, language);
    }

    @GetMapping("/{movieId}/credits")
    public Mono<HashMap<String, Object>> movieCredits(@PathVariable("movieId") Long movieId) {
        return movieDataProvider.getMovieCredits(movieId);
    }

    @GetMapping("/{movieId}/images")
    public Mono<HashMap<String, Object>> movieImages(@PathVariable("movieId") Long movieId) {
        return movieDataProvider.getMovieImages(movieId);
    }

    @GetMapping("/{movieId}/keywords")
    public Mono<HashMap<String, Object>> movieKeywords(@PathVariable("movieId") Long movieId) {
        return movieDataProvider.getMovieKeywords(movieId);
    }

    @GetMapping("/{movieId}/recommendations")
    public Mono<HashMap<String, Object>> movieRecommendations(@PathVariable("movieId") Long movieId, @PathParam("language") Optional<String> language) {
        return movieDataProvider.getMovieRecommendations(movieId, language);
    }

    @GetMapping("/{movieId}/similar")
    public Mono<HashMap<String, Object>> movieSimilar(@PathVariable("movieId") Long movieId, @PathParam("language") Optional<String> language) {
        return movieDataProvider.getMovieSimilarMovies(movieId, language);
    }

    @PatchMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovie(@PathVariable("movieId") Long movieId, @Valid @RequestBody MovieUserSpecificData movieUserSpecificData) {
        movieUpdater.update(movieId, movieUserSpecificData);
    }

}
