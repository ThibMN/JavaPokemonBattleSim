package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class SolarBeam extends Move {
    public SolarBeam() {
        super("SolarBeam", 120, Type.GRASS, false, 0, null, 100);
    }
}