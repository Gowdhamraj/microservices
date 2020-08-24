package com.gow.microservices.poc.limitsservice.model;

public class LimitsConfiguration {

    private Integer maximum;
    private Integer minimum;

    public LimitsConfiguration(Integer maximum, Integer minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }
}
