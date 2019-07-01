package com.amazurok.crafts;

import com.amazurok.interfce.Flyable;
import com.amazurok.tower.WeatherTower;


public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

     JetPlane(String name, Coordinates coordinates) {
         super(name, coordinates);
     }

     public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                weatherTower.writeToFile(String.format("%s#%s(%d): This is hot.",
                        this.getClass().getName(), this.name, this.id));
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                weatherTower.writeToFile(String.format("%s#%s(%d): It's raining. Better watch out for lightings.",
                        this.getClass().getName(), this.name, this.id));
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight() );
                weatherTower.writeToFile(String.format("%s#%s(%d): Oh no, I can't see anything!!",
                        this.getClass().getName(), this.name, this.id));
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                weatherTower.writeToFile(String.format("%s#%s(%d): OMG! Winter is coming!",
                        this.getClass().getName(), this.name, this.id));
                break;
        }
        if (coordinates.getHeight() == 0) {
            weatherTower.unregister(this);
            weatherTower.writeToFile(String.format("%s#%s(%d) landing in [lat:%d, lon:%d]",
                    this.getClass().getName(), this.name, this.id, coordinates.getLatitude(), coordinates.getLongitude()));
            weatherTower.writeToFile(String.format("Tower says: %s#%s(%d) unregistered from weather tower.",
                    this.getClass().getName(), this.name, this.id));
        }
     }

     public void registerTower(WeatherTower weatherTower) {
         this.weatherTower = weatherTower;
         weatherTower.register(this);
         weatherTower.writeToFile(String.format("Tower says: %s#%s(%d) registered to weather tower.", this.getClass().getName(), this.name, this.id));
     }
}
