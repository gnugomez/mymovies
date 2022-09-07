package com.gnugomez.mymovies.app.movie.infrastructure.http;

import com.gnugomez.mymovies.app.movie.infrastructure.providers.MoviesDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/genere/movie")
public class MovieGenereController {
    MoviesDataRepository moviesDataRepository;

    @GetMapping("/list")
    public Map popular(@PathParam("language") Optional<String> language) {
        return moviesDataRepository.getMovieGenres(language);
    }

}
