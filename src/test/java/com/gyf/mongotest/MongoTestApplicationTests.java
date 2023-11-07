package com.gyf.mongotest;



import com.gyf.mongotest.entry.Performance;
import com.gyf.mongotest.service.PerformanceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoTestApplicationTests {

    @Autowired
    private PerformanceService performanceService;


    @Test
    public void test(){

    }

    
}
