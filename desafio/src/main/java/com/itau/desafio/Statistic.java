package com.itau.desafio;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Statistic(
        @JsonProperty("count") Long count,
        @JsonProperty("sum") Double sum,
        @JsonProperty("avg") Double avg,
        @JsonProperty("min") Double min,
        @JsonProperty("max") Double max
) {
    public Statistic {
        if (count == 0) {
            min = 0.0;
            max = 0.0;
        }
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "count=" + count +
                ", sum=" + sum +
                ", avg=" + avg +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
