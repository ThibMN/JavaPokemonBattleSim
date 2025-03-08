package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class CloseCombat extends Move {
    public CloseCombat() {
        super("CloseCombat", 120, Type.FIGHTING, true, 0, null, 100);
    }
}