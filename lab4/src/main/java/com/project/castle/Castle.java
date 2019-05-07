package com.project.castle;

import com.project.castle.edge.CastleWallEdgesPainter;
import com.project.castle.edge.FortressEdgesPainter;
import com.project.castle.fortress.CastleWallsPainter;
import com.project.castle.tower.TowerPainter;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Castle implements ActionListener {
    private float[][] distances = new float[][]{
            {1, 1}, {-1, -1}, {-1, 1}, {1, -1}
    };
    private float upperEyeLimit = 5.0f;
    private float lowerEyeLimit = 1.0f;
    private float farthestEyeLimit = 6.0f;
    private float nearestEyeLimit = 3.0f;

    private TransformGroup treeTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new Castle();
    }

    private Castle() {
        var universe = new SimpleUniverse();

        viewingTransformGroup =
                universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        new Timer(50, this).start();
    }

    private BranchGroup createSceneGraph() {
        var root = new BranchGroup();

        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildCastleSkeleton();
        root.addChild(treeTransformGroup);

        var background = new Background(new Color3f(1.0f, 1.0f, 1.0f));
        background.setApplicationBounds(new BoundingSphere(
                new Point3d(0, 0, 0),
                100000
        ));
        root.addChild(background);

        var sphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

        var light = new DirectionalLight(
                new Color3f(1.0f, 0.5f, 0.4f),
                new Vector3f(4.0f, -7.0f, -12.0f)
        );
        light.setInfluencingBounds(sphere);
        root.addChild(light);

        var ambient = new AmbientLight(new Color3f(1.0f, 1.0f, 1.0f));
        ambient.setInfluencingBounds(sphere);
        root.addChild(ambient);

        return root;
    }

    private void buildCastleSkeleton() {
        new CastleWallsPainter().buildTiers(treeTransformGroup::addChild);
        new CastleWallEdgesPainter().buildEdges(treeTransformGroup::addChild);
        setOneLevelOfTowers(0.8f, 0.25f);
        setOneLevelOfTowers(0.4f, 0.5f);
        new TowerPainter().buildTowers(treeTransformGroup::addChild);
        new FortressEdgesPainter().buildEdges(treeTransformGroup::addChild);
    }

    private void setOneLevelOfTowers(float distanceFromCentre, float height) {
        IntStream.range(0, distances.length).forEach(i -> {
            var tower = new Transform3D();
            tower.setTranslation(new Vector3f(
                    distanceFromCentre * distances[i][0],
                    distanceFromCentre * distances[i][1],
                    height
            ));
            var group = new TransformGroup();
            group.setTransform(tower);
            group.addChild(CastleBody.getTower());
            treeTransformGroup.addChild(group);
        });
    }

    public void actionPerformed(ActionEvent e) {
        var delta = 0.03f;

        treeTransform3D.rotZ(angle);
        treeTransformGroup.setTransform(treeTransform3D);
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
        viewingTransformGroup.setTransform(viewingTransform);
    }
}