package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.infrastructure.TMDBDataSource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/genere/movie")
public class movieGenereController {
    TMDBDataSource tmdbDataSource;

    @GetMapping("/list")
    public Map popular(@PathParam("language") Optional<String> language) {
        return tmdbDataSource.getMovieGenres(language);
    }

}
