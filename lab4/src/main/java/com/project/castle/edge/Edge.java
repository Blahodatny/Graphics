package com.project.castle.edge;

import com.project.TransformGroupBuilder;
import com.project.castle.FLAGS;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearence;

abstract class Edge extends TransformGroupBuilder {
    private static final int COEFFICIENT = 25;

    abstract float getBoxSideLength();

    private TransformGroup build() {
        var group = new TransformGroup();
        group.addChild(buildGroup(
                new Vector3f(.0f, .0f, .0f),
                new Box(
                        getBoxSideLength() * COEFFICIENT,
                        getBoxSideLength(),
                        getBoxSideLength(),
                        FLAGS.get(),
                        getAppearence()
                ),
                false
        ));
        for (var i = getBoxSideLength() * (COEFFICIENT - 1);
             i >= -getBoxSideLength() * (COEFFICIENT - 1);
             i -= getBoxSideLength() * 4)
            group.addChild(buildGroup(
                    new Vector3f(i, .0f, getBoxSideLength() * 1.5f),
                    new Box(
                            getBoxSideLength(),
                            getBoxSideLength(),
                            getBoxSideLength(),
                            FLAGS.get(),
                            getAppearence()
                    ),
                    false
            ));
        return group;
    }

    TransformGroup build(float x, float y, float z, boolean rotate) {
        return buildGroup(new Vector3f(x, y, z), build(), rotate);
    }
}