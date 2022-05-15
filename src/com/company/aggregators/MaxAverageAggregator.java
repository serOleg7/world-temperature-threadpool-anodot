package com.company.aggregators;

import com.company.Aggregator;
import com.company.model.DailyTemp;

import java.util.List;

public class MaxAverageAggregator implements Aggregator {

/*
    used for finding top average
*/

    @Override
    public double aggregate(List<DailyTemp> stats) {
        return stats.stream().mapToDouble(DailyTemp::getTemperature).average().orElse(0);
    }
}

