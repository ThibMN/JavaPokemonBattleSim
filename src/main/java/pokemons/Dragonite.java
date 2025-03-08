package pokemons;

import models.Pokemon;
import models.StatusEffect;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Dragonite extends Pokemon implements Serializable {
    public Dragonite() {
        super("Dragonite", 91, 134, 100, 95, 100, 80, new Type[]{Type.DRAGON, Type.FLYING},
                Arrays.asList(
                        new Move("Dragon Claw", 80, Type.DRAGON, true, 0, null, 100),
                        new Move("Hurricane", 110, Type.FLYING, false, 0, null, 70),
                        new Move("Thunder Punch", 75, Type.ELECTRIC, true, 0.1, StatusEffect.PARALYSIS, 100),
                        new Move("Hyper Beam", 150, Type.NORMAL, false, 0, null, 90)
                ));
    }
}