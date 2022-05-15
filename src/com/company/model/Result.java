package com.company.model;

import lombok.*;

@Getter
@AllArgsConstructor
public class Result implements Comparable<Result> {
    private City city;
    private double aggregatedTemp;


    @Override
    public int compareTo(Result o) {
        int res = Double.compare(aggregatedTemp, o.aggregatedTemp);
        if (res != 0) return res;
        return city.getId().compareTo(o.getCity().getId());
    }
}
