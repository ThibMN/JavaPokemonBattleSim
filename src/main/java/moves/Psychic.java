package moves;

import models.Move;
import models.Type;
import models.StatusEffect;

public class Psychic extends Move {
    public Psychic() {
        super("Psychic", 90, Type.PSYCHIC, false, 0, null, 100);
    }
}