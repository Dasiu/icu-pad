package com.icupad.controller;

import com.icupad.domain.Movie;
import com.icupad.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Movie> index() {
        return movieService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Map<String, Long> create(@RequestBody Movie movie) {
        movieService.save(movie);
        Map<String, Long> response = new HashMap<>();
        response.put("id", movie.getId());

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie show(@PathVariable Integer id) {
        return movieService.findOne(id.longValue());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Movie movie) {
        movie.setId(id.longValue());
        movieService.save(movie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Integer id) {
        movieService.delete(id.longValue());
    }
}
