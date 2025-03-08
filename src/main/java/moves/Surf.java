package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class Surf extends Move {
    public Surf() {
        super("Surf", 90, Type.WATER, false, 0, null);
    }
}