package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.util.Arrays;

public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", 45, 49, 65, 49, 65, 45, new Type[]{Type.GRASS, Type.POISON},
                Arrays.asList(
                        new Move("Vine Whip", 45, Type.GRASS, true, 0, null),
                        new Move("Razor Leaf", 55, Type.GRASS, true, 0, null),
                        new Move("Solar Beam", 120, Type.GRASS, false, 0, null),
                        new Move("Sludge Bomb", 90, Type.POISON, false, 0, null)
                ));
    }
}