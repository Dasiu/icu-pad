package com.icupad.api_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icupad.BaseIntegrationTest;
import com.icupad.domain.Movie;
import com.icupad.service.MovieService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoviesAPITest extends BaseIntegrationTest {
    @Value("${local.server.port}")
    int port;

    @Autowired
    private MovieService movieService;

    private Movie[] movies;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        restAssuredConfig();
        movieService.deleteAll();
        movies = twoSampleMovies();
    }

    @Test
    public void getMoviesShouldListAllMovies() {
        when().get("/movies").then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", hasItems("Batman", "Psy"));
    }

    @Test
    public void getMoviesIdShouldReturnMovie() {
        Movie response =
                when().get("/movies/{0}", movies[0].getId()).then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().as(Movie.class);

        assertEquals(movies[0], response);
    }

    @Test
    public void postMoviesShouldCreateNewMovie() {
        Movie newMovie = new Movie();
        newMovie.setTitle("Inception");

        Long newMovieId = given()
                .contentType(ContentType.JSON)
                .body(newMovie)

                .when().post("/movies").then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().<Integer>path("id").longValue();

        assertTrue(movieService.exists(newMovieId));
    }

    @Test
    public void putMoviesIdShouldUpdateMovie() {
        String newTitle = "The Dark Knight";
        Movie updatedMovie = new Movie();
        updatedMovie.setTitle(newTitle);

        given()
                .contentType(ContentType.JSON)
                .body(updatedMovie)

                .when().put("/movies/{0}", movies[0].getId()).then()
                .statusCode(204);
    }

    @Test
    public void deleteMoviesIdShouldDeleteMovie() {
        when().delete("/movies/{0}", movies[0].getId()).then()
                .statusCode(204);
    }

    private void restAssuredConfig() {
        RestAssured.port = port;
        RestAssured.authentication = form("admin", "admin", new FormAuthConfig("/login", "username", "password"));
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (cls, charset) -> objectMapper
                ));
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
