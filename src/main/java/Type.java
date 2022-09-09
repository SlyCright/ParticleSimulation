import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class Type {

    private final int id;

    private final float hue;

    private final List<Atom> atoms = new ArrayList<>();

    private final HashMap<Integer, AttractionLaw> attractionLaws = new HashMap<>();

    public Type(int id, float hue) {
        this.id = id;
        this.hue = hue;
    }

    public void generateAttractionLawFor(Type affected) {
        int affectedId = affected.getId();
        this.attractionLaws.put(affectedId, new AttractionLaw());
    }

    public void generateParticles(int total) {
        for (int i = 0; i < total; i++) {
            atoms.add(new Atom());
        }
    }

    public void affectType(Type affectedType) {
        int affectedId = affectedType.getId();
        AttractionLaw attractionLaw = this.attractionLaws.get(affectedId);
        this.atoms.forEach(
                affective -> affectedType.getAtoms().forEach(
                        affected -> attractionLaw.attractParticle(affective, affected)));
    }

    public void update() {
        atoms.forEach(Atom::update);
    }

}
