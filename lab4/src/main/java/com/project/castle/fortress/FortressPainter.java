package com.project.castle.fortress;

import java.util.function.Consumer;
import javax.media.j3d.Group;
import javax.vecmath.AxisAngle4d;

public class FortressPainter extends Fortress {
    private static final String IMAGE = "lab4/src/main/resources/image.jpg";
    private static final float FORTRESS_HEIGHT = 0.125f;

    public void buildFortresses(Consumer<Group> consumer) {

    }

    String getFortressPicture() { return IMAGE; }

    float getFortressHeight() { return FORTRESS_HEIGHT; }

    protected AxisAngle4d getAngle() { return null; }
}