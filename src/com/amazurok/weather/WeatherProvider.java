package com.amazurok.weather;

import com.amazurok.crafts.Coordinates;

public class WeatherProvider {
    private WeatherProvider weatherProvider;
    private String[] weather;

    private WeatherProvider() {
        this.weather = new String[] { "RAIN", "FOG", "SUN", "SNOW" };
        this.weatherProvider = new WeatherProvider();
    }

    public WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {

    }
}
