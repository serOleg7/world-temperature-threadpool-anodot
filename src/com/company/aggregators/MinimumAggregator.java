package com.company.aggregators;

import com.company.Aggregator;
import com.company.model.DailyTemp;

import java.util.List;

public class MinimumAggregator implements Aggregator {

    @Override
    public double aggregate(List<DailyTemp> stats) {
        return stats.stream().mapToDouble(DailyTemp::getTemperature).min().orElse(0);
    }
}
