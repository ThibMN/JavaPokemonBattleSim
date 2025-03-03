package models;

public class Antidote implements ItemEffect {
    @Override
    public void apply(Pokemon pokemon) {
        pokemon.cureStatus();
    }
}
