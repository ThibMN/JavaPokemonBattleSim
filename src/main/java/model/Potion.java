package model;

public class Potion implements ItemEffect {
    private int healAmount;

    public Potion(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void apply(Pokemon pokemon) {
        pokemon.setHp(Math.min(pokemon.getHp() + healAmount, pokemon.getMaxHp()));
        System.out.println(pokemon.getName() + " recovered " + healAmount + " HP!");
    }
}
