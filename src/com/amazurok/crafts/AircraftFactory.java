package com.amazurok.crafts;

import com.amazurok.exceptions.IllegalInputException;
import com.amazurok.interfce.Flyable;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws IllegalInputException {
        switch (type) {
            case "Helicopter":
                return new Helicopter(name, new Coordinates(longitude, latitude, height));
            case "Baloon":
                return new Baloon(name, new Coordinates(longitude, latitude, height));
            case "JetPlane":
                return new JetPlane(name, new Coordinates(longitude, latitude, height));
            default:
                throw new IllegalInputException(String.format("Type '%s' is not valid", type));
        }
    }
}
