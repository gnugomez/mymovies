package com.gnugomez.mymovies.app.movie.infrastructure.persistence;

import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificData;
import com.gnugomez.mymovies.app.movie.domain.MovieUserSpecificDataId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieUserSpecificDataRepository extends JpaRepository<MovieUserSpecificData, MovieUserSpecificDataId> {
}
