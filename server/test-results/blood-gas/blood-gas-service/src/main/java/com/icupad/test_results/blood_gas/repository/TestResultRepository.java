package com.icupad.test_results.blood_gas.repository;

import com.icupad.repository.BaseRepository;
import com.icupad.test_results.blood_gas.domain.TestResult;
import org.springframework.stereotype.Repository;

@Repository("bloodGasTestResultRepository")
public interface TestResultRepository extends BaseRepository<TestResult, Long>, TestResultRepositoryCustom {
    TestResult findByHl7Id(String hl7Id);

//    @Query(nativeQuery = true, value =
//            "select * from blood_gas_test_result bgtres " +
//                    "join blood_gas_test_request bgtreq on bgtres.test_request_id = bgtreq.id " +
//                    "join blood_gas_test_panel_result bgttpr on bgtreq.test_panel_result_id = bgttpr.id " +
//                    "where bgttpr.request_date > :startDate " +
//                    "and bgttpr.request_date < :endDate")
//    Collection<TestResult> findByStartDateAndEndDate(LocalDateTime startDate,
//                                                           LocalDateTime endDate);
}