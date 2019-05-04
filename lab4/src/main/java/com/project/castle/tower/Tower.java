package com.project.castle.tower;

import com.project.ColorPainter;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3f;

abstract class Tower {
    private final static String lines = "lab4/src/main/resources/lines.png";

    abstract double getAngle();

    abstract float getCylinderRadius();

    abstract float getConeHeight();

    private Transform3D getTransform(Vector3f vector) {
        var transform = new Transform3D();
        transform.setTranslation(vector);
        transform.setRotation(new AxisAngle4d(1, 0, 0, getAngle()));
        return transform;
    }

    private TransformGroup buildWalls(float x, float y, float height) {
        var group = new TransformGroup();
        group.setTransform(getTransform(new Vector3f(x, y, height * 0.5f)));
        group.addChild(new Cylinder(
                getCylinderRadius(),
                height,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                ColorPainter.getAppearence(lines)
        ));
        return group;
    }

    private TransformGroup buildRoof(float x, float y, float height) {
        var group = new TransformGroup();
        group.setTransform(getTransform(new Vector3f(x, y, height + 0.15f)));
        group.addChild(new Cone(
                getCylinderRadius() + 0.025f,
                getConeHeight(),
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                ColorPainter.getAppearence()
        ));
        return group;
    }

    TransformGroup build(float x, float y, float height) {
        var transformGroup = new TransformGroup();
        transformGroup.addChild(buildWalls(x, y, height));
        transformGroup.addChild(buildRoof(x, y, height));
        return transformGroup;
    }
}