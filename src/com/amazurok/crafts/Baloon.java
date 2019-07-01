package com.amazurok.crafts;

import com.amazurok.interfce.Flyable;
import com.amazurok.tower.WeatherTower;


public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                weatherTower.writeToFile(String.format("%s#%s(%d): Let's enjoy the good weather and take some pics.", this.getClass().getName(), this.name, this.id));
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                weatherTower.writeToFile(String.format("%s#%s(%d): Damn you rain! You messed up my baloon.", this.getClass().getName(), this.name, this.id));
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                weatherTower.writeToFile(String.format("%s#%s(%d): Oh no, I can't see anything!!", this.getClass().getName(), this.name, this.id));
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                weatherTower.writeToFile(String.format("%s#%s(%d): It's snowing. We're gonna crash.", this.getClass().getName(), this.name, this.id));
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
        weatherTower.writeToFile(String.format("Tower says: %s(%d) registered to weather tower.", this.name, this.id));
    }
}
