package com.icupad.service;

import com.icupad.domain.Movie;
import com.icupad.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends AbstractBaseService<Movie> implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        super(movieRepository);

        this.movieRepository = movieRepository;
    }
}
