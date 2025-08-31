package com.microservice.limit_service.model;

public class LimitServiceConfiguration {
    private int minimum;
    private int maximum;

    public LimitServiceConfiguration() {
    }

    public LimitServiceConfiguration(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
