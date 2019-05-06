package com.project.castle.fortress;

import com.project.castle.FLAGS;
import com.project.castle.edge.Edge;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearence;

abstract class Fortress extends Edge {
    abstract String getFortressPicture();

    abstract float getFortressHeigth();

    public TransformGroup buildFortress(float x, float y, float z,
            float boxSideLength) {
        return buildGroup(
                new Vector3f(x, y, z),
                new Box(
                        boxSideLength,
                        boxSideLength,
                        getFortressHeigth(),
                        FLAGS.get(),
                        getAppearence(getFortressPicture())
                ),
                false
        );
    }
}