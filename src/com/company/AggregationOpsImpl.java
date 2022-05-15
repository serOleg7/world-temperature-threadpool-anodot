package com.company;

import com.company.model.AggregatorType;
import com.company.model.City;
import com.company.model.DailyTemp;
import com.company.model.Result;
import lombok.Getter;

import java.util.*;

@Getter
public class AggregationOpsImpl implements AggregationOps {
    private final int SIZE_OF_RESULTS;
    private final TreeSet<Result> results;
    private final AggregatorType type;
    private Aggregator aggregator;

    public AggregationOpsImpl(AggregatorType type, int size) {
        results = type.toString().contains("Max") ? new TreeSet<>(Collections.reverseOrder()) : new TreeSet<>();
        this.type = type;
        SIZE_OF_RESULTS = size;
        initAggregator();
    }

    private void initAggregator() {
        try {
            Class<?> clss = Class.forName(type.toString());
            aggregator = (Aggregator) clss.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aggregate(Map.Entry<City, List<DailyTemp>> stats) {
        double temp = aggregator.aggregate(stats.getValue());
        updateResults(stats, temp);
    }

    private void updateResults(Map.Entry<City, List<DailyTemp>> stats, double temp) {
        synchronized (this) {
            if (results.size() < SIZE_OF_RESULTS)
                results.add(new Result(stats.getKey(), temp));

            else if (type.toString().contains("Max") && results.last().getAggregatedTemp() < temp) {
                results.remove(results.last());
                results.add(new Result(stats.getKey(), temp));


            } else if (!type.toString().contains("Max") && results.last().getAggregatedTemp() > temp) {
                results.remove(results.last());
                results.add(new Result(stats.getKey(), temp));
            }
        }
    }


}
