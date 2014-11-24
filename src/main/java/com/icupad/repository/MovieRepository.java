package com.icupad.repository;

import com.icupad.domain.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>, MovieCustomRepository {
}
