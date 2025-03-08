package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Mewtwo extends Pokemon implements Serializable {
    public Mewtwo() {
        super("Mewtwo", 106, 110, 154, 90, 90, 130, new Type[]{Type.PSYCHIC},
                Arrays.asList(
                        new Move("Psychic", 90, Type.PSYCHIC, false, 0, null, 100),
                        new Move("Shadow Ball", 80, Type.GHOST, false, 0, null, 100),
                        new Move("Aura Sphere", 80, Type.FIGHTING, false, 0, null, 100),
                        new Move("Ice Beam", 90, Type.ICE, false, 0, null, 100)
                ));
    }
}