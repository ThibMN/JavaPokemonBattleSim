package models;

public abstract class HeldItems{
    private final String name;

    private HeldItems(String name) {
        this.name = name;
    }

    abstract void effects();

    public String getName() { return name; }
}
