package truss;

import lombok.Getter;
import lombok.SneakyThrows;
import processing.core.PVector;

import java.util.*;

public class Specimen {

    public static final int INITIAL_JOINTS_TOTAL = 10;

    private static final int INITIAL_BEAMS_TOTAL = 10;

    private final float load;

    private float fitness;

    @Getter
    private boolean simulationDone = false;

    private Map<Beam, JointsPair> graph;

    private List<Joint> joints;

    public Specimen(float load, float distance, Specimen ancestor) {
        this.load = load;
        if (ancestor == null) {
            createNewSpecimen();
            fixFreezeJoints();
        }

    }

    private void createNewSpecimen() {
        joints = createJoints();
        List<Beam> beams = createBeams();
        graph = new HashMap<>();
        beams.forEach(beam -> graph.put(beam, new JointsPair(
                joints.get(new Random().nextInt(joints.size())),
                joints.get(new Random().nextInt(joints.size())))));
    }

    private List<Joint> createJoints() {
        List<Joint> joints = new ArrayList<>();
        Joint loaded = new Joint();
        loaded.setLoaded(true);
        loaded.setLoadForce(new PVector(0f, load));
        joints.add(loaded);
        for (int i = 1; i < INITIAL_JOINTS_TOTAL; i++) {
            joints.add(new Joint());
        }
        return joints;
    }

    private List<Beam> createBeams() {
        List<Beam> beams = new ArrayList<>();
        for (int i = 0; i < INITIAL_BEAMS_TOTAL; i++) {
            beams.add(new Beam());
        }
        return beams;
    }

    private void fixFreezeJoints() {
        joints.stream()
                .filter(joint -> joint.getPosition().x < Application.FREEZE_DISTANCE)
                .forEach(joint -> joint.setFixed(true));
    }

    @SneakyThrows
    public void update() {
        if (!simulationDone) {
            graph.forEach(Beam::update);
            joints.forEach(Joint::update);
//            simulationDone = ifSimulationDone();
//            if (simulationDone) calculateFitness();
        }
    }

    public void draw() {
        Application.processing.noStroke();
        Application.processing.fill(0f, 0f, 0.125f);
        Application.processing.stroke(1f);
        graph.forEach(Beam::draw);
        Application.processing.noStroke();
        Application.processing.rect(0f, 0f, Application.FREEZE_DISTANCE,Application.processing.height);
        joints.forEach(Joint::draw);
    }

}
