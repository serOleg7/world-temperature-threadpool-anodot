package com.company.controller;

import com.company.WorldTemperature;
import com.company.WorldTemperatureImpl;
import com.company.model.AggregatorType;
import com.company.model.City;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<String> cityIds = new HashSet<>();

        WorldTemperature worldTemperature = new WorldTemperatureImpl(AggregatorType.MAXAVG, 50_000, 100, 3);
    //  WorldTemperature worldTemperature = new WorldTemperatureImpl(AggregatorType.MAXAVG);

        LinkedHashMap<City, Double> results = worldTemperature.get(cityIds);

    }

}
