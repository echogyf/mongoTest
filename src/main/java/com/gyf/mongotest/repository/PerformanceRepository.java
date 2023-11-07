package com.gyf.mongotest.repository;

import com.gyf.mongotest.entry.Performance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface PerformanceRepository extends MongoRepository<Performance, String> {
    List<Performance> findByMyUuid(String uuid);



}
