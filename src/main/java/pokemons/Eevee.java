package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Eevee extends Pokemon implements Serializable {
    public Eevee() {
        super("Eevee", 55, 55, 45, 50, 65, 55, new Type[]{Type.NORMAL},
                Arrays.asList(
                        new Move("Quick Attack", 40, Type.NORMAL, true, 0, null),
                        new Move("Bite", 60, Type.DARK, true, 0, null),
                        new Move("Swift", 60, Type.NORMAL, false, 0, null),
                        new Move("Take Down", 90, Type.NORMAL, true, 0, null)
                ));
    }
}