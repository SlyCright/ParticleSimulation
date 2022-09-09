
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Application extends PApplet {

    public static PApplet processing;

    private final List<Molecule> molecules = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("Application", args);
    }

    public void settings() {
        //fullScreen();
    }

    public void setup() {
        noStroke();
        processing = this;
        colorMode(HSB, 1f);
        List<Element> elements = createElements();
        Iterator<Element> elementIterator = elements.iterator();
        for (int i = 0; i < Constants.ATOMS_TOTAL; i++) {
            if (!elementIterator.hasNext()) elementIterator = elements.iterator();
            molecules.add(new Molecule(new Atom(elementIterator.next())));
        }
    }

    private List<Element> createElements() {
        ArrayList<Element> elements = new ArrayList<>();
        for (int i = 0; i < Constants.ELEMENTS_TOTAL; i++) {
            float hue = (float) i / (float) Constants.ELEMENTS_TOTAL;
            Element element = new Element(i, hue);
            elements.add(element);
        }
        elements.forEach(affecting -> elements.forEach(affecting::generateAttractionLawFor));
        return elements;
    }

    public void draw() {
        background(0);
        molecules.forEach(affecting -> molecules.forEach(affecting::affect));
        molecules.forEach(Molecule::update);
        molecules.forEach(molecule -> {
            Set<Atom> atoms = molecule.getAtoms();
            atoms.forEach(atom -> {
                fill(atom.getElement().getHue(), 1f, 1f);
                circle(atom.getPosition().x, atom.getPosition().y, 5f);
            });
        });
    }

}
