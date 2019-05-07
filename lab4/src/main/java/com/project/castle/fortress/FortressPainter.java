package com.project.castle.fortress;

import com.project.castle.TransformGroupFactory;
import java.util.function.Consumer;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;

public class FortressPainter extends TransformGroupFactory {
    private static final String IMAGE = "lab4/src/main/resources/image.jpg";
    private static final float FORTRESS_HEIGHT = 0.125f;

    public void buildFortresses(Consumer<Group> consumer) {

    }

    public float[][] getVectorCoordinates() {
        return new float[0][];
    }

    public TransformGroup getBuildCallback(float x, float y, float z,
            boolean rotate) {
        return null;
    }
}