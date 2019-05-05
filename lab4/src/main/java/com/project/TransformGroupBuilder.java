package com.project;

import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3f;

public abstract class TransformGroupBuilder {
    protected abstract AxisAngle4d getAngle();

    protected TransformGroup buildGroup(Vector3f vector, Node node,
            boolean rotate) {
        var transform = new Transform3D();
        transform.setTranslation(vector);
        if (rotate)
            transform.setRotation(getAngle());

        var group = new TransformGroup();
        group.setTransform(transform);
        group.addChild(node);
        return group;
    }
}