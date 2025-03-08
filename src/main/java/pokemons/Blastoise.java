package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Blastoise extends Pokemon implements Serializable {
    public Blastoise() {
        super("Blastoise", 79, 83, 85, 100, 105, 78, new Type[]{Type.WATER},
            Arrays.asList(
                new Move("Hydro Pump", 110, Type.WATER, false, 0, null, 80),
                new Move("Ice Beam", 90, Type.ICE, false, 0, null, 100),
                new Move("Bite", 60, Type.DARK, true, 0, null, 100),
                new Move("Surf", 90, Type.WATER, false, 0, null, 90)
            )
        );
    }
}