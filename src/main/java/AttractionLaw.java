import lombok.Getter;
import processing.core.PVector;

import java.util.Random;

@Getter
public class AttractionLaw {

    private float distanceOfInfluence;

    private float velocityOfDistanceChange;

    private float attractionFactor;

    private float velocityOfAttractionChange;

    private  float repulsionFactor;

    private float velocityOfRepulsionChange;

    private float m, b; // as parameters of slope-intercept form of equation of linear attraction law

    public AttractionLaw() {
        Random random = new Random();
        distanceOfInfluence = random.nextFloat(Constants.MIN_DISTANCE_OF_INFLUENCE, Constants.MAX_DISTANCE_OF_INFLUENCE);
        velocityOfDistanceChange = random.nextFloat(
                Constants.MIN_OF_VELOCITY_OF_DISTANCE_CHANGE,
                Constants.MAX_OF_VELOCITY_OF_DISTANCE_CHANGE);
        if (random.nextBoolean()) velocityOfAttractionChange *= -1f;
        attractionFactor = random.nextFloat(0, Constants.MAX_FORCE_FACTOR);
        velocityOfAttractionChange = random.nextFloat(
                Constants.MIN_OF_VELOCITY_OF_FORCE_CHANGE,
                Constants.MAX_OF_VELOCITY_OF_FORCE_CHANGE);
        if (random.nextBoolean()) velocityOfAttractionChange *= -1f;
        repulsionFactor = -random.nextFloat(0, Constants.MAX_FORCE_FACTOR);
        velocityOfRepulsionChange = random.nextFloat(
                Constants.MIN_OF_VELOCITY_OF_FORCE_CHANGE,
                Constants.MAX_OF_VELOCITY_OF_FORCE_CHANGE);
        if (random.nextBoolean()) velocityOfRepulsionChange *= -1f;
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
        updateDistanceOfInfluence();
        updateAttractionForce();
        updateRepulsionForce();
        m = (attractionFactor - repulsionFactor) / distanceOfInfluence;
        b = (distanceOfInfluence * repulsionFactor) / distanceOfInfluence;
    }

    private void updateDistanceOfInfluence() {
        distanceOfInfluence += velocityOfDistanceChange;
        if (distanceOfInfluence < Constants.MIN_DISTANCE_OF_INFLUENCE && velocityOfDistanceChange < 0) {
            velocityOfDistanceChange = new Random().nextFloat(
                    Constants.MIN_OF_VELOCITY_OF_DISTANCE_CHANGE,
                    Constants.MAX_OF_VELOCITY_OF_DISTANCE_CHANGE);
        }
        if (distanceOfInfluence > Constants.MAX_DISTANCE_OF_INFLUENCE && velocityOfDistanceChange > 0) {
            velocityOfDistanceChange = -(new Random()).nextFloat(
                    Constants.MIN_OF_VELOCITY_OF_DISTANCE_CHANGE,
                    Constants.MAX_OF_VELOCITY_OF_DISTANCE_CHANGE);
        }
    }
    private void updateAttractionForce() {
        attractionFactor += velocityOfAttractionChange;
        if (attractionFactor < 0 && velocityOfAttractionChange < 0) {
            velocityOfAttractionChange = new Random().nextFloat(
                    Constants.MIN_OF_VELOCITY_OF_FORCE_CHANGE,
                    Constants.MAX_OF_VELOCITY_OF_FORCE_CHANGE);
        }
        if (attractionFactor > Constants.MAX_FORCE_FACTOR && velocityOfAttractionChange > 0) {
            velocityOfAttractionChange = -(new Random()).nextFloat(
                    Constants.MIN_OF_VELOCITY_OF_FORCE_CHANGE,
                    Constants.MAX_OF_VELOCITY_OF_FORCE_CHANGE);
        }
    }

    private void updateRepulsionForce() {
        repulsionFactor += velocityOfRepulsionChange;
        if (repulsionFactor < -Constants.MAX_FORCE_FACTOR && velocityOfRepulsionChange < 0f) {
            velocityOfRepulsionChange = new Random().nextFloat(
                    Constants.MIN_OF_VELOCITY_OF_FORCE_CHANGE,
                    Constants.MAX_OF_VELOCITY_OF_FORCE_CHANGE);
        }
        if (repulsionFactor > 0f && velocityOfRepulsionChange > 0) {
            velocityOfRepulsionChange = -(new Random()).nextFloat(
                    Constants.MIN_OF_VELOCITY_OF_FORCE_CHANGE,
                    Constants.MAX_OF_VELOCITY_OF_FORCE_CHANGE);
        }
    }

}
