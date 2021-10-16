package com.redhat.katacoda.kafka;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class VehicleInfo {
    
    @JsonIgnore
    private String provider;
    private long vehicleId;
    private double pricePerMinute;
    private long timeToPickup;
    private int availableSpace;
    private boolean available;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public long getTimeToPickup() {
        return timeToPickup;
    }

    public void setTimeToPickup(long timeToPickup) {
        this.timeToPickup = timeToPickup;
    }

    public int getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(int availableSpace) {
        this.availableSpace = availableSpace;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "VehicleInfo{" + "provider='" + getProvider() + '\'' + ", vehicleId=" + getVehicleId()
                + ", pricePerMinute=" + getPricePerMinute() + ", timeToPickup=" + getTimeToPickup()
                + ", availableSpace=" + getAvailableSpace() + ", available=" + isAvailable() + '}';
    }
}
