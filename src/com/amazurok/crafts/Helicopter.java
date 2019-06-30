package com.amazurok.crafts;

import com.amazurok.interfce.Flyable;
import com.amazurok.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

     Helicopter(String name, Coordinates coordinates) {
         super(name, coordinates);
     }

     public void updateConditions() {

     }

     public void registerTower(WeatherTower weatherTower) {
         this.weatherTower = weatherTower;
     }
}
