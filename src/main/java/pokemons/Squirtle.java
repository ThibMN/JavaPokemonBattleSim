package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.util.Arrays;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", 44, 48, 50, 65, 64, 43, new Type[]{Type.WATER},
                Arrays.asList(
                        new Move("Water Gun", 40, Type.WATER, false, 0, null),
                        new Move("Bubble Beam", 65, Type.WATER, false, 0, null),
                        new Move("Bite", 60, Type.DARK, true, 0, null),
                        new Move("Hydro Pump", 110, Type.WATER, false, 0, null)
                ));
    }
}