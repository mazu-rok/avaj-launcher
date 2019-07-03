package com.amazurok.simulator;

import com.amazurok.crafts.AircraftFactory;
import com.amazurok.exceptions.ArgumentsException;
import com.amazurok.exceptions.IllegalInputException;
import com.amazurok.exceptions.ReadFileException;
import com.amazurok.interfce.Flyable;
import com.amazurok.tower.WeatherTower;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private static int TYPE = 0;
    private static int NAME = 1;
    private static int LONGITUDE = 2;
    private static int LATITUDE = 3;
    private static int HEIGHT = 4;


    public static void main(String[] args) {
        WeatherTower weatherTower = new WeatherTower();
        List<Flyable> aircraftList = new ArrayList<>();

        try {
            PrintWriter pw = new PrintWriter("simulation.txt");
            pw.close();
            if (args.length > 1) {
                throw new ArgumentsException("Too many arguments");
            } else if (args.length == 0) {
                throw new ArgumentsException("Please, pass scenario name as a program argument");
            }
            InputStream file = new FileInputStream(args[0]);
            BufferedReader buf = new BufferedReader(new InputStreamReader(file));

            String timesOfSimulationString = buf.readLine();
            if (timesOfSimulationString == null) {
                throw new ReadFileException("File is empty");
            }
            int timesOfSimulation;
            try {
                timesOfSimulation = Integer.parseInt(timesOfSimulationString);
                if (timesOfSimulation < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                throw new ReadFileException("The first line of the file must contain a positive integer number.");
            }
            String line = buf.readLine();
            int lineNum = 2;
            while (line != null) {
                String[] aircraft = line.split(" ");
                if (aircraft.length != 5) {
                    throw new IllegalInputException(String.format("Line[%d] is invalid", lineNum));
                }
                int longitude;
                try {
                    longitude = Integer.parseInt(aircraft[LONGITUDE]);
                    if (longitude < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    throw new ReadFileException(String.format("Longitude in line[%d] is invalid", lineNum));
                }
                int latitude;
                try {
                    latitude = Integer.parseInt(aircraft[LATITUDE]);
                    if (latitude < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    throw new ReadFileException(String.format("Latitude in line[%d] is invalid", lineNum));
                }
                int height;
                try {
                    height = Integer.parseInt(aircraft[HEIGHT]);
                } catch (NumberFormatException e) {
                    throw new ReadFileException(String.format("Height in line[%d] is invalid", lineNum));
                }
                Flyable flyable = AircraftFactory.newAircraft(aircraft[TYPE], aircraft[NAME], longitude, latitude, height);
                flyable.registerTower(weatherTower);
                aircraftList.add(flyable);
                lineNum++;
                line = buf.readLine();
            }
            buf.close();
            for (int i = 0; i < timesOfSimulation; i++) {
                weatherTower.writeToFile(String.format("->Simulation number %d\n", i + 1));
                weatherTower.changeWeather();
            }
        } catch (ReadFileException | IllegalInputException | ArgumentsException e) {
            System.out.println(String.format("\u001B[31mERROR: %s\u001B[0m", e.getMessage()));
        } catch (FileNotFoundException e) {
            System.out.println(String.format("\u001B[31mERROR: File '%s' not found\u001B[0m", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("\u001B[31mAn error has occurred: %s\u001B[0m", e.getMessage()));
        }

    }
}

