package com.gnugomez.mymovies.app.movie.application;

import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificDataId;
import com.gnugomez.mymovies.app.movie.infrastructure.persistence.MovieUserSpecificDataRepository;
import com.gnugomez.mymovies.app.user.domain.auth.UsernamePasswordPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieUpdater {

    MovieUserSpecificDataRepository newMovieUserSpecificDataRepository;

    public void update(Long movieId, MovieUserSpecificData newMovieUserSpecificData) {
        UsernamePasswordPrincipal loggedUser = (UsernamePasswordPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        MovieUserSpecificData movieUserSpecificData = newMovieUserSpecificDataRepository
                .findById(new MovieUserSpecificDataId(loggedUser.getId(), movieId)).orElse(new MovieUserSpecificData());

        movieUserSpecificData.setId(new MovieUserSpecificDataId(loggedUser.getId(), movieId));

        if (newMovieUserSpecificData.getRating() != null) {
            movieUserSpecificData.setRating(newMovieUserSpecificData.getRating());
        }
        if (newMovieUserSpecificData.getNotes() != null) {
            movieUserSpecificData.setNotes(newMovieUserSpecificData.getNotes());
        }
        if (newMovieUserSpecificData.getFavorite() != null) {
            movieUserSpecificData.setFavorite(newMovieUserSpecificData.getFavorite());
        }

        newMovieUserSpecificDataRepository.save(movieUserSpecificData);
    }
}
