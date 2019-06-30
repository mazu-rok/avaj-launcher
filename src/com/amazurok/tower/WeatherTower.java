package com.amazurok.tower;

import com.amazurok.crafts.Coordinates;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return "";
    }

    public void changeWeather() {
        this.conditionChanged();
    }

}
