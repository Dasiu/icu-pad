package com.icupad.config;

import com.icupad.domain.Movie;
import com.icupad.service.MovieService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class Seed {
    @Autowired
    private MovieService movieService;

    private static final Faker faker = new Faker();

    @PostConstruct
    public void seed() {
        createMovie(10);
    }

    public void createMovie(Integer num) {
        for (int i = 0; i < num; i++) {
            movieService.save(generateMovie());
        }
    }

    private Movie generateMovie() {
        Movie movie = new Movie();
        movie.setTitle(faker.sentence(1));

        return movie;
    }
}
