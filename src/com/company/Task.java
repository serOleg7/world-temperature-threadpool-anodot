package com.company;

import com.company.model.City;
import com.company.model.DailyTemp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task implements Runnable{
    private final City city;

    public Task(City city) {
        this.city = city;
    }

    @Override
    public void run() {
        List<DailyTemp> list = new ArrayList<>();
        //Goes to api fill list
        //
        //list =  getLastYearTemperature(city.getId());
        WorldTemperatureImpl.aggregationOps.aggregate(Map.entry(city, list));
    }
}
