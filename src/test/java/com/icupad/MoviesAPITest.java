package com.icupad;

import com.icupad.controller.MovieController;
import com.icupad.domain.Movie;
import com.icupad.service.MovieService;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class})
@Transactional
public class MoviesAPITest {
    @Autowired
    private MovieController movieController;

    @Autowired
    private MovieService movieService;
    private Movie[] movies;

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(movieController);
        movies = twoSampleMovies();
    }

    @Test
    public void getMoviesShouldListAllMovies() {
        Movie[] response = when().get("/movies")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().as(Movie[].class);

        assertArrayEquals(movies, response);
    }

    @Test
    public void getMoviesIdShouldReturnMovie() {
        Movie response =
                when().
                        get("/movies/" + movies[0].getId())
                .then()
                        .statusCode(200).contentType(ContentType.JSON)
                        .extract().as(Movie.class);

        assertEquals(movies[0], response);
    }

    @Test
    public void postMoviesShouldCreateNewMovie() {
        Movie newMovie = new Movie();
        newMovie.setTitle("Inception");
        Long id =
                given().log().all().contentType(ContentType.JSON).body(newMovie)
                .when().post("/movies/")
                .then().log().all().statusCode(201).contentType(ContentType.JSON)
                .extract().<Integer>path("id").longValue();

        assertTrue(movieService.exists(id));
        Movie savedMovie = movieService.findOne(id);
        assertEquals(newMovie, savedMovie);
    }

    @Test
    public void putMoviesIdShouldUpdateMovie() {
        String newTitle = "The Dark Knight";
        Movie update = new Movie();
        update.setTitle(newTitle);

        given().contentType(ContentType.JSON).body(update)
                .when().put("/movies/" + movies[0].getId())
                .then().statusCode(204);

        assertEquals(newTitle, movies[0].getTitle());
    }

    @Test
    public void deleteMoviesIdShouldDeleteMovie() {
        when().delete("/movies/" + movies[0].getId())
                .then().statusCode(204);

        assertEquals(1, movieService.count());
    }

    private Movie[] twoSampleMovies() {
        Movie batman = new Movie();
        batman.setTitle("Batman");
        movieService.save(batman);

        Movie psy = new Movie();
        psy.setTitle("Psy");
        movieService.save(psy);

        return new Movie[]{batman, psy};
    }
}
