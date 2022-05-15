package com.company;

import com.company.model.AggregatorType;
import com.company.model.City;
import com.company.model.Result;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorldTemperatureImpl implements WorldTemperature {
    private final int CITY_POPULATION;
    private final int POOL_SIZE;
    static AggregationOps aggregationOps;

    public WorldTemperatureImpl(AggregatorType type, int cityPopulation, int poolSize, int sizeOfResults) {
        CITY_POPULATION = cityPopulation;
        POOL_SIZE = poolSize;
        aggregationOps = new AggregationOpsImpl(type, sizeOfResults);
    }

    public WorldTemperatureImpl(AggregatorType type) {
        this(type, 50_000, 100, 3);
    }

    @Override
    public LinkedHashMap<City, Double> get(Set<String> cityIds) {
        Set<City> cities = getCitiesFromApi(cityIds);

        createRunTasks(cities);
        //doWork(cities);  //used instead of createRunTasks, based on parallel stream

        return getConvertedResultsMap();
    }

    private LinkedHashMap<City, Double> getConvertedResultsMap() {
        TreeSet<Result> tmp = aggregationOps.getResults();
        LinkedHashMap<City, Double> fRes = new LinkedHashMap<>();
        for (Result result : tmp)
            fRes.put(result.getCity(), result.getAggregatedTemp());
        return fRes;
    }


    private void createRunTasks(Set<City> cities) {
        List<Task> tasks = new ArrayList<>();
        for (City city : cities)
            if (city.getPopulation() > CITY_POPULATION)
                tasks.add(new Task(city));

        ExecutorService executorService = Executors.newWorkStealingPool(POOL_SIZE);
        for (Task task : tasks)
            executorService.execute(task);

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Set<City> getCitiesFromApi(Set<String> cityIds) {
        Set<City> cities = new HashSet<>();
        // cities = getAllCitiesByIds(cityIds); // getCities from API
        return cities;
    }



//    private void doWork(Set<City> cities) {
//        cities.parallelStream().forEach((o) -> {
//            if (o.getPopulation() > CITY_POPULATION)
//                aggregationOps.aggregate(Map.entry(o, getLastYearTemperature(o.getId())));
//        });
//    }
}
