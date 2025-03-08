package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.util.Arrays;

public class Snorlax extends Pokemon {
    public Snorlax() {
        super("Snorlax", 160, 110, 65, 65, 110, 30, new Type[]{Type.NORMAL},
                Arrays.asList(
                        new Move("Body Slam", 85, Type.NORMAL, true, 0, null),
                        new Move("Hyper Beam", 150, Type.NORMAL, false, 0, null),
                        new Move("Earthquake", 100, Type.GROUND, true, 0, null),
                        new Move("Crunch", 80, Type.DARK, true, 0, null)
                ));
    }
}