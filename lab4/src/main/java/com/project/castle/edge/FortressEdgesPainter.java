package com.project.castle.edge;

import java.util.stream.IntStream;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class FortressEdgesPainter extends EdgesPainter {
    // distance from center
    private final static float DIST = 0.192f;
    private final static float Z_POSITION = 0.63f;
    private final static float[][] COORDINATES = new float[][]{
            {0.4f, 0.4f, .0f},
            {-0.4f, 0.4f, .0f},
            {0.8f, 0.8f, -0.25f},
            {-0.8f, 0.8f, -0.25f}
    };

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

    float[][] getVectorCoordinates() {
        return COORDINATES;
    }

    TransformGroup getBuildCallback(float x, float y, float z, boolean rotate) {
        return buildGroup(new Vector3f(x, y, z), buildFortressEdges(), false);
    }
}