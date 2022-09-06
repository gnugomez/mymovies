package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.infrastructure.TMDBDataSource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class movieController {
    TMDBDataSource tmdbDataSource;

    @GetMapping("/popular")
    public Map popular(@PathParam("language") Optional<String> language) {
        return tmdbDataSource.getPopularMovies(language);
    }

    @GetMapping("/top_rated")
    public Map topRated(@PathParam("language") Optional<String> language) {
        return tmdbDataSource.getTopRatedMovies(language);
    }

    @GetMapping("/{movieId}")
    public Map movieDetails(@PathVariable("movieId") int movieId, @PathParam("language") Optional<String> language) {
        return tmdbDataSource.getMovieDetails(movieId, language);
    }

    @GetMapping("/{movieId}/credits")
    public Map movieCredits(@PathVariable("movieId") int movieId) {
        return tmdbDataSource.getMovieCredits(movieId);
    }

    @GetMapping("/{movieId}/images")
    public Map movieImages(@PathVariable("movieId") int movieId) {
        return tmdbDataSource.getMovieImages(movieId);
    }

    @GetMapping("/{movieId}/keywords")
    public Map movieKeywords(@PathVariable("movieId") int movieId) {
        return tmdbDataSource.getMovieKeywords(movieId);
    }

    @GetMapping("/{movieId}/recommendations")
    public Map movieRecommendations(@PathVariable("movieId") int movieId, @PathParam("language") Optional<String> language) {
        return tmdbDataSource.getMovieRecommendations(movieId, language);
    }

    @GetMapping("/{movieId}/similar")
    public Map movieSimilar(@PathVariable("movieId") int movieId, @PathParam("language") Optional<String> language) {
        return tmdbDataSource.getMovieSimilarMovies(movieId, language);
    }

}
