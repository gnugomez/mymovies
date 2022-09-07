package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.application.MovieFinder;
import com.gnugomez.mymovies.app.movie.application.MovieUpdater;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.infrastructure.providers.MoviesDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    MoviesDataRepository moviesDataRepository;

    MovieFinder movieFinder;

    MovieUpdater movieUpdater;

    @GetMapping("/popular")
    public Map popular(@PathParam("language") Optional<String> language) {
        return moviesDataRepository.getPopularMovies(language);
    }

    @GetMapping("/top_rated")
    public Map topRated(@PathParam("language") Optional<String> language) {
        return moviesDataRepository.getTopRatedMovies(language);
    }

    @GetMapping("/{movieId}")
    public Map movieDetails(@PathVariable("movieId") Long movieId, @PathParam("language") Optional<String> language) {
        return movieFinder.find(movieId, language);
    }

    @GetMapping("/{movieId}/credits")
    public Map movieCredits(@PathVariable("movieId") Long movieId) {
        return moviesDataRepository.getMovieCredits(movieId);
    }

    @GetMapping("/{movieId}/images")
    public Map movieImages(@PathVariable("movieId") Long movieId) {
        return moviesDataRepository.getMovieImages(movieId);
    }

    @GetMapping("/{movieId}/keywords")
    public Map movieKeywords(@PathVariable("movieId") Long movieId) {
        return moviesDataRepository.getMovieKeywords(movieId);
    }

    @GetMapping("/{movieId}/recommendations")
    public Map movieRecommendations(@PathVariable("movieId") Long movieId, @PathParam("language") Optional<String> language) {
        return moviesDataRepository.getMovieRecommendations(movieId, language);
    }

    @GetMapping("/{movieId}/similar")
    public Map movieSimilar(@PathVariable("movieId") Long movieId, @PathParam("language") Optional<String> language) {
        return moviesDataRepository.getMovieSimilarMovies(movieId, language);
    }

    @PatchMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovie(@PathVariable("movieId") Long movieId, @Valid @RequestBody MovieUserSpecificData movieUserSpecificData) {

        movieUpdater.update(
                movieId,
                movieUserSpecificData
        );

    }

}
