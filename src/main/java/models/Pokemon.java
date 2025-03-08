package models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private int hp;
    private final int maxHp;
    private int attack;
    private final int specialAttack;
    private final int defense;
    private final int specialDefense;
    private int speed;
    private final Type[] types;
    private List<Move> moves;
    private HeldItems heldItem;
    private StatusEffect statusEffect; // New: Status effect

    public Pokemon(String name, int hp, int attack, int specialAttack, int defense, int specialDefense, int speed, Type[] types, List<Move> moves) {
        this.name = name;
        this.hp = this.maxHp = 150 + hp;
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
        if (!move.hits()) {
            System.out.println(name + "'s attack missed!");
            return;
        }

        double typeMultiplier = Type.getMultiplier(move.getType(), target.getTypes());
        double stab = Arrays.asList(this.types).contains(move.getType()) ? 1.5 : 1.0;

        double damage = move.getPower() *
                ((double) (move.isPhysical() ? this.attack : this.specialAttack) /
                        (move.isPhysical() ? target.getDefense() : target.getSpecialDefense())) *
                typeMultiplier * stab * (0.85 + Math.random() * 0.15);

        target.takeDamage((int) damage);
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void initializeDefaultMoves() {
        if (moves == null || moves.isEmpty()) {
            moves = Arrays.asList(
                    new Move("Tackle", 40, Type.NORMAL, true, 0, null, 80),
                    new Move("Quick Attack", 40, Type.NORMAL, true, 0, null, 100),
                    new Move("Struggle", 50, Type.NORMAL, true, 0, null, 100),
                    new Move("Scratch", 40, Type.NORMAL, true, 0, null, 100)
            );
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public Type[] getTypes() {
        return types;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public HeldItems getHeldItem() {
        return heldItem;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHeldItem(HeldItems heldItem) {
        this.heldItem = heldItem;
    }
}
