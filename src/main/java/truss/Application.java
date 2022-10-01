package truss;

import lombok.SneakyThrows;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

public class Application extends PApplet {

    private static final int POPULATION_SIZE = 100;

    public static PApplet processing;

    private List<Specimen> species = new ArrayList<>();

    private Specimen showSpecimen;

    private List<Integer> drawTimes = new LinkedList<>();

    private float screenRatio;

    public static void main(String[] args) {
        PApplet.main("truss.Application", args);
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        processing = this;
        colorMode(HSB, 1f);
        screenRatio = (float) processing.width / (float) processing.height;
        species = makeNextGeneration(0f, 0f);
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
        background(0f);
        showSpecimen.update();
        showSpecimen.draw();
    }

    private boolean simulationIsDone() {
        for (Specimen specimen : species) {
            if (!specimen.isSimulationDone()) return false;
        }
        return true;
    }

}
