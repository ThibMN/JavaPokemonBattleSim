package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Charmander extends Pokemon implements Serializable {
    public Charmander() {
        super("Charmander", 39, 52, 60, 43, 50, 65, new Type[]{Type.FIRE},
                Arrays.asList(
                        new Move("Flamethrower", 90, Type.FIRE, false, 0, null),
                        new Move("Dragon Claw", 80, Type.DRAGON, true, 0, null),
                        new Move("Fire Spin", 35, Type.FIRE, false, 0, null),
                        new Move("Slash", 70, Type.NORMAL, true, 0, null)
                ));
    }
}