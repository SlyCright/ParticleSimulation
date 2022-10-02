package truss;

import lombok.SneakyThrows;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

public class Application extends PApplet {

    public static final float GRAVITY_FORCE=0.01f;
    public static final float FREEZE_DISTANCE_RATIO = 0.25f;

    public static float FREEZE_DISTANCE;

    private static final int POPULATION_SIZE = 100;

    public static PApplet processing;

    private List<Specimen> species = new ArrayList<>();

    private Specimen showSpecimen;

    public static void main(String[] args) {
        PApplet.main("truss.Application", args);
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        processing = this;
        colorMode(HSB, 1f);
        FREEZE_DISTANCE = processing.width * FREEZE_DISTANCE_RATIO;
        species = makeNextGeneration(0.1f, 20f);
        showSpecimen = species.get(0);
        stroke(1f);
        fill(1f);
    }

    private List<Specimen> makeNextGeneration(float load, float distance) {
        List<Specimen> species = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            species.add(new Specimen(load, distance, null));
        }
        return species;
    }

    public void draw() {
        showSpecimen.update();
        background(0f);
        showSpecimen.draw();
    }

    private boolean simulationIsDone() {
        for (Specimen specimen : species) {
            if (!specimen.isSimulationDone()) return false;
        }
        return true;
    }

}
