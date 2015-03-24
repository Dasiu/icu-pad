package com.icupad.controller;

import com.icupad.domain.Movie;
import com.icupad.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieControllerImpl extends AbstractBaseController<Movie> implements MovieController {
    private MovieService movieService;

    @Autowired
    public MovieControllerImpl(MovieService movieService) {
        super(movieService);

        this.movieService = movieService;
    }
}
