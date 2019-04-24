package com.project;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;

class CastleBody {
    static Box getBody(float height, float width) {
        return new Box(
                width,
                width,
                height,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getAppearence("source_folder/metal.jpg")
        );
    }

    static Box getTower() {
        return new Box(
                0.2f,
                0.2f,
                0.125f,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getAppearence("source_folder/Koala.jpg")
        );
    }

    private static Cylinder getCentralTower(float cylinderHeight) {
        return new Cylinder(
                0.1f,
                cylinderHeight,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getAppearence("source_folder/lines.png")
        );
    }

    private static Cone getCentralTowerRoof() {
        return new Cone(
                0.125f,
                0.3f,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getRoofAppearence()
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
                getRoofAppearence()
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
                getRoofAppearence()
        ));
        return group;
    }

    private static TransformGroup getFetch() {
        TransformGroup tg = new TransformGroup();
        TransformGroup wall = getWall();
        tg.addChild(wall);
        for (float i = 0.192f; i > -0.193f; i -= 0.032f) {
            TransformGroup cover = getCover(i);
            tg.addChild(cover);
        }
        return tg;
    }

    public static TransformGroup getProtectFetch(float xPos, float yPos,
            float zPos, boolean turn) {
        TransformGroup tg = new TransformGroup();

        TransformGroup fetch3 = CastleBody.getFetch();
        Transform3D fetch3trans = new Transform3D();
        fetch3trans.setTranslation(new Vector3f(xPos, yPos, zPos));
        if (turn) {
            fetch3trans.setRotation(new AxisAngle4d(
                    0,
                    0,
                    1,
                    Math.toRadians(90)
            ));
        }
        fetch3.setTransform(fetch3trans);
        tg.addChild(fetch3);

        return tg;
    }

    static TransformGroup getFourFetches() {
        var tg = new TransformGroup();
        var distanceFromCentre = 0.192f;
        var zPos = 0.63f;

        TransformGroup fetch1 = CastleBody.getFetch();
        Transform3D fetch1trans = new Transform3D();
        fetch1trans.setTranslation(new Vector3f(.0f, distanceFromCentre, zPos));
        fetch1.setTransform(fetch1trans);
        tg.addChild(fetch1);

        TransformGroup fetch2 = CastleBody.getFetch();
        Transform3D fetch2trans = new Transform3D();
        fetch2trans.setTranslation(new Vector3f(
                .0f,
                -distanceFromCentre,
                zPos
        ));
        fetch2.setTransform(fetch2trans);
        tg.addChild(fetch2);

        TransformGroup fetch3 = CastleBody.getFetch();
        Transform3D fetch3trans = new Transform3D();
        fetch3trans.setTranslation(new Vector3f(distanceFromCentre, .0f, zPos));
        fetch3trans.setRotation(new AxisAngle4d(0, 0, 1, Math.toRadians(90)));
        fetch3.setTransform(fetch3trans);
        tg.addChild(fetch3);

        TransformGroup fetch4 = CastleBody.getFetch();
        Transform3D fetch4trans = new Transform3D();
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

    public static TransformGroup getCylinderTower(float height, float xPos,
            float yPos) {
        TransformGroup tg = new TransformGroup();

        Cylinder centralTower = CastleBody.getCentralTower(height);
        Transform3D centralTowerT = new Transform3D();
        centralTowerT.setTranslation(new Vector3f(xPos, yPos, height * 0.5f));
        centralTowerT.setRotation(new AxisAngle4d(1, 0, 0, Math.toRadians(90)));
        TransformGroup centralTowerTG = new TransformGroup();
        centralTowerTG.setTransform(centralTowerT);
        centralTowerTG.addChild(centralTower);
        tg.addChild(centralTowerTG);

        Cone centralTowerRoof = CastleBody.getCentralTowerRoof();
        Transform3D centralTowerRoofT = new Transform3D();
        centralTowerRoofT.setTranslation(new Vector3f(
                xPos,
                yPos,
                height + 0.15f
        ));
        centralTowerRoofT.setRotation(new AxisAngle4d(
                1,
                0,
                0,
                Math.toRadians(90)
        ));
        TransformGroup centralTowerRoofTG = new TransformGroup();
        centralTowerRoofTG.setTransform(centralTowerRoofT);
        centralTowerRoofTG.addChild(centralTowerRoof);
        tg.addChild(centralTowerRoofTG);

        return tg;
    }

    private static Appearance getRoofAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(new Color(0, 0, 0));
        Color3f ambient = new Color3f(new Color(100, 38, 38));
        Color3f diffuse = new Color3f(new Color(255, 38, 38));
        Color3f specular = new Color3f(new Color(0, 0, 0));
        ap.setMaterial(new Material(
                ambient,
                emissive,
                diffuse,
                specular,
                1.0f
        ));
        return ap;
    }

    private static Appearance getAppearence(String file) {
        var texture = new TextureLoader(file, "LUMINANCE", new Container())
                .getTexture();

        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

        var texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        var ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);
        ap.setMaterial(new Material(
                new Color3f(new Color(100, 38, 38)),
                new Color3f(new Color(0, 0, 0)),
                new Color3f(new Color(100, 38, 38)),
                new Color3f(new Color(0, 0, 0)),
                1.0f
        ));
        return ap;
    }
}