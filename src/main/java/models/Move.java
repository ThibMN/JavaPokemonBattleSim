package models;

public class Move {
    private String name;
    private int power;
    private Type type;
    private boolean isPhysical;
    private double secondaryEffectChance; // Chance d'un effet secondaire (ex: brûlure, paralysie)
    private StatusEffect secondaryEffect; // Effet secondaire de l'attaque

    public Move(String name, int power, Type type, boolean isPhysical, double secondaryEffectChance, StatusEffect secondaryEffect) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.isPhysical = isPhysical;
        this.secondaryEffectChance = secondaryEffectChance;
        this.secondaryEffect = secondaryEffect;
    }

    public boolean triggersSecondaryEffect() {
        return secondaryEffect != null && Math.random() < secondaryEffectChance;
    }

    // Getters
    public String getName() { return name; }
    public int getPower() { return power; }
    public Type getType() { return type; }
    public boolean isPhysical() { return isPhysical; }
    public double getSecondaryEffectChance() { return secondaryEffectChance; }
    public StatusEffect getSecondaryEffect() { return secondaryEffect; }
}
