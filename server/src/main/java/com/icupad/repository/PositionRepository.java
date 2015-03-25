package com.icupad.repository;

import com.icupad.domain.test_result.fluid_balance.FluidBalancePosition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends PagingAndSortingRepository<FluidBalancePosition, Long> {
}
