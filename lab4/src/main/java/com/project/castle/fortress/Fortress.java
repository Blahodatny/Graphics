package com.project.castle.fortress;

import com.project.TransformGroupBuilder;
import com.project.castle.FLAGS;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearence;

abstract class Fortress extends TransformGroupBuilder {
    abstract String getFortressPicture();

    abstract float getFortressHeight();

    TransformGroup buildFortress(float x, float y, float z,
            float boxSideLength) {
        return buildGroup(
                new Vector3f(x, y, z),
                new Box(
                        boxSideLength,
                        boxSideLength,
                        getFortressHeight(),
                        FLAGS.get(),
                        getAppearence(getFortressPicture())
                ),
                false
        );
    }
}