package com.amazurok.interfce;

import com.amazurok.tower.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower wetherTower);
}
