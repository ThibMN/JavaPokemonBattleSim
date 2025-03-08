package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class Flamethrower extends Move {
    public Flamethrower() {
        super("Flamethrower", 90, Type.FIRE, false, 0.1, StatusEffect.BURN);
    }
}