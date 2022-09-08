import lombok.Getter;
import processing.core.PVector;

import java.util.Random;

@Getter
public class AttractionLaw {

    public static final float MIN_DISTANCE_OF_INFLUENCE = 10f;

    public static final float MAX_DISTANCE_OF_INFLUENCE = 150f;

    public static final float MAX_FORCE_FACTOR = 0.4f;

    private final float distanceOfInfluence;

    private final float attractionFactor;

    private final float repulsionFactor;

    private final float m, b; // as parameters of slope-intercept form of equation of linear attraction law

    public AttractionLaw() {
        Random random = new Random();
        this.distanceOfInfluence = random.nextFloat(MIN_DISTANCE_OF_INFLUENCE, MAX_DISTANCE_OF_INFLUENCE);
        this.attractionFactor = random.nextFloat(-MAX_FORCE_FACTOR,MAX_FORCE_FACTOR);
        this.repulsionFactor = -random.nextFloat(-MAX_FORCE_FACTOR,MAX_FORCE_FACTOR);
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

}
