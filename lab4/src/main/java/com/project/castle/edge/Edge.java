package com.project.castle.edge;

import com.project.castle.FLAGS;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearence;

abstract class Edge {
    abstract float getBoxSideLength();

    private TransformGroup build(Vector3f vector, Node node) {
        var transform = new Transform3D();
        transform.setTranslation(vector);

        var group = new TransformGroup();
        group.setTransform(transform);
        group.addChild(node);
        return group;
    }

    TransformGroup build() {
        var group = new TransformGroup();
        group.addChild(build(
                new Vector3f(.0f, .0f, .0f),
                new Box(
                        getBoxSideLength() * 25,
                        getBoxSideLength(),
                        getBoxSideLength(),
                        FLAGS.get(),
                        getAppearence()
                )
        ));
        for (var i = getBoxSideLength() * 24;
             i >= -getBoxSideLength() * 24;
             i -= getBoxSideLength() * 4)
            group.addChild(build(
                    new Vector3f(i, .0f, getBoxSideLength() * 1.5f),
                    new Box(
                            getBoxSideLength(),
                            getBoxSideLength(),
                            getBoxSideLength(),
                            FLAGS.get(),
                            getAppearence()
                    )
            ));
        return group;
    }
}