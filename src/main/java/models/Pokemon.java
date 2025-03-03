package models;

import java.util.List;

public class Pokemon {
    private String name;
    private int hp, maxHp, attack, specialAttack, defense, specialDefense, speed;
    private Type[] types;
    private List<Move> moves;
    private HeldItems heldItem;
    private StatusEffect statusEffect; // New: Status effect

    public Pokemon(String name, int hp, int attack, int specialAttack, int defense, int specialDefense, int speed, Type[] types, List<Move> moves) {
        this.name = name;
        this.hp = this.maxHp = hp;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.types = types;
        this.moves = moves;
        this.statusEffect = null; // No status effect at start
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public boolean isFainted() {
        return this.hp <= 0;
    }

    public void setStatusEffect(StatusEffect status) {
        this.statusEffect = status;
    }

    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    public void applyStatusEffect() {
        if (statusEffect != null) {
            statusEffect.applyEffect(this);
        }
    }

    public void cureStatus() {
        this.statusEffect = null;
        System.out.println(name + " has been cured of its status condition!");
    }

    public void attack(Pokemon target, Move move) {
        double typeMultiplier = Type.getMultiplier(move.getType(), target.getTypes());
        double damage = move.getPower() * ((double) (move.isPhysical() ? this.attack : this.specialAttack) /
                (move.isPhysical() ? target.getDefense() : target.getSpecialDefense()))
                * typeMultiplier * (0.85 + Math.random() * 0.15);
        target.takeDamage((int) damage);
    }

    // Getters and setters
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getSpecialAttack() { return specialAttack; }
    public int getDefense() { return defense; }
    public int getSpecialDefense() { return specialDefense; }
    public int getSpeed() { return speed; }
    public Type[] getTypes() { return types; }
    public List<Move> getMoves() { return moves; }
    public HeldItems getHeldItem() { return heldItem; }

    public void setHp(int hp) { this.hp = hp; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setHeldItem(HeldItems heldItem) { this.heldItem = heldItem; }
}
