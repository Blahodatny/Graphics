package com.project.castle.fortress;

import com.project.TransformGroupBuilder;
import com.project.FLAGS;
import com.sun.j3d.utils.geometry.Box;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.media.j3d.Group;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearance;

public class CastleWallsPainter extends TransformGroupBuilder {
    private static final String IMAGE = "lab4/src/main/resources/metal.jpg";
    private static final float FORTRESS_HEIGHT = 0.125f;
    private static final float[] FORTRESS_SIDE_LENGTHS =
            new float[]{1.0f, 0.6f};

    public void buildTiers(Consumer<Group> consumer) {
        IntStream.range(0, FORTRESS_SIDE_LENGTHS.length)
                .forEach(i -> consumer.accept(buildGroup(
                        new Vector3f(.0f, 0.f, i == 0 ? 0.f : 0.25f),
                        new Box(
                                FORTRESS_SIDE_LENGTHS[i],
                                FORTRESS_SIDE_LENGTHS[i],
                                FORTRESS_HEIGHT,
                                FLAGS.get(),
                                getAppearance(IMAGE)
                        ),
                        false
                )));
    }

    protected AxisAngle4d getAngle() { return null; }
}