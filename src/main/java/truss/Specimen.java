package truss;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.*;

public class Specimen {

    public static final int INITIAL_JOINTS_TOTAL = 10;

    private static final int INITIAL_BEAMS_TOTAL = 10;

    private final float load;

    private final float distance;

    private int counter;

    @Getter
    private boolean simulationDone = false;

    private Map<Beam, JointsPair> graph;

    private List<Joint> joints;

    public Specimen(float load, float distance, Specimen ancestor) {
        this.load = load;
        this.distance = distance;
        if (ancestor == null) createNewSpecimen();
    }

    private void createNewSpecimen() {
        joints = createJoints();
        List<Beam> beams = createBeams();
        graph = new HashMap<>();
        beams.forEach(beam -> graph.put(beam, new JointsPair(
                joints.get(new Random().nextInt(joints.size())),
                joints.get(new Random().nextInt(joints.size())))));
    }

    private List<Beam> createBeams() {
        List<Beam> beams = new ArrayList<>();
        for (int i = 0; i < INITIAL_BEAMS_TOTAL; i++) {
            beams.add(new Beam());
        }
        return beams;
    }

    private List<Joint> createJoints() {
        List<Joint> joints = new ArrayList<>();
        for (int i = 0; i < INITIAL_JOINTS_TOTAL; i++) {
            joints.add(new Joint());
        }
        return joints;
    }

    @SneakyThrows
    public void update() {
        graph.forEach(Beam::update);
        joints.forEach(Joint::update);
    }

    public void draw() {
        graph.forEach(Beam::draw);
        joints.forEach(Joint::draw);
    }

}
