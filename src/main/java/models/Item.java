package models;

public class Item {
    private String name;
    private ItemEffect effect;

    public Item(String name, ItemEffect effect) {
        this.name = name;
        this.effect = effect;
    }

    public void use(Pokemon pokemon) {
        effect.apply(pokemon);
        System.out.println(pokemon.getName() + " used " + name + "!");
    }

    public String getName() { return name; }
}
