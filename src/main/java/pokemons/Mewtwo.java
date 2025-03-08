package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.util.Arrays;

public class Mewtwo extends Pokemon {
    public Mewtwo() {
        super("Mewtwo", 106, 110, 154, 90, 90, 130, new Type[]{Type.PSYCHIC},
                Arrays.asList(
                        new Move("Psychic", 90, Type.PSYCHIC, false, 0, null),
                        new Move("Shadow Ball", 80, Type.GHOST, false, 0, null),
                        new Move("Aura Sphere", 80, Type.FIGHTING, false, 0, null),
                        new Move("Ice Beam", 90, Type.ICE, false, 0, null)
                ));
    }
}