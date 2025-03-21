package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Snorlax extends Pokemon implements Serializable {
    public Snorlax() {
        super("Snorlax", 160, 110, 65, 65, 110, 30, new Type[]{Type.NORMAL},
                Arrays.asList(
                        new Move("Body Slam", 85, Type.NORMAL, true, 0, null, 100),
                        new Move("Hyper Beam", 150, Type.NORMAL, false, 0, null, 90),
                        new Move("Earthquake", 100, Type.GROUND, true, 0, null, 100),
                        new Move("Crunch", 80, Type.DARK, true, 0, null, 100)
                ));
    }
}