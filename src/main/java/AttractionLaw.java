public class AttractionLaw {

    private final float distanceOfInfluence;

    private final float attractionFactor;

    private final float repulsionFactor;

    private float m,b; // as parameters of slope-intercept form of equation of linear attraction law

    public AttractionLaw(float distanceOfInfluence, float attractionFactor, float repulsionFactor) {
        this.distanceOfInfluence = distanceOfInfluence;
        this.attractionFactor = attractionFactor;
        this.repulsionFactor = repulsionFactor;
        m=(attractionFactor-repulsionFactor)/distanceOfInfluence;
        b=()/distanceOfInfluence
    }

    private float calculateAttractionFor(float distance){
        return
    }

}
