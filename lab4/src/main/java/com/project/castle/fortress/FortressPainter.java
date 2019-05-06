package com.project.castle.fortress;

import java.util.function.Consumer;
import javax.media.j3d.Group;
import javax.vecmath.AxisAngle4d;

public class FortressPainter extends Fortress {
    private static final String IMAGE = "lab4/src/main/resources/image.jpg";
    private static final float FORTRESS_HEIGHT = 0.125f;
    private static final float TOOTH_SIDE_LENGTH = 0.008f;

    public void buildFortresses(Consumer<Group> consumer) {

    }

    String getFortressPicture() {
        return IMAGE;
    }

    float getFortressHeigth() {
        return FORTRESS_HEIGHT;
    }

    public float getToothSideLength() {
        return TOOTH_SIDE_LENGTH;
    }

    protected AxisAngle4d getAngle() {
        return null;
    }
}