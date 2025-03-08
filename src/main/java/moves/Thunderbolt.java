package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class Thunderbolt extends Move {
    public Thunderbolt() {
        super("Thunderbolt", 90, Type.ELECTRIC, false, 0.1, StatusEffect.PARALYSIS);
    }
}