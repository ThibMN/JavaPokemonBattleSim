package models;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE, FIGHTING, POISON,
    GROUND, FLYING, PSYCHIC, BUG, ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY;

    private static final Map<Type, Map<Type, Double>> effectiveness = new HashMap<>();

    static {
        // Initialisation des maps pour chaque type
        for (Type type : Type.values()) {
            effectiveness.put(type, new HashMap<>());
        }

        // Super efficace (x2)
        effectiveness.get(FIRE).put(GRASS, 2.0);
        effectiveness.get(FIRE).put(ICE, 2.0);
        effectiveness.get(FIRE).put(BUG, 2.0);
        effectiveness.get(FIRE).put(STEEL, 2.0);

        effectiveness.get(WATER).put(FIRE, 2.0);
        effectiveness.get(WATER).put(GROUND, 2.0);
        effectiveness.get(WATER).put(ROCK, 2.0);

        effectiveness.get(ELECTRIC).put(WATER, 2.0);
        effectiveness.get(ELECTRIC).put(FLYING, 2.0);

        effectiveness.get(GRASS).put(WATER, 2.0);
        effectiveness.get(GRASS).put(GROUND, 2.0);
        effectiveness.get(GRASS).put(ROCK, 2.0);

        effectiveness.get(ICE).put(GRASS, 2.0);
        effectiveness.get(ICE).put(GROUND, 2.0);
        effectiveness.get(ICE).put(FLYING, 2.0);
        effectiveness.get(ICE).put(DRAGON, 2.0);

        effectiveness.get(FIGHTING).put(NORMAL, 2.0);
        effectiveness.get(FIGHTING).put(ICE, 2.0);
        effectiveness.get(FIGHTING).put(ROCK, 2.0);
        effectiveness.get(FIGHTING).put(DARK, 2.0);
        effectiveness.get(FIGHTING).put(STEEL, 2.0);

        effectiveness.get(POISON).put(GRASS, 2.0);
        effectiveness.get(POISON).put(FAIRY, 2.0);

        effectiveness.get(GROUND).put(FIRE, 2.0);
        effectiveness.get(GROUND).put(ELECTRIC, 2.0);
        effectiveness.get(GROUND).put(POISON, 2.0);
        effectiveness.get(GROUND).put(ROCK, 2.0);
        effectiveness.get(GROUND).put(STEEL, 2.0);

        effectiveness.get(FLYING).put(GRASS, 2.0);
        effectiveness.get(FLYING).put(FIGHTING, 2.0);
        effectiveness.get(FLYING).put(BUG, 2.0);

        effectiveness.get(PSYCHIC).put(FIGHTING, 2.0);
        effectiveness.get(PSYCHIC).put(POISON, 2.0);

        effectiveness.get(BUG).put(GRASS, 2.0);
        effectiveness.get(BUG).put(PSYCHIC, 2.0);
        effectiveness.get(BUG).put(DARK, 2.0);

        effectiveness.get(ROCK).put(FIRE, 2.0);
        effectiveness.get(ROCK).put(ICE, 2.0);
        effectiveness.get(ROCK).put(FLYING, 2.0);
        effectiveness.get(ROCK).put(BUG, 2.0);

        effectiveness.get(GHOST).put(PSYCHIC, 2.0);
        effectiveness.get(GHOST).put(GHOST, 2.0);

        effectiveness.get(DRAGON).put(DRAGON, 2.0);

        effectiveness.get(DARK).put(PSYCHIC, 2.0);
        effectiveness.get(DARK).put(GHOST, 2.0);

        effectiveness.get(STEEL).put(ICE, 2.0);
        effectiveness.get(STEEL).put(ROCK, 2.0);
        effectiveness.get(STEEL).put(FAIRY, 2.0);

        effectiveness.get(FAIRY).put(FIGHTING, 2.0);
        effectiveness.get(FAIRY).put(DRAGON, 2.0);
        effectiveness.get(FAIRY).put(DARK, 2.0);

        // Peu efficace (x0.5)
        effectiveness.get(FIRE).put(FIRE, 0.5);
        effectiveness.get(FIRE).put(WATER, 0.5);
        effectiveness.get(FIRE).put(ROCK, 0.5);
        effectiveness.get(FIRE).put(DRAGON, 0.5);

        effectiveness.get(WATER).put(WATER, 0.5);
        effectiveness.get(WATER).put(GRASS, 0.5);
        effectiveness.get(WATER).put(DRAGON, 0.5);

        effectiveness.get(GRASS).put(FIRE, 0.5);
        effectiveness.get(GRASS).put(GRASS, 0.5);
        effectiveness.get(GRASS).put(POISON, 0.5);
        effectiveness.get(GRASS).put(FLYING, 0.5);
        effectiveness.get(GRASS).put(BUG, 0.5);
        effectiveness.get(GRASS).put(DRAGON, 0.5);
        effectiveness.get(GRASS).put(STEEL, 0.5);

        effectiveness.get(ELECTRIC).put(ELECTRIC, 0.5);
        effectiveness.get(ELECTRIC).put(GRASS, 0.5);
        effectiveness.get(ELECTRIC).put(DRAGON, 0.5);

        // Immunité (x0.0)
        effectiveness.get(NORMAL).put(GHOST, 0.0);
        effectiveness.get(FIGHTING).put(GHOST, 0.0);
        effectiveness.get(ELECTRIC).put(GROUND, 0.0);
        effectiveness.get(POISON).put(STEEL, 0.0);
        effectiveness.get(GROUND).put(FLYING, 0.0);
        effectiveness.get(PSYCHIC).put(DARK, 0.0);
        effectiveness.get(GHOST).put(NORMAL, 0.0);
        effectiveness.get(DRAGON).put(FAIRY, 0.0);

    }

    public static double getMultiplier(Type moveType, Type[] targetTypes) {
        double multiplier = 1.0;
        for (Type targetType : targetTypes) {
            multiplier *= effectiveness.getOrDefault(moveType, new HashMap<>()).getOrDefault(targetType, 1.0);
        }
        return multiplier;
    }
}
