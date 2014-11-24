package com.icupad.service;

import com.icupad.domain.Movie;
import com.icupad.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public boolean exists(Long id) {
        return movieRepository.exists(id);
    }

    public Movie findOne(Long id) {
        return movieRepository.findOne(id);
    }

    public long count() {
        return movieRepository.count();
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public void delete(Long id) {
        movieRepository.delete(id);
    }
}
