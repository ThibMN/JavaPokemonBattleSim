package models;

public class Move {
    private final String name;
    private final int power;
    private final Type type;
    private final boolean isPhysical;
    private final double secondaryEffectChance; // Chance of a secondary effect (ex: burning, paralysis)
    private final StatusEffect secondaryEffect; // secondary effect of the attack

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

    public StatusEffect getStatusEffect() {
        return secondaryEffect;
    }
}
