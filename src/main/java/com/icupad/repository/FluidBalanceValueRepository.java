package com.icupad.repository;

import com.icupad.domain.test_result.fluid_balance.FluidBalanceValue;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FluidBalanceValueRepository extends PagingAndSortingRepository<FluidBalanceValue, Long> {
}
