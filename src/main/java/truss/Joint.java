package truss;

import lombok.Getter;
import processing.core.PVector;

import java.util.Random;

public class Joint {

    public static final float FRICTION_FACTOR = 0.95f;

    private final PVector acceleration = new PVector();

    private final PVector velocity = new PVector();
@Getter
    private final PVector position;

    public Joint() {
        position = new PVector(
                new Random().nextFloat(Application.processing.width),
                new Random().nextFloat(Application.processing.height));
    }

    public void draw() {
        Application.processing.circle(position.x,position.y,5f);
    }

    public void update() {
        velocity.add(acceleration);
        velocity.mult(FRICTION_FACTOR);
        position.add(velocity);
        acceleration.set(0f, 0f);
    }

    public void applyForce(PVector force) {
        acceleration.add(force);
    }

}
