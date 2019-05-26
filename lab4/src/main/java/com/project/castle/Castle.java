package com.project.castle;

import com.project.castle.edge.CastleWallEdgesPainter;
import com.project.castle.edge.FortressEdgesPainter;
import com.project.castle.fortress.CastleWallsPainter;
import com.project.castle.fortress.FortressPainter;
import com.project.castle.tower.TowerPainter;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

class Castle implements ActionListener {
    private static final float upperEyeLimit = 5.0f;
    private static final float lowerEyeLimit = 1.0f;
    private static final float farthestEyeLimit = 6.0f;
    private static final float nearestEyeLimit = 3.0f;

    private TransformGroup treeGroup;
    private TransformGroup viewingGroup;
    private Transform3D treeTransform = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();

    private final static float delta = 0.03f;
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String... args) { new Castle(); }

    private Castle() {
        var universe = new SimpleUniverse();
        viewingGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = lowerEyeLimit;

        new Timer(50, this).start();
    }

    private BranchGroup createSceneGraph() {
        var root = new BranchGroup();

        treeGroup = new TransformGroup();
        treeGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildCastle(treeGroup::addChild);
        root.addChild(treeGroup);

        var background = new Background(new Color3f(1.0f, 1.0f, 1.0f));
        background.setApplicationBounds(new BoundingSphere(
                new Point3d(0, 0, 0),
                100000
        ));
        root.addChild(background);

        var light = new DirectionalLight(
                new Color3f(1.0f, 0.5f, 0.4f),
                new Vector3f(4.0f, -7.0f, -12.0f)
        );
        var sphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        light.setInfluencingBounds(sphere);
        root.addChild(light);

        var ambient = new AmbientLight(new Color3f(1.0f, 1.0f, 1.0f));
        ambient.setInfluencingBounds(sphere);
        root.addChild(ambient);

        return root;
    }

    private void buildCastle(Consumer<Group> consumer) {
        new CastleWallsPainter().buildTiers(consumer);
        new CastleWallEdgesPainter().build(consumer);
        new TowerPainter().buildTowers(consumer);
        new FortressPainter().build(consumer);
        new FortressEdgesPainter().build(consumer);
    }

    public void actionPerformed(ActionEvent e) {
        treeTransform.rotZ(angle);
        treeGroup.setTransform(treeTransform);

        angle += delta;

        descend = eyeHeight > upperEyeLimit ||
                !(eyeHeight < lowerEyeLimit) && descend;
        eyeHeight += descend ? -delta : delta;

        approaching = eyeDistance > farthestEyeLimit ||
                !(eyeDistance < nearestEyeLimit) && approaching;
        eyeDistance += approaching ? -delta : delta;

        var eye = new Point3d(eyeDistance, eyeDistance, eyeHeight);
        var center = new Point3d(.0f, .0f, 0.5f);
        var up = new Vector3d(.0f, .0f, 1.0f);

        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingGroup.setTransform(viewingTransform);
    }
}