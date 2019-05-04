package com.project;

import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;

import java.awt.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class Main extends JFrame
{
    //The canvas to be drawn upon.
    public Canvas3D myCanvas3D;
    public Main() {
//������� ��� �������� ���� �� ������ � ��������
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//��������� ��������� ����� �� �����������
        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
//��������� SimpleUniverse (����������� �������)
        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);
//��������� ������� �� �����������
        simpUniv.getViewingPlatform().setNominalViewingTransform();
//��������� �����
        createSceneGraph(simpUniv);
//������� ����� � �����
        addLight(simpUniv);
        //������� ����� ���������� �������� �� ���� �� ��������� ����
        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);
        //��������� ���� ��������
        setTitle("Sample");
        setSize(700, 700);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        Main cockroach = new Main();
    }
    //� ����� ����� ����������� ��'���� �� ��������� �� �����
    public void createSceneGraph(SimpleUniverse su)
    {
// ������������ ����� .obj.
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene cockroachScene = null;
        try
        {
            cockroachScene = f.load("models/mike.obj");
        }
        catch (Exception e)
        {
            System.out.println("File loading failed:" + e);
        }
//��������� ��������������� ����� ��� ������������� ��'����
        Transform3D scaling = new Transform3D();
        scaling.setScale(1.0/6);
        Transform3D tfRoach = new Transform3D();
        tfRoach.rotX(Math.PI/3);
        tfRoach.mul(scaling);
        TransformGroup tgRoach = new TransformGroup(tfRoach);
        TransformGroup sceneGroup = new TransformGroup();
//        sceneGroup.addChild(cockroachScene.getSceneGroup());

//��������� ���� ��'����, � ���� ���������� ������
//�� ���� � ������� ���� ��'����
        Hashtable roachNamedObjects = cockroachScene.getNamedObjects();
        Enumeration enumer = roachNamedObjects.keys();
        String name;
        while (enumer.hasMoreElements())
        {
            name = (String) enumer.nextElement();
            System.out.println("Name: "+name);
        }
//��������� ��� ������� � �����-������� ����
        Appearance lightApp = new Appearance();
        setToMyDefaultAppearance(lightApp,new Color3f(0f,0f,1f));

        Appearance lightApp1 = new Appearance();
        setToMyDefaultAppearance(lightApp1,new Color3f(0f,0f,1f));

        Shape3D wheel1 = (Shape3D) roachNamedObjects.get("left_leg");
        wheel1.setAppearance(lightApp);
        Shape3D wheel2 = (Shape3D) roachNamedObjects.get("right_leg");
        wheel2.setAppearance(lightApp);
        Shape3D wheel3 = (Shape3D) roachNamedObjects.get("left_hand");
        wheel3.setAppearance(lightApp);
        Shape3D wheel4 = (Shape3D) roachNamedObjects.get("right_hand");
        wheel4.setAppearance(lightApp);
        Shape3D platinum = (Shape3D) roachNamedObjects.get("monstr");

        TextureLoader loader = new TextureLoader("sources/123.jpg", "LUMINACE", new Container());
        Texture texture = loader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(1.0f, 0.0f, 0.0f, 0.0f));
// ������������ �������� ��������
// ���� ���� REPLACE, BLEND ��� DECAL ������ MODULATE
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        Appearance ap = new Appearance();

        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);

        platinum.setAppearance(lightApp1);
        platinum.setAppearance(ap);
        TransformGroup car = new TransformGroup();
        car.addChild(platinum.cloneTree());

//        TransformGroup wheels = new TransformGroup();
        TransformGroup wheel1gr = new TransformGroup();
        TransformGroup wheel2gr = new TransformGroup();
        TransformGroup wheel3gr = new TransformGroup();
        TransformGroup wheel4gr = new TransformGroup();
        wheel1gr.addChild(wheel1.cloneTree());
        wheel2gr.addChild(wheel2.cloneTree());
        wheel3gr.addChild(wheel3.cloneTree());
        wheel4gr.addChild(wheel4.cloneTree());
//        Transform3D translateTransform= new Transform3D();
//        rotateTransformY.rotY(Math.PI/2);
//        translateTransform.mul(rotateTransformY);

        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0),Double.MAX_VALUE);
        BranchGroup theScene = new BranchGroup();
        Transform3D tCrawl = new Transform3D();
        Transform3D tCrawl1 = new Transform3D();
        tCrawl.rotY(-90D);
        tCrawl1.rotX(-90D);
//        tCrawl1.setTranslation(new Vector3f(0.2f, -0.1f, -0.61f));
        long crawlTime = 10000;//���, �� ���� ������ ��������� �����
        Alpha crawlAlpha = new Alpha(1,
                Alpha.INCREASING_ENABLE,
                0,
                0, crawlTime,0,0,0,0,0);
        float crawlDistance = 3.0f; //�������, �� ��� ����������� �ᒺ��
        PositionInterpolator posICrawl = new PositionInterpolator(crawlAlpha,
                sceneGroup,tCrawl, -9.0f, crawlDistance);

        long crawlTime1 = 30000;//���, �� ���� ������ ��������� �����
        Alpha crawlAlpha1 = new Alpha(1,
                Alpha.INCREASING_ENABLE,
                3000,
                0, crawlTime1,0,0,0,0,0);
        float crawlDistance1 = 15.0f; //�������, �� ��� ����������� �ᒺ��
        PositionInterpolator posICrawl1 = new PositionInterpolator(crawlAlpha1,
                sceneGroup,tCrawl1, -9.0f, crawlDistance1);


        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
        posICrawl.setSchedulingBounds(bs);
        posICrawl1.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(posICrawl);
//        sceneGroup.addChild(posICrawl1);

        Alpha upRamp = new Alpha();

        upRamp.setIncreasingAlphaDuration(800);
        upRamp.setLoopCount(-1);

        Transform3D wheelSpinner = new Transform3D();
        Transform3D transform1 = new Transform3D();
        Transform3D transform2 = new Transform3D();
        Transform3D transform3 = new Transform3D();
        Transform3D transform4 = new Transform3D();

        wheelSpinner.rotX(Math.PI*2);
        wheelSpinner.setTranslation(new Vector3f(0.5f,0f,0f));
//        transform.set(0.0f);
        transform4.rotZ(-Math.PI/2);
        transform4.setTranslation(new Vector3f(0.5f, -0.1f, 0.55f));

        transform2.rotZ(-Math.PI/2);
        transform2.setTranslation(new Vector3f(0.5f, -0.1f, 0.51f));

        transform1.rotZ(-Math.PI/2);
        transform1.setTranslation(new Vector3f(0.5f, -0.1f, -0.65f));

        transform3.rotZ(-Math.PI/2);
        transform3.setTranslation(new Vector3f(0.5f, -0.1f, -0.61f));
//        transform.mul(wheelSpinner, transform);
//        wheelSpinner.set

        RotationInterpolator mySpinner1 = new RotationInterpolator(upRamp, wheel1gr,
                transform1, 0.0f,(float)(Math.PI * 2.0) );
        RotationInterpolator mySpinner2 = new RotationInterpolator(upRamp, wheel2gr,
                transform2, 0.0f,(float)(Math.PI * 2.0) );
        RotationInterpolator mySpinner3 = new RotationInterpolator(upRamp, wheel3gr,
                transform3, 0.0f,(float)(Math.PI * 2.0) );
        RotationInterpolator mySpinner4 = new RotationInterpolator(upRamp, wheel4gr,
                transform4, 0.0f, (float)(Math.PI * 2.0) );

//        RotationPathInterpolator
        mySpinner1.setSchedulingBounds(bounds);
        mySpinner2.setSchedulingBounds(bounds);
        mySpinner3.setSchedulingBounds(bounds);
        mySpinner4.setSchedulingBounds(bounds);

        wheel1gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel2gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel3gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel4gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel1gr.addChild(mySpinner1);
        wheel2gr.addChild(mySpinner2);
        wheel3gr.addChild(mySpinner3);
        wheel4gr.addChild(mySpinner4);

        sceneGroup.addChild(car);
        sceneGroup.addChild(wheel1gr);
        sceneGroup.addChild(wheel2gr);
        sceneGroup.addChild(wheel3gr);
        sceneGroup.addChild(wheel4gr);
        tgRoach.addChild(sceneGroup); // 000000000000000000000000
        theScene.addChild(tgRoach);//��������� ��'���� � �����
//��������� ���� ���
        Background bg = new Background(new Color3f(0.5f,0.5f,0.5f));
        // ��� ��� ��������
//        TextureLoader myLoader = new  TextureLoader("D:/Study/3 ����/2 �������/��/lab6/src/whit.jpg",this);
//        ImageComponent2D myImage = myLoader.getImage( );
//        bg.setImage(myImage);
        bg.setApplicationBounds(bounds);
        theScene.addChild(bg);
        theScene.compile();
//������ ����� �� ����������� �������
        su.addBranchGraph(theScene);
    }
    //����� ��� ��������� �������� �������
    public static void setToMyDefaultAppearance(Appearance app, Color3f col)
    {
        app.setMaterial(new Material(col,col,col,col,150.0f));
    }
    //����� ��� ��������� ���������
    public void addLight(SimpleUniverse su)
    {
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(0.5f,1.0f,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }
}

