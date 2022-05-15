package com.company;

import com.company.model.City;

import java.util.LinkedHashMap;
import java.util.Set;

public interface WorldTemperature {
    LinkedHashMap<City, Double> get(Set<String> cityIds);
}
