import lombok.Getter;

import java.util.HashMap;

@Getter
public class Element {

    private final int id;

    private final float hue;

    private final HashMap<Integer, AttractionLaw> attractionLaws = new HashMap<>();

    public Element(int id, float hue) {
        this.id = id;
        this.hue = hue;
    }

    public void generateAttractionLawFor(Element affected) {
        int affectedId = affected.getId();
        this.attractionLaws.put(affectedId, new AttractionLaw());
    }

    public void affectWithLaw(Atom affecting, Atom affected) {
        int affectingElementId = affecting.getElement().getId();
        AttractionLaw attractionLaw = this.attractionLaws.get(affectingElementId);
        attractionLaw.attractParticle(affecting, affected);
    }

    public static void update(Molecule molecule) {

    }

}
