package com.project.castle.edge;

import java.util.function.Consumer;
import javax.media.j3d.Group;
import javax.vecmath.AxisAngle4d;

public class FortressEdgesPainter extends Edge {
    private static final float TOOTH_SIDE_LENGTH = 0.008f;
    private static final AxisAngle4d ANGLE =
            new AxisAngle4d(0, 0, 1, Math.toRadians(90));
    // distance from center
    private final static float DIST = 0.192f;
    private final static float Z_POSITION = 0.63f;

    // 0.f DIST
    // -DIST -0.f true
    // -0.f -DIST
    // DIST 0.f true
    public void buildFortressEdges(Consumer<Group> consumer) {
        for (var i = 0; i < 4; i++) {
            var even = i % 2 == 0;
            var odd = i % 3 == 0;
            consumer.accept(buildEdge(
                    (even ? .0f : DIST) * (odd ? 1 : -1),
                    (even ? DIST : .0f) * (odd ? 1 : -1),
                    Z_POSITION,
                    !even
            ));
        }
    }

    float getToothSideLength() {
        return TOOTH_SIDE_LENGTH;
    }

    protected AxisAngle4d getAngle() {
        return ANGLE;
    }
}