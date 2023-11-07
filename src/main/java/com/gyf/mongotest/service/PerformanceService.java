package com.gyf.mongotest.service;

import com.gyf.mongotest.entry.Performance;
import com.gyf.mongotest.entry.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface PerformanceService{
    String addTen();

    String addTwo();

    String addOne();

    String addSame();
}