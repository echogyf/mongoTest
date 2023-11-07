package com.gyf.mongotest.service;

import com.gyf.mongotest.entry.Performance;
import com.gyf.mongotest.repository.PerformanceRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PerformanceServiceImpl implements PerformanceService{
    private final MongoTemplate mongoTemplate;

    private final PerformanceRepository performanceRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(2); // 创建一个包含两个线程的线程池

    public PerformanceServiceImpl(MongoTemplate mongoTemplate, PerformanceRepository performanceRepository){
        this.mongoTemplate = mongoTemplate;
        this.performanceRepository = performanceRepository;
    }

    // 生成Performance对象
    private Performance generatePerformance(String uuid, int arrayLength) {
        Random random = new Random();
        List<Integer> ids = new ArrayList<>();
        for (int j = 0; j < arrayLength; j++) {
            ids.add(10000000 + random.nextInt(90000000));
        }
        return new Performance(uuid, ids);
    }

    @Override
    public String addTen() {
        List<Performance> performanceList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) { // 插入100万条数据
            String uuid = UUID.randomUUID().toString();
            int arrayLength = 4; // 随机生成数组长度，范围为1到10
            Performance performance = generatePerformance(uuid, arrayLength);
            performanceList.add(performance);
        }
        performanceRepository.saveAll(performanceList); // 使用MongoRepository的saveAll方法保存数据
        return "成功插入 " + performanceList.size() + " 条数据";

    }

    @Override
    public String addTwo() {
        List<Performance> performanceList = new ArrayList<>();
        for (int i = 0; i < 200000; i++) { // 插入20万条数据
            String uuid = UUID.randomUUID().toString();
            int arrayLength = 4; // 随机生成数组长度，范围为1到10
            Performance performance = generatePerformance(uuid, arrayLength);
            performanceList.add(performance);
        }
        performanceRepository.saveAll(performanceList); // 使用MongoRepository的saveAll方法保存数据
        return "成功插入 " + performanceList.size() + " 条数据";
    }

    @Override
    public String addOne(){
        String uuid = UUID.randomUUID().toString();
        int arrayLength = 4; // 随机生成数组长度，范围为1到10
        Performance performance = generatePerformance(uuid, arrayLength);
        performanceRepository.insert(performance);
        return performance.getMyUuid();
    }

    @Override
    public String addSame() {
        Random random = new Random();
        String uuid = UUID.randomUUID().toString();
        int arrayLength = 4; // 随机生成数组长度，范围为1到10
        for (int i = 0; i < 3; i++){
            List<Integer> ids = new ArrayList<>();
            for (int j = 0; j < arrayLength; j++) {
                ids.add(10000000 + random.nextInt(90000000)); // 随机生成整数，范围为0到99
            }
            Performance performance = new Performance();
            performance.setMyUuid(uuid);
            performance.setIds(ids);
            performance.setExpirationDate(new Date(System.currentTimeMillis() + 120 * 1000));
            performanceRepository.insert(performance);
        }
        return uuid;
    }

    private void deleteDatas() {
        System.out.println("执行删除20w");
        executorService.submit(() -> {
            for (int i = 0; i < 200000; i++) {
                AggregationOperation sampleOperation = Aggregation.sample(1);
                Aggregation aggregation = Aggregation.newAggregation(sampleOperation);
                List<Performance> performanceList = mongoTemplate.aggregate(aggregation, "performance", Performance.class).getMappedResults();
                if (!performanceList.isEmpty()) {
                    Performance performance = performanceList.get(0);
                    performanceRepository.deleteById(performance.getId());
                }
            }
        });
    }

    // 插入100条数据
    private void insertData() {
        System.out.println("执行插入");
        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString();
            int arrayLength = 4;
            // 生成数据并插入MongoDB
            executorService.submit(() -> {
                Performance performance = generatePerformance(uuid, arrayLength);
                performanceRepository.insert(performance);
            });
        }
    }

    // 随机删除100条数据
    private void deleteData() {
        System.out.println("执行删除");
        executorService.submit(() -> {
            // 随机删除100条数据
            for (int i = 0; i < 100; i++) {
                AggregationOperation sampleOperation = Aggregation.sample(1);
                Aggregation aggregation = Aggregation.newAggregation(sampleOperation);
                List<Performance> performanceList = mongoTemplate.aggregate(aggregation, "performance", Performance.class).getMappedResults();
                if (!performanceList.isEmpty()) {
                    Performance performance = performanceList.get(0);
                    performanceRepository.deleteById(performance.getId());
                }
            }
        });
    }

     //每秒执行一次插入和删除操作
    @Scheduled(fixedRate = 1000)
    public void insertAndDeleteData() {
        insertData();
        deleteData();
    }

    @Scheduled(fixedRate = 120000)
    public void delete() {
        deleteDatas();
    }
}
