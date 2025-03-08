package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class PoisonJab extends Move {
    public PoisonJab() {
        super("PoisonJab", 80, Type.POISON, true, 0.3, StatusEffect.POISON);
    }
}