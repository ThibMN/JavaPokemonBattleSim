package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class Fly extends Move {
    public Fly() {
        super("Fly", 90, Type.FLYING, true, 0, null, 95);
    }
}