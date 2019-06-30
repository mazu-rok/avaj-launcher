package com.amazurok.tower;

import com.amazurok.interfce.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {

    }

    public void unregister(Flyable flyable) {

    }

    protected void conditionChanged() {

    }
}
