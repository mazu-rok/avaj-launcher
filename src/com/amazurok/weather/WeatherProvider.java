package com.amazurok.weather;

import com.amazurok.crafts.Coordinates;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static String[] weather;

    private WeatherProvider() {
        weather = new String[]{ "RAIN", "FOG", "SUN", "SNOW" };
        weatherProvider = new WeatherProvider();
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int random = new Random().nextInt((coordinates.getLatitude() * coordinates.getLongitude())
                / coordinates.getHeight());

        return (weather[random % weather.length]);
    }
}
