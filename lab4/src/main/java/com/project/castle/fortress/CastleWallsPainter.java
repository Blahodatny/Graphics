package com.project.castle.fortress;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import javax.media.j3d.Group;
import javax.vecmath.AxisAngle4d;

public class CastleWallsPainter extends Fortress {
    private static final String IMAGE = "lab4/src/main/resources/metal.jpg";
    private static final float FORTRESS_HEIGHT = 0.125f;
    private static final float[] FORTRESS_SIDE_LENGTHS =
            new float[]{1.0f, 0.6f};

    public void buildTiers(Consumer<Group> consumer) {
        IntStream.range(0, FORTRESS_SIDE_LENGTHS.length)
                .forEach(i -> consumer.accept(buildFortress(
                        .0f,
                        .0f,
                        i == 0 ? 0.f : 0.25f,
                        FORTRESS_SIDE_LENGTHS[i]
                )));
    }

    String getFortressPicture() { return IMAGE; }

    float getFortressHeight() { return FORTRESS_HEIGHT; }

    protected AxisAngle4d getAngle() { return null; }
}