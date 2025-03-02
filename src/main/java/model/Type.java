package model;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    FIRE, WATER, GRASS, ELECTRIC, GROUND, FLYING, ROCK, PSYCHIC;

    // Type effectiveness chart
    private static final Map<Type, Map<Type, Double>> effectiveness = new HashMap<>();

    static {
        for (Type type : Type.values()) {
            effectiveness.put(type, new HashMap<>());
        }

        // Define effectiveness (example values based on Pokémon logic)
        effectiveness.get(FIRE).put(GRASS, 2.0);
        effectiveness.get(FIRE).put(WATER, 0.5);
        effectiveness.get(WATER).put(FIRE, 2.0);
        effectiveness.get(WATER).put(GRASS, 0.5);
        effectiveness.get(GRASS).put(WATER, 2.0);
        effectiveness.get(GRASS).put(FIRE, 0.5);
        effectiveness.get(ELECTRIC).put(WATER, 2.0);
        effectiveness.get(ELECTRIC).put(GROUND, 0.0); // Electric doesn't affect Ground
        effectiveness.get(GROUND).put(ELECTRIC, 2.0);
        effectiveness.get(FLYING).put(GROUND, 0.0); // Flying is immune to Ground
        effectiveness.get(ROCK).put(FLYING, 2.0);
        effectiveness.get(PSYCHIC).put(ROCK, 1.0); // Example: Normal effectiveness
    }

    /**
     * Get the damage multiplier when a move of a given type hits a target of a certain type.
     *
     * @param moveType  The type of the attacking move.
     * @param targetTypes The types of the Pokémon being attacked.
     * @return The multiplier to apply to damage.
     */
    public static double getMultiplier(Type moveType, Type[] targetTypes) {
        double multiplier = 1.0;
        for (Type targetType : targetTypes) {
            multiplier *= effectiveness.getOrDefault(moveType, new HashMap<>()).getOrDefault(targetType, 1.0);
        }
        return multiplier;
    }
}
