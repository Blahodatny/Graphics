package com.project.lab;

import java.util.stream.IntStream;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

class TreeForm {
    final private static short CENTER_X = 400;
    final private static short CENTER_Y = 350;
    final private static short RADIUS_X = 300;
    final private static short RADIUS_Y = 250;
    // 'start angle' and 'length' parameters for arcs
    final private static short[][] PARAMS = {{227, 86}, {230, 80}, {240, 60}};

    private static final Ellipse ELLIPSE;
    private static final Rectangle STEM;
    private static final Arc[] CROWN = new Arc[3];

    static {
        ELLIPSE = new Ellipse(
                CENTER_X,
                CENTER_Y + 305,
                RADIUS_X - 230,
                RADIUS_Y - 215
        );
        ELLIPSE.setFill(Paint.valueOf("#DEB887"));
        ELLIPSE.setStroke(Color.BLACK);
        ELLIPSE.setStrokeWidth(Stroke.WIDTH);

        STEM = new Rectangle(370, 580, 60, 85);
        STEM.setArcHeight(30);
        STEM.setArcWidth(80);
        STEM.setFill(Color.BROWN);
        STEM.setStroke(Color.BLACK);
        STEM.setStrokeWidth(Stroke.WIDTH);

        IntStream.range(0, PARAMS.length).forEach(i -> {
            var arc = new Arc(
                    CENTER_X,
                    CENTER_Y - i * 100,
                    RADIUS_X - (i == 0 ? 0 : 50),
                    RADIUS_Y - i * 25,
                    PARAMS[i][0],
                    PARAMS[i][1]
            );
            arc.setType(ArcType.ROUND);
            arc.setStroke(Color.BLACK);
            arc.setFill(Color.GREEN);
            arc.setStrokeWidth(Stroke.WIDTH);
            CROWN[i] = arc;
        });
    }

    static Ellipse getEllipse() { return ELLIPSE; }

    static Rectangle getStem() { return STEM; }

    static Arc[] getCrown() { return CROWN; }
}