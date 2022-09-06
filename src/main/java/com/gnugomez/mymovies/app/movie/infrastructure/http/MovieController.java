package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.infrastructure.MoviesDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    MoviesDataRepository moviesDataRepository;

    @GetMapping("/popular")
    public Map popular(@PathParam("language") Optional<String> language) {
        return moviesDataRepository.getPopularMovies(language);
    }

    @GetMapping("/top_rated")
    public Map topRated(@PathParam("language") Optional<String> language) {
        return moviesDataRepository.getTopRatedMovies(language);
    }

    @GetMapping("/{movieId}")
    public Map movieDetails(@PathVariable("movieId") int movieId, @PathParam("language") Optional<String> language) {
        return moviesDataRepository.getMovieDetails(movieId, language);
    }

    @GetMapping("/{movieId}/credits")
    public Map movieCredits(@PathVariable("movieId") int movieId) {
        return moviesDataRepository.getMovieCredits(movieId);
    }

    @GetMapping("/{movieId}/images")
    public Map movieImages(@PathVariable("movieId") int movieId) {
        return moviesDataRepository.getMovieImages(movieId);
    }

    @GetMapping("/{movieId}/keywords")
    public Map movieKeywords(@PathVariable("movieId") int movieId) {
        return moviesDataRepository.getMovieKeywords(movieId);
    }

    @GetMapping("/{movieId}/recommendations")
    public Map movieRecommendations(@PathVariable("movieId") int movieId, @PathParam("language") Optional<String> language) {
        return moviesDataRepository.getMovieRecommendations(movieId, language);
    }

    @GetMapping("/{movieId}/similar")
    public Map movieSimilar(@PathVariable("movieId") int movieId, @PathParam("language") Optional<String> language) {
        return moviesDataRepository.getMovieSimilarMovies(movieId, language);
    }

}
