package com.project.castle;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3f;

import static com.project.ColorPainter.getAppearence;

class CastleBody {

    static Box getTower() {
        return new Box(
                0.2f,
                0.2f,
                0.125f,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getAppearence("lab4/src/main/resources/image.jpg")
        );
    }

    private static TransformGroup getCover(float x) {
        var group = new TransformGroup();
        var transform = new Transform3D();
        transform.setTranslation(new Vector3f(x, .0f, 0.012f));
        group.setTransform(transform);
        group.addChild(new Box(
                0.008f,
                0.008f,
                0.008f,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getAppearence()
        ));
        return group;
    }

    private static TransformGroup getWall() {
        var group = new TransformGroup();
        var transform = new Transform3D();
        transform.setTranslation(new Vector3f(.0f, .0f, .0f));
        group.setTransform(transform);
        group.addChild(new Box(
                0.2f,
                0.008f,
                0.008f,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getAppearence()
        ));
        return group;
    }

    private static TransformGroup getFetch() {
        var tg = new TransformGroup();
        var wall = getWall();
        tg.addChild(wall);
        for (var i = 0.192f; i > -0.193f; i -= 0.032f) {
            var cover = getCover(i);
            tg.addChild(cover);
        }
        return tg;
    }

    static TransformGroup getFourFetches() {
        var tg = new TransformGroup();
        var distanceFromCentre = 0.192f;
        var zPos = 0.63f;

        var fetch1 = CastleBody.getFetch();
        var fetch1trans = new Transform3D();
        fetch1trans.setTranslation(new Vector3f(.0f, distanceFromCentre, zPos));
        fetch1.setTransform(fetch1trans);
        tg.addChild(fetch1);

        var fetch2 = CastleBody.getFetch();
        var fetch2trans = new Transform3D();
        fetch2trans.setTranslation(new Vector3f(
                .0f,
                -distanceFromCentre,
                zPos
        ));
        fetch2.setTransform(fetch2trans);
        tg.addChild(fetch2);

        var fetch3 = CastleBody.getFetch();
        var fetch3trans = new Transform3D();
        fetch3trans.setTranslation(new Vector3f(distanceFromCentre, .0f, zPos));
        fetch3trans.setRotation(new AxisAngle4d(0, 0, 1, Math.toRadians(90)));
        fetch3.setTransform(fetch3trans);
        tg.addChild(fetch3);

        var fetch4 = CastleBody.getFetch();
        var fetch4trans = new Transform3D();
        fetch4trans.setTranslation(new Vector3f(
                -distanceFromCentre,
                .0f,
                zPos
        ));
        fetch4trans.setRotation(new AxisAngle4d(0, 0, 1, Math.toRadians(90)));
        fetch4.setTransform(fetch4trans);
        tg.addChild(fetch4);
        return tg;
    }
}