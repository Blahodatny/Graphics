package com.project.castle.tower;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.media.j3d.Node;
import javax.vecmath.AxisAngle4d;

public class TowerPainter extends Tower {
    private final static AxisAngle4d ANGLE =
            new AxisAngle4d(1, 0, 0, Math.toRadians(90));
    private final static float CYLINDER_RADIUS = 0.1f;
    private final static float CONE_HEIGHT = 0.3f;
    private final static float[] CYLINDER_HEIGHTS =
            new float[]{1.5f, 1.1f, 1.05f, 0.9f, 1.0f};
    // distance from center
    private final static float DIST = 0.2f;

    public void run(Consumer<Node> consumer) {
        IntStream.range(0, CYLINDER_HEIGHTS.length)
                .forEach(i -> consumer.accept(build(
                        i == 0 ? .0f : DIST * (i % 2 == 0 ? 1 : -1),
                        i == 0 ? .0f : DIST * (i % 3 == 1 ? 1 : -1),
                        CYLINDER_HEIGHTS[i]
                )));
    }

    protected AxisAngle4d getAngle() {
        return ANGLE;
    }

    float getCylinderRadius() {
        return CYLINDER_RADIUS;
    }

    float getConeHeight() {
        return CONE_HEIGHT;
    }
}