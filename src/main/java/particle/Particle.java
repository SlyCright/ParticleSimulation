package particle;

import lombok.Getter;
import processing.core.PVector;

import java.util.Random;

@Getter
public class Particle {

    private final PVector acceleration = new PVector();

    private final PVector velocity = new PVector();

    private final PVector position;

    public Particle() {
        position = new PVector(
                new Random().nextFloat(Application.processing.width),
                new Random().nextFloat(Application.processing.height));
    }

    public void applyForce(PVector force) {
        acceleration.add(force);
    }

    public void update() {
        velocity.add(acceleration);
        velocity.mult(Constants.FRICTION_FACTOR);
        position.add(velocity);
        if (position.x < 0 && velocity.x < 0) {
            velocity.x *= -1;
            position.x = 0;
        }
        if (position.x > Application.processing.width && velocity.x > 0) {
            velocity.x *= -1;
            position.x = Application.processing.width;
        }
        if (position.y < 0 && velocity.y < 0) {
            velocity.y *= -1;
            position.y = 0;
        }
        if (position.y > Application.processing.height && velocity.y > 0) {
            velocity.y *= -1;
            position.y = Application.processing.height;
        }
        acceleration.set(0f, 0f);
    }

}
