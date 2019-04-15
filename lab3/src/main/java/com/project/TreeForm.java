package com.project;

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

    private static Ellipse ellipse;
    private static Rectangle stem;
    private static Arc[] crown = new Arc[3];

    static {
        ellipse = new Ellipse(
                CENTER_X,
                CENTER_Y + 305,
                RADIUS_X - 230,
                RADIUS_Y - 215
        );
        ellipse.setFill(Paint.valueOf("#DEB887"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(3);

        stem = new Rectangle(370, 580, 60, 85);
        stem.setArcHeight(30);
        stem.setArcWidth(80);
        stem.setFill(Color.BROWN);
        stem.setStroke(Color.BLACK);
        stem.setStrokeWidth(3);

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
            arc.setStrokeWidth(3);
            crown[i] = arc;
        });
    }

    static Ellipse getEllipse() {
        return ellipse;
    }

    static Rectangle getStem() {
        return stem;
    }

    static Arc[] getCrown() {
        return crown;
    }
}