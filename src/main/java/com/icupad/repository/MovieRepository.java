package com.icupad.repository;

import com.icupad.domain.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends BaseRepository<Movie, Long>, MovieCustomRepository {
}
