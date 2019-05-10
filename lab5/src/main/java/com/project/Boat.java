package com.project;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.io.FileReader;
import java.io.IOException;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

class Boat extends JFrame {
    private final static String TRACK = "lab5/src/main/resources/track.png";
    private final static String BOAT = "lab5/src/main/resources/Boat.obj";

    private static Canvas3D canvas;
    private static SimpleUniverse universe;
    private static BranchGroup root;
    private static TransformGroup boat;

    private Boat() throws IOException {
        setTitle("Lab 5");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas, BorderLayout.CENTER);

        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();

        root = new BranchGroup();
        addImageBackground();
        addDirectionalLightToUniverse();
        addAmbientLightToUniverse();

        changeViewAngle();

        boat = getBoatGroup();
        root.addChild(boat);
        root.compile();

        universe.addBranchGraph(root);
    }

    private void addImageBackground() {
        var background =
                new Background(new TextureLoader(TRACK, canvas).getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        background.setApplicationBounds(new BoundingSphere(
                new Point3d(0.0, 0.0, 0.0), 100.0));
        root.addChild(background);
    }

    private void addDirectionalLightToUniverse() {
        var bounds = new BoundingSphere();
        bounds.setRadius(100);

        var light = new DirectionalLight(
                new Color3f(1, 1, 1),
                new Vector3f(-1, -1, -1)
        );
        light.setInfluencingBounds(bounds);
        root.addChild(light);
    }

    private void addAmbientLightToUniverse() {
        var light = new AmbientLight(new Color3f(1, 1, 1));
        light.setInfluencingBounds(new BoundingSphere());
        root.addChild(light);
    }

    private void changeViewAngle() {
        var transform = new Transform3D();
        transform.setTranslation(new Vector3f(0, 0, 6));
        universe.getViewingPlatform()
                .getMultiTransformGroup()
                .getTransformGroup(0)
                .setTransform(transform);
    }

    private TransformGroup getBoatGroup() throws IOException {
        var transform3D = new Transform3D();
        transform3D.setScale(new Vector3d(0.2, 0.2, 0.2));

        var rotationY = new Transform3D();
        rotationY.rotY(Math.PI / 2);
        transform3D.mul(rotationY);

        var group = getModelGroup(getModelShape3D());
        group.setTransform(transform3D);

        return group;
    }

    private TransformGroup getModelGroup(Shape3D shape) {
        var group = new TransformGroup();
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.addChild(shape);
        return group;
    }

    private Shape3D getModelShape3D() throws IOException {
        var scene = getSceneFromFile();
        var shape = (Shape3D) scene.getNamedObjects().get("frame0");
        scene.getSceneGroup().removeChild(shape);
        return shape;
    }

    private Scene getSceneFromFile() throws IOException {
        var file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE |
                ObjectFile.STRIPIFY);
        return file.load(new FileReader(BOAT));
    }

    public static void main(String[] args) {
        canvas.addKeyListener(new BoatAnimation(boat));
        try {
            new Boat().setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}