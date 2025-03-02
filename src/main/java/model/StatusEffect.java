package model;

public enum StatusEffect {
    PARALYSIS {
        @Override
        public void applyEffect(Pokemon pokemon) {
            // Paralysis: 25% chance to skip turn + Speed is halved
            pokemon.setSpeed(pokemon.getSpeed() / 2);
            System.out.println(pokemon.getName() + " is paralyzed! It may not be able to move.");
        }

        @Override
        public boolean preventsAttack() {
            return Math.random() < 0.25;
        }
    },

    POISON {
        @Override
        public void applyEffect(Pokemon pokemon) {
            // Poison: Loses 1/8 of max HP per turn
            int damage = pokemon.getHp() / 8;
            pokemon.takeDamage(damage);
            System.out.println(pokemon.getName() + " is poisoned! It loses " + damage + " HP.");
        }

        @Override
        public boolean preventsAttack() {
            return false;
        }
    },

    BURN {
        @Override
        public void applyEffect(Pokemon pokemon) {
            // Burn: Loses 1/16 of max HP per turn + Halves physical attack
            int damage = pokemon.getHp() / 16;
            pokemon.takeDamage(damage);
            pokemon.setAttack(pokemon.getAttack() / 2);
            System.out.println(pokemon.getName() + " is burned! Its physical attack is weakened.");
        }

        @Override
        public boolean preventsAttack() {
            return false;
        }
    };

    /**
     * Applies the effect on the Pokémon.
     * @param pokemon The affected Pokémon.
     */
    public abstract void applyEffect(Pokemon pokemon);

    /**
     * Determines if the Pokémon is unable to attack this turn.
     * @return True if the Pokémon cannot attack, false otherwise.
     */
    public abstract boolean preventsAttack();
}
