package com.project.castle.tower;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearence;

abstract class Tower {
    private final static String lines = "lab4/src/main/resources/lines.png";

    abstract double getAngle();

    abstract float getCylinderRadius();

    abstract float getConeHeight();

    private TransformGroup build(Vector3f vector, Node node) {
        var transform = new Transform3D();
        transform.setTranslation(vector);
        transform.setRotation(new AxisAngle4d(1, 0, 0, getAngle()));

        var group = new TransformGroup();
        group.setTransform(transform);
        group.addChild(node);
        return group;
    }

    TransformGroup build(float x, float y, float height) {
        var group = new TransformGroup();
        var flags =
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        group.addChild(build(
                new Vector3f(x, y, height * 0.5f),
                new Cylinder(
                        getCylinderRadius(),
                        height,
                        flags,
                        getAppearence(lines)
                )
        ));
        group.addChild(build(
                new Vector3f(x, y, height + 0.15f),
                new Cone(
                        getCylinderRadius() + 0.025f,
                        getConeHeight(),
                        flags,
                        getAppearence()
                )
        ));
        return group;
    }
}