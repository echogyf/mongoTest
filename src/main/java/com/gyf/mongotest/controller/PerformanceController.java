package com.gyf.mongotest.controller;

import com.gyf.mongotest.entry.Performance;
import com.gyf.mongotest.repository.PerformanceRepository;
import com.gyf.mongotest.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class PerformanceController {
    private final PerformanceService performanceService;

    private final PerformanceRepository performanceRepository;

    public PerformanceController(PerformanceService performanceService, PerformanceRepository performanceRepository){
        this.performanceService = performanceService;
        this.performanceRepository = performanceRepository;
    }

    @GetMapping("/{uuid}")
    public List<Performance> getByUUID(@PathVariable String uuid) {
        return performanceRepository.findByMyUuid(uuid);
    }

    @GetMapping("/addTen")
    public String addTen(){
        return performanceService.addTen();
    }

    @GetMapping("/addTwo")
    public String addTwo(){
        return performanceService.addTwo();
    }

    @GetMapping("/addOne")
    public String addOne(){
        return performanceService.addOne();
    }

    @GetMapping("/addSame")
    public String addSame(){
        return performanceService.addSame();
    }

}
