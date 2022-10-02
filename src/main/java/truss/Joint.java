package truss;

import lombok.Getter;
import lombok.Setter;
import processing.core.PVector;

import javax.swing.tree.FixedHeightLayoutCache;
import java.lang.reflect.Field;
import java.util.Random;

public class Joint {

    public static final float FRICTION_FACTOR = 0.95f;

    private final PVector acceleration = new PVector();

    private final PVector velocity = new PVector();

    @Setter
    @Getter
    private boolean isFixed = false;

    @Setter
    private boolean isLoaded = false;

    @Getter
    private PVector position;

    @Setter
    private PVector loadForce;

    public Joint() {
        position = new PVector(
                new Random().nextFloat(Application.processing.width),
                new Random().nextFloat(Application.processing.height));
    }

    public void draw() {
        Application.processing.fill(isFixed ? 0.25f : 1f);
        if (isLoaded) Application.processing.fill(0f, 1f, 1f);
        Application.processing.circle(position.x, position.y, 15f);
    }

    public void update() {
        if (isFixed) return;
        if (isLoaded) this.applyForce(loadForce);
        velocity.add(acceleration);
        velocity.mult(FRICTION_FACTOR);
        position.add(velocity);
        acceleration.set(0f, 0f);
    }

    public void applyForce(PVector force) {
        acceleration.add(force);
    }

}
