package truss;

import processing.core.PVector;

import java.util.Random;

public class Beam {

    public static final float MIN_NORMAL_LENGTH = 25f;

    public static final float MAX_NORMAL_LENGTH = 75f;

    private static final float STIFFNESS = 0.005f;

    private final float normalLength;
private float currentLength;

    public Beam() {
        this.normalLength = new Random().nextFloat(MIN_NORMAL_LENGTH, MAX_NORMAL_LENGTH);
    }

    public void update(JointsPair jointsPair) {
        Joint first = jointsPair.getFirst();
        Joint second = jointsPair.getSecond();
        PVector beamVector = PVector.sub(second.getPosition(), first.getPosition());
         currentLength = beamVector.mag();
        float lengthDelta = currentLength - normalLength;
                float forceMagnitude=lengthDelta*STIFFNESS;
        PVector force = beamVector.copy().normalize().mult(forceMagnitude);
        first.applyForce(force);
        second.applyForce(force.mult(-1f));
    }

    public void draw(JointsPair jointsPair) {
        Joint first = jointsPair.getFirst();
        Joint second = jointsPair.getSecond();
        Application.processing.line(
                first.getPosition().x,
                first.getPosition().y,
                second.getPosition().x,
                second.getPosition().y);
    }

}
