package com.amazurok.crafts;

import com.amazurok.interfce.Flyable;
import com.amazurok.tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

     JetPlane(String name, Coordinates coordinates) {
         super(name, coordinates);
     }

     public void updateConditions() {

     }

     public void registerTower(WeatherTower weatherTower) {
         this.weatherTower = weatherTower;
     }
}
