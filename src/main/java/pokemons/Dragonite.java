package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.util.Arrays;

public class Dragonite extends Pokemon {
    public Dragonite() {
        super("Dragonite", 91, 134, 100, 95, 100, 80, new Type[]{Type.DRAGON, Type.FLYING},
                Arrays.asList(
                        new Move("Dragon Claw", 80, Type.DRAGON, true, 0, null),
                        new Move("Hurricane", 110, Type.FLYING, false, 0, null),
                        new Move("Thunder Punch", 75, Type.ELECTRIC, true, 0, null),
                        new Move("Hyper Beam", 150, Type.NORMAL, false, 0, null)
                ));
    }
}