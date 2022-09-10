import processing.core.PApplet;

import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;

public class Application extends PApplet {

    public static PApplet processing;

    List<Type> types = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("Application", args);
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        processing = this;
        for (int i = 0; i < Constants.TYPES_TOTAL; i++) {
            float hue = (float) i / (float) Constants.TYPES_TOTAL;
            Type type = new Type(i, hue);
            type.generateParticles((int) ((float) Constants.PARTICLES_TOTAL / (float) Constants.TYPES_TOTAL));
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
            particles.forEach(particle -> {
                circle(particle.getPosition().x, particle.getPosition().y, 5f);
            });
        });
        types.forEach(type -> type.getAttractionLaws().values().forEach(AttractionLaw::update));
    }

}
