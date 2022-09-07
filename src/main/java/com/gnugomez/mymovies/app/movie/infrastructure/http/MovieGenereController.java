package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.infrastructure.providers.MovieDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/genere/movie")
public class MovieGenereController {
    MovieDataProvider movieDataProvider;

    @GetMapping("/list")
    public Mono<HashMap<String, Object>> popular(@PathParam("language") Optional<String> language) {
        return movieDataProvider.getMovieGenres(language);
    }

}
