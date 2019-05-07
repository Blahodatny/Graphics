package com.project;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;

public abstract class TransformGroupFactory extends TransformGroupBuilder {
    private static final AxisAngle4d ANGLE =
            new AxisAngle4d(0, 0, 1, Math.toRadians(90));
    private final float[][] COORDINATES = getVectorCoordinates();

    public abstract float[][] getVectorCoordinates();

    public abstract TransformGroup getBuildCallback(float x, float y, float z,
            boolean rotate);

    public void build(Consumer<Group> consumer) {
        IntStream.range(0, COORDINATES.length)
                .forEach(i -> IntStream.rangeClosed(0, COORDINATES[i].length)
                        .mapToObj(j -> {
                            var even = j % 2 == 0;
                            var odd = j % 3 == 0;
                            return getBuildCallback(
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

    protected AxisAngle4d getAngle() { return ANGLE; }
}