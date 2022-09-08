import processing.core.PApplet;

import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;

public class Application extends PApplet {

    public static final int TYPES_TOTAL = 3;

    public static final int PARTICLES_TOTAL = 600;

    public static PApplet processing;

    AttractionLaw attractionLaw;

    List<Type> types = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("Application", args);
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        processing = this;
        for (int i = 0; i < TYPES_TOTAL; i++) {
            float hue = (float) i / (float) TYPES_TOTAL;
            Type type = new Type(i, hue);
            type.generateParticles((int) ((float) PARTICLES_TOTAL / (float) TYPES_TOTAL));
            types.add(type);
        }
        types.forEach(affective -> types.forEach(affective::generateAttractionLawFor));
        colorMode(HSB, 1f);
        noStroke();
    }

    public void draw() {
        background(0);
        types.forEach(affective -> types.forEach(affective::affectType));
        types.forEach(Type::update);
        types.forEach(type -> {
            fill(type.getHue(), 1f, 1f);
            List<Particle> particles = type.getParticles();
            particles.forEach(particle -> circle(
                    particle.getPosition().x,
                    particle.getPosition().y,
                    5f));
        });
    }

}
