package com.crucialtechnologies.task.mapfragment.projectHelper;

public class Config {
    private static Config instance;
    private String currentLatitude;
    private String currentLongitude;
    private String currentLocation;

    public static Config getInstance() {
        if (instance == null)
            instance = new Config();

        return instance;
    }

    public static void clear() {
        instance = null;
        instance = new Config();
    }
    private Config() {
    }
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public double getDCurrentLatitude() {
        try {
            return Double.parseDouble(currentLatitude);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    public double getDCurrentLongitude() {
        try {
            return Double.parseDouble(currentLongitude);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
