package com.gyf.mongotest.entry;

public class QueryResult {
    private Performance performance;
    private double executionTimeInSeconds;

    public QueryResult(Performance performance, double executionTimeInSeconds) {
        this.performance = performance;
        this.executionTimeInSeconds = executionTimeInSeconds;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public double getExecutionTimeInSeconds() {
        return executionTimeInSeconds;
    }

    public void setExecutionTimeInSeconds(double executionTimeInSeconds) {
        this.executionTimeInSeconds = executionTimeInSeconds;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "performance=" + performance +
                ", executionTimeInSeconds=" + executionTimeInSeconds +
                '}';
    }
}
