package com.amazurok.tower;

import com.amazurok.interfce.Flyable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    private FileWriter writer;
    private File file = new File("simulation.txt");

    private List<Flyable> observers = new ArrayList<>();
    private List<Flyable> unregistered = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        if (!unregistered.contains(flyable)) {
            unregistered.add(flyable);
        }
    }

    protected void conditionChanged() {
        for (Flyable flyable: observers) {
            flyable.updateConditions();
        }
        observers.removeAll(unregistered);
    }

    public void writeToFile(String text) {
        try {
            this.file.createNewFile();
            this.writer = new FileWriter(file, true);

            try {
                writer.write(text);
                writer.flush();
            } catch (Exception exception) {
                System.out.println("ERROR: could not write to file");
            }
        } catch (IOException e) {
            System.out.println("ERROR: could not create/open file");
        }
    }
}
