import lombok.Getter;
import processing.core.PVector;

import java.util.Random;

@Getter
public class AttractionLaw {

    private final float distanceOfInfluence;

    private final float attractionFactor;

    private final float repulsionFactor;

    private float m, b; // as parameters of slope-intercept form of equation of linear attraction law

    public AttractionLaw() {
        Random random = new Random();
        this.distanceOfInfluence = random.nextFloat(Constants.MIN_DISTANCE_OF_INFLUENCE, Constants.MAX_DISTANCE_OF_INFLUENCE);
        this.attractionFactor = random.nextFloat(0, Constants.MAX_FORCE_FACTOR);
        this.repulsionFactor = -random.nextFloat(0, Constants.MAX_FORCE_FACTOR);
        m = (attractionFactor - repulsionFactor) / distanceOfInfluence;
        b = (distanceOfInfluence * repulsionFactor) / distanceOfInfluence;
    }

    public void attractParticle(Particle affective, Particle affected) {
        if (affective != affected) {
            PVector sub = PVector.sub(affective.getPosition(), affected.getPosition());
            float distance = sub.mag();
            float forceMag = calculateAttractionFor(distance);
            PVector forceVector = sub.normalize().mult(forceMag);
            affected.applyForce(forceVector);
        }
    }

    public float calculateAttractionFor(float distance) {
        if (distance < distanceOfInfluence) {
            return m * distance + b;
        }
        return 0f;
    }

    public void update() {
        m += (new Random()).nextFloat() / 1000f - 0.0005f;
        b += (new Random()).nextFloat() / 1000f - 0.0005f;
    }

}
