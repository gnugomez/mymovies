package com.gnugomez.mymovies.app.movie.application;

import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificDataId;
import com.gnugomez.mymovies.app.movie.infrastructure.persistence.MovieUserSpecificDataRepository;
import com.gnugomez.mymovies.app.movie.infrastructure.providers.MoviesDataProvider;
import com.gnugomez.mymovies.app.user.domain.auth.UsernamePasswordPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieFinder {

    MovieUserSpecificDataRepository movieUserSpecificDataRepository;

    MoviesDataProvider moviesDataProvider;

    public Mono<HashMap<String, Object>> find(Long movieId, Optional<String> language) {
        UsernamePasswordPrincipal loggedUser = (UsernamePasswordPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Mono<HashMap<String, Object>> movie = moviesDataProvider.getMovieDetails(movieId, language);

        Mono<MovieUserSpecificData> movieUserSpecificData = Mono.fromCallable(() -> movieUserSpecificDataRepository
                .findById(new MovieUserSpecificDataId(loggedUser.getId(), movieId)).orElse(new MovieUserSpecificData())
        );

        return Mono.zip(movie, movieUserSpecificData)
                .map(tuple -> {
                    HashMap<String, Object> m = tuple.getT1();
                    MovieUserSpecificData specificData = tuple.getT2();
                    m.put("rating", specificData.getRating());
                    m.put("notes", specificData.getNotes());
                    m.put("favorite", specificData.getFavorite() != null ? specificData.getFavorite() : false);
                    return m;
                });
    }
}
