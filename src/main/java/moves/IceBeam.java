package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class IceBeam extends Move {
    public IceBeam() {
        super("IceBeam", 90, Type.ICE, false, 0, null, 100);
    }
}