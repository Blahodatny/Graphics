package com.project.castle.edge;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearence;

abstract class Edge {
    private TransformGroup build(Vector3f vector, Node node) {
        var transform = new Transform3D();
        transform.setTranslation(vector);

        var group = new TransformGroup();
        group.setTransform(transform);
        group.addChild(node);
        return group;
    }

    private TransformGroup build(float x) {
        var group = new TransformGroup();
        group.addChild(build(
                new Vector3f(.0f, .0f, .0f),
                new Box(
                        0.2f,
                        0.008f,
                        0.008f,
                        Primitive.GENERATE_NORMALS +
                                Primitive.GENERATE_TEXTURE_COORDS,
                        getAppearence()
                )
        ));
        for (var i = 0.192f; i > -0.193f; i -= 0.032f)
            group.addChild(build(
                    new Vector3f(x, .0f, 0.012f),
                    new Box(
                            0.008f,
                            0.008f,
                            0.008f,
                            Primitive.GENERATE_NORMALS +
                                    Primitive.GENERATE_TEXTURE_COORDS,
                            getAppearence()
                    )
            ));
        return group;
    }
}