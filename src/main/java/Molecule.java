import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Molecule {

    private final HashMap<Atom, List<Atom>> bonds = new HashMap<>();

    public Molecule(Atom atom) {
        bonds.put(atom, new ArrayList<>());
    }

    public void affect(Molecule molecule) {
        this.getAtoms().forEach(affecting -> molecule.getAtoms().forEach(affecting::affect));
    }

    public void update() {
    }

    public Set<Atom> getAtoms() {
        return bonds.keySet();
    }

}
