package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class Earthquake extends Move {
    public Earthquake() {
        super("Earthquake", 100, Type.GROUND, true, 0, null);
    }
}