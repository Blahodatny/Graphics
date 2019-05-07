package com.project.castle.tower;

import com.project.ColorPainter;
import com.project.TransformGroupBuilder;
import com.project.castle.FLAGS;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearance;

abstract class Tower extends TransformGroupBuilder {
    private final static String LINES = "lab4/src/main/resources/lines.png";

    abstract float getCylinderRadius();

    abstract float getConeHeight();

    TransformGroup buildTower(float x, float y, float z) {
        var group = new TransformGroup();
        group.addChild(buildGroup(
                new Vector3f(x, y, z * 0.5f),
                new Cylinder(
                        getCylinderRadius(),
                        z,
                        FLAGS.get(),
                        getAppearance(LINES)
                ),
                true
        ));
        group.addChild(buildGroup(
                new Vector3f(x, y, z + 0.15f),
                new Cone(
                        getCylinderRadius() + 0.025f,
                        getConeHeight(),
                        FLAGS.get(),
                        ColorPainter.getAppearance()
                ),
                true
        ));
        return group;
    }
}