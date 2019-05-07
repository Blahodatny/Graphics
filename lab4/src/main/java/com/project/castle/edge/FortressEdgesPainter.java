package com.project.castle.edge;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;

public class FortressEdgesPainter extends Edge {
    private static final float TOOTH_SIDE_LENGTH = 0.008f;
    private static final AxisAngle4d ANGLE =
            new AxisAngle4d(0, 0, 1, Math.toRadians(90));
    // distance from center
    private final static float DIST = 0.192f;
    private final static float Z_POSITION = 0.63f;
    private final static float[][] COORDINATES = new float[][]{
            {0.4f, .0f},
            {0.8f, -0.25f}
    };

    // 0.f DIST
    // -DIST -0.f true
    // -0.f -DIST
    // DIST 0.f true
    private TransformGroup buildFortressEdges() {
        var group = new TransformGroup();
        IntStream.range(0, 4).forEach(i -> {
            var even = i % 2 == 0;
            var odd = i % 3 == 0;
            group.addChild(buildEdge(
                    (even ? .0f : DIST) * (odd ? 1 : -1),
                    (even ? DIST : .0f) * (odd ? 1 : -1),
                    Z_POSITION,
                    !even
            ));
        });
        return group;
    }

    public void buildFortressEdges(Consumer<Group> consumer) {
//        for (int i = 0 ; i < COORDINATES.length; i++) {
//            var even = j % 2 == 0;
//            var odd = j % 3 == 0;
//            consumer.accept(buildGroup(
//            return buildEdge(
//                    COORDINATES[i][even ? 0 : 1] *
//                            (odd ? 1 : -1),
//                    COORDINATES[i][even ? 1 : 0] *
//                            (odd ? 1 : -1),
//                    COORDINATES[i][2],
//                    even
//            );
//            ));
//        }
    }

    float getToothSideLength() {
        return TOOTH_SIDE_LENGTH;
    }

    protected AxisAngle4d getAngle() {
        return ANGLE;
    }
}