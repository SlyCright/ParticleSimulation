import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttractionLawTest {

    private final AttractionLaw attractionLaw = new AttractionLaw();

    @Test
    public void testCalculateAttractionFor() {
        float distance = 0f;
        float attraction = attractionLaw.calculateAttractionFor(distance);
        System.out.println("attraction = " + attraction);
        distance = attractionLaw.getDistanceOfInfluence();
        attraction = attractionLaw.calculateAttractionFor(distance);
        System.out.println("attraction = " + attraction);
        distance /= 2;
        attraction = attractionLaw.calculateAttractionFor(distance);
        System.out.println("attraction = " + attraction);
    }

}