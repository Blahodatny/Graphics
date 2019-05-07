package com.project.castle.fortress;

import com.project.FLAGS;
import com.project.TransformGroupFactory;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearance;

public class FortressPainter extends TransformGroupFactory {
    private static final String IMAGE = "lab4/src/main/resources/image.jpg";
    private static final float FORTRESS_HEIGHT = 0.125f;
    private static final float FORTRESS_SIDE_LENGTH = 0.2f;
    private final static float[][] COORDINATES = new float[][]{
            {0.4f, 0.4f, 0.5f},
            {-0.4f, 0.4f, 0.5f},
            {0.8f, 0.8f, 0.25f},
            {-0.8f, 0.8f, 0.25f}
    };

    public float[][] getVectorCoordinates() { return COORDINATES; }

    public TransformGroup getBuildCallback(float x, float y, float z,
            boolean rotate) {
        return buildGroup(
                new Vector3f(x, y, z),
                new Box(
                        FORTRESS_SIDE_LENGTH,
                        FORTRESS_SIDE_LENGTH,
                        FORTRESS_HEIGHT,
                        FLAGS.get(),
                        getAppearance(IMAGE)
                ),
                false
        );
    }
}