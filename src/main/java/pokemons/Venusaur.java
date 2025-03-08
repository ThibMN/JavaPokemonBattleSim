package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Venusaur extends Pokemon implements Serializable {
    public Venusaur() {
        super("Venusaur", 80, 82, 100, 83, 100, 80, new Type[]{Type.GRASS, Type.POISON},
                Arrays.asList(
                        new Move("Solar Beam", 120, Type.GRASS, false, 0, null),
                        new Move("Sludge Bomb", 90, Type.POISON, false, 0, null),
                        new Move("Earthquake", 100, Type.GROUND, true, 0, null),
                        new Move("Giga Drain", 75, Type.GRASS, false, 0, null)
                ));
    }
}