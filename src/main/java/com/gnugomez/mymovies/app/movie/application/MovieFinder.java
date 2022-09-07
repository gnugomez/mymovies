package com.gnugomez.mymovies.app.movie.application;

import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificDataId;
import com.gnugomez.mymovies.app.movie.infrastructure.persistence.MovieUserSpecificDataRepository;
import com.gnugomez.mymovies.app.movie.infrastructure.providers.MoviesDataRepository;
import com.gnugomez.mymovies.app.user.infrastructure.auth.UsernamePasswordPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieFinder {

    MovieUserSpecificDataRepository movieUserSpecificDataRepository;

    MoviesDataRepository moviesDataRepository;

    public Map find(Long movieId, Optional<String> language) {
        UsernamePasswordPrincipal loggedUser = (UsernamePasswordPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map movie = moviesDataRepository.getMovieDetails(movieId, language);

        MovieUserSpecificData movieUserSpecificData = movieUserSpecificDataRepository
                .findById(new MovieUserSpecificDataId(loggedUser.getId(), movieId)).orElse(new MovieUserSpecificData());

        movie.put("favorite", movieUserSpecificData.isFavorite());
        movie.put("rating", movieUserSpecificData.getRating());
        movie.put("notes", movieUserSpecificData.getNotes());

        return movie;
    }
}
