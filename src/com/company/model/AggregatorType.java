package com.company.model;

public enum AggregatorType {
        MAXAVG("com.company.aggregators.MaxAverageAggregator"), MIN("com.company.aggregators.MinimumAggregator");

        private final String value;

        AggregatorType(String value) {
                this.value = value;
        }

        @Override
        public String toString() {
                return value;
        }
}
