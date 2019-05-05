package com.project.castle.edge;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.media.j3d.Node;
import javax.vecmath.AxisAngle4d;

public class EdgePainter extends Edge {
    private static final float BOX_SIDE_LENGTH = 0.008f;
    private static final AxisAngle4d ANGLE =
            new AxisAngle4d(0, 0, 1, Math.toRadians(90));
    private static final float[][] COORDINATES = new float[][]{
            {0.592f, .0f, 0.38f},
            {0.992f, 0.4f, .0f, 0.133f}
    };

    // 0.592f .0f true
    // -.0f - 0.592f
    // -0.592f -0.f true
    // .0f 0.592f
    private void buildUpperTierEdges(Consumer<Node> consumer) {
        IntStream.rangeClosed(0, COORDINATES[0].length).mapToObj(i -> {
                    var even = i % 2 == 0;
                    var odd = i % 3 == 0;
                    return build(
                            COORDINATES[0][even ? 0 : 1] * (odd ? 1 : -1),
                            COORDINATES[0][even ? 1 : 0] * (odd ? 1 : -1),
                            COORDINATES[0][2],
                            even
                    );
                }
        ).forEach(consumer);
    }

    public void run(Consumer<Node> consumer) {
        for (var i = 0; i < COORDINATES.length; i++)
            for (var j = 0;
                 j < (COORDINATES[i].length - 1) * Math.pow(2, i + 1);
                 j++) {
                consumer.accept(build(
                        COORDINATES[i][0] * (i % 2 == 0 ? 1 : -1),
                        COORDINATES[i][1] * (i % 3 == 1 ? 1 : -1),
                        COORDINATES[i][COORDINATES.length - 1],
                        true
                ));
            }
    }

    float getBoxSideLength() {
        return BOX_SIDE_LENGTH;
    }

    protected AxisAngle4d getAngle() {
        return ANGLE;
    }
}