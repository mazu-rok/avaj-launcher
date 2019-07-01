package com.amazurok.tower;

import com.amazurok.crafts.Coordinates;
import com.amazurok.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.conditionChanged();
    }

}
