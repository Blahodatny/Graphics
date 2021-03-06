package com.project.castle.edge;

import com.project.ColorPainter;
import com.project.FLAGS;
import com.project.TransformGroupFactory;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

abstract class Edge extends TransformGroupFactory {
    private static final int COEFFICIENT = 25;

    abstract float getToothSideLength();

    private TransformGroup buildEdge() {
        var group = new TransformGroup();
        group.addChild(buildGroup(
                new Vector3f(.0f, .0f, .0f),
                new Box(
                        getToothSideLength() * COEFFICIENT,
                        getToothSideLength(),
                        getToothSideLength(),
                        FLAGS.get(),
                        ColorPainter.getAppearance()
                ),
                false
        ));
        for (var i = getToothSideLength() * (COEFFICIENT - 1);
             i >= -getToothSideLength() * (COEFFICIENT - 1);
             i -= getToothSideLength() * 4)
            group.addChild(buildGroup(
                    new Vector3f(i, .0f, getToothSideLength() * 1.5f),
                    new Box(
                            getToothSideLength(),
                            getToothSideLength(),
                            getToothSideLength(),
                            FLAGS.get(),
                            ColorPainter.getAppearance()
                    ),
                    false
            ));
        return group;
    }

    TransformGroup buildEdge(float x, float y, float z, boolean rotate) {
        return buildGroup(new Vector3f(x, y, z), buildEdge(), rotate);
    }
}