package com.gnugomez.mymovies.app.movie.application;

import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificDataId;
import com.gnugomez.mymovies.app.movie.infrastructure.persistence.MovieUserSpecificDataRepository;
import com.gnugomez.mymovies.app.user.infrastructure.auth.UsernamePasswordPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieUpdater {

    MovieUserSpecificDataRepository movieUserSpecificDataRepository;

    public void update(Long movieId, MovieUserSpecificData movieUserSpecificData) {
        UsernamePasswordPrincipal loggedUser = (UsernamePasswordPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        movieUserSpecificData.setId(new MovieUserSpecificDataId(loggedUser.getId(), movieId));

        movieUserSpecificDataRepository.save(movieUserSpecificData);
    }
}
