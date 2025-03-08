package pokemons;

import models.Pokemon;
import models.Type;
import models.Move;

import java.io.Serializable;
import java.util.Arrays;

public class Pikachu extends Pokemon implements Serializable {
    public Pikachu() {
        super("Pikachu", 35, 55, 50, 40, 50, 90, new Type[]{Type.ELECTRIC},
                Arrays.asList(
                        new Move("Thunderbolt", 90, Type.ELECTRIC, false, 0, null),
                        new Move("Quick Attack", 40, Type.NORMAL, true, 0, null),
                        new Move("Iron Tail", 100, Type.STEEL, true, 0, null),
                        new Move("Electro Ball", 60, Type.ELECTRIC, false, 0, null)
                ));
    }
}