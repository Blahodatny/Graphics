package com.project;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.io.FileNotFoundException;
import java.util.Objects;
import javax.media.j3d.Alpha;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

class Car extends JFrame {
    private static final double[][] WHEELS_Y_Z = new double[][]{
            {-0.1, -0.644},
            {-0.101, 0.52},
            {-0.1, -0.625},
            {-0.101, 0.535}
    };

    private Car() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        var universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();

        createSceneGraph(universe);
        addLight(universe);

        var behavior = new OrbitBehavior(canvas);
        behavior.setSchedulingBounds(new BoundingSphere(
                new Point3d(0.0, 0.0, 0.0),
                Double.MAX_VALUE
        ));
        universe.getViewingPlatform().setViewPlatformBehavior(behavior);

        setTitle("Car");
        setSize(700, 700);
        getContentPane().add("Center", canvas);
        setVisible(true);
    }

    private void createSceneGraph(SimpleUniverse universe) {
        var sphere = new BoundingSphere(
                new Point3d(0.0, 0.0, 0.0),
                Double.MAX_VALUE
        );
        var carBranchGroup = new BranchGroup();
        var carBackground = new Background(new Color3f(1.0f, 1.0f, 1.0f));

        Scene carScene = null;

        try {
            carScene = new ObjectFile(ObjectFile.RESIZE).load(
                    "lab6/src/main/resources/models/car.obj");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var roachNamedObjects =
                Objects.requireNonNull(carScene).getNamedObjects();

        // start animation
        var startTransformation = new Transform3D();
        startTransformation.setScale(1.0 / 6);
        var combinedStartTransformation = new Transform3D();
        combinedStartTransformation.rotY(-3 * Math.PI / 2);
        combinedStartTransformation.mul(startTransformation);

        var carStartTransformGroup =
                new TransformGroup(combinedStartTransformation);


        // wheels
        int movesCount = 100; // moves count
        int movesDuration = 500; // moves for 0,3 seconds
        int startTime = 0; // launch animation after timeStart seconds

        var sceneGroup = new TransformGroup();
        for (var i = 0; i < WHEELS_Y_Z.length; i++) {
            var wheelTG1 = new TransformGroup();
            wheelTG1.addChild(((Shape3D) roachNamedObjects.get(
                    "wheel" + (i + 1))).cloneTree());

            var legRotAxis = new Transform3D();
            legRotAxis.set(new Vector3d(0, WHEELS_Y_Z[i][0], WHEELS_Y_Z[i][1]));
            legRotAxis.setRotation(new AxisAngle4d(0, 0, -0.1, Math.PI / 2));

            var wheel1rot =
                    new RotationInterpolator(
                            new Alpha(
                                    movesCount,
                                    Alpha.INCREASING_ENABLE,
                                    startTime,
                                    0,
                                    movesDuration,
                                    0,
                                    0,
                                    0,
                                    0,
                                    0
                            ),
                            wheelTG1,
                            legRotAxis,
                            0.0f,
                            (float) Math.PI * 2
                    );
            wheel1rot.setSchedulingBounds(sphere);
            wheelTG1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            wheelTG1.addChild(wheel1rot);
            sceneGroup.addChild(wheelTG1);
        }

        var tgBody = new TransformGroup();
        tgBody.addChild(((Shape3D) roachNamedObjects.get("platinum1")).cloneTree());
        sceneGroup.addChild(tgBody.cloneTree());

        carBranchGroup.addChild(rotate(translate(
                carStartTransformGroup,
                new Vector3f(0.0f, 0.0f, 0.5f)
        ), new Alpha(10, 5000)));
        carStartTransformGroup.addChild(sceneGroup);

        carBackground.setApplicationBounds(new BoundingSphere(
                new Point3d(120.0, 250.0, 100.0),
                Double.MAX_VALUE
        ));
        carBranchGroup.addChild(carBackground);

        carBranchGroup.compile();
        universe.addBranchGraph(carBranchGroup);
    }

    private void addLight(SimpleUniverse universe) {
        var group = new BranchGroup();
        var light = new DirectionalLight(
                new Color3f(1.0f, 1.0f, 1.0f),
                new Vector3f(-1.0f, 0.0f, -0.5f)
        );
        light.setInfluencingBounds(new BoundingSphere(new Point3d(
                0.0,
                0.0,
                0.0
        ), 100.0));
        group.addChild(light);
        universe.addBranchGraph(group);
    }

    private TransformGroup translate(Node node, Vector3f vector) {
        var transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        var transformGroup = new TransformGroup();
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(node);
        return transformGroup;
    }

    private TransformGroup rotate(Node node, Alpha alpha) {
        var xFormGroup = new TransformGroup();
        xFormGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        var interpolator = new RotationInterpolator(alpha, xFormGroup);
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(
                0.0,
                0.0,
                0.0
        ), 1.0));

        xFormGroup.addChild(interpolator);
        xFormGroup.addChild(node);
        return xFormGroup;
    }

    public static void main(String... args) {
        new Car();
    }
}