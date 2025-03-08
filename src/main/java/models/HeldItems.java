package models;

import java.io.Serializable;

public abstract class HeldItems implements Serializable {
    private final String name;

    private HeldItems(String name) {
        this.name = name;
    }

    abstract void effects();

    public String getName() { return name; }
}
