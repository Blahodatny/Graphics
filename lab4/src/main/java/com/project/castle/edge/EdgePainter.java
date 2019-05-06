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
            {0.992f, .0f, 0.133f},
            {0.992f, 0.4f, 0.133f},
            {0.992f, -0.4f, 0.133f}
    };

    public void buildTierEdges(Consumer<Node> consumer) {
        IntStream.range(0, COORDINATES.length)
                .forEach(i -> IntStream.rangeClosed(0, COORDINATES[i].length)
                        .mapToObj(j -> {
                            var even = j % 2 == 0;
                            var odd = j % 3 == 0;
                            return build(
                                    COORDINATES[i][even ? 0 : 1] *
                                            (odd ? 1 : -1),
                                    COORDINATES[i][even ? 1 : 0] *
                                            (odd ? 1 : -1),
                                    COORDINATES[i][2],
                                    even
                            );
                        })
                        .forEach(consumer));
    }

    float getBoxSideLength() {
        return BOX_SIDE_LENGTH;
    }

    protected AxisAngle4d getAngle() {
        return ANGLE;
    }
}