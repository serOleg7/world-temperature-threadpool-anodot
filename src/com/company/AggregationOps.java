package com.company;

import com.company.model.City;
import com.company.model.DailyTemp;
import com.company.model.Result;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface AggregationOps {
    void aggregate(Map.Entry<City, List<DailyTemp>> stats);

    TreeSet<Result> getResults();
}
