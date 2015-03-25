package com.icupad.config;

import com.github.javafaker.Faker;
import com.icupad.domain.Movie;
import com.icupad.domain.user.Doctor;
import com.icupad.domain.user.User;
import com.icupad.service.MovieService;
import com.icupad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class Seed {
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    private static final Faker faker = new Faker();

    @PostConstruct
    public void seed() {
        createUsers();
        createMovie(10);
    }

    private void createUsers() {
        User user = new Doctor();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setEnabled(true);
        userService.save(user);
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
