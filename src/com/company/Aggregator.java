package com.company;

import com.company.model.DailyTemp;

import java.util.List;

public interface Aggregator {
    double aggregate(List<DailyTemp> stats);


}
