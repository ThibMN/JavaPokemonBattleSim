package pokemons;

import models.Pokemon;
import models.StatusEffect;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Charmander extends Pokemon implements Serializable {
    public Charmander() {
        super("Charmander", 39, 52, 60, 43, 50, 65, new Type[]{Type.FIRE},
                Arrays.asList(
                        new Move("Flamethrower", 90, Type.FIRE, false, 0.3, StatusEffect.BURN, 90),
                        new Move("Dragon Claw", 80, Type.DRAGON, true, 0, null, 100),
                        new Move("Fire Spin", 35, Type.FIRE, false, 0, null, 80),
                        new Move("Slash", 70, Type.NORMAL, true, 0, null, 100)
                ));
    }
}