package com.project;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

class TreeStem {
    private Ellipse ellipse;
    private Rectangle trunk;

    TreeStem() {
        ellipse = new Ellipse(400, 655, 70, 35);
        ellipse.setFill(Paint.valueOf("#DEB887"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(3);

        trunk = new Rectangle(370, 580, 60, 85);
        trunk.setArcHeight(30);
        trunk.setArcWidth(80);
        trunk.setFill(Color.BROWN);
        trunk.setStroke(Color.BLACK);
        trunk.setStrokeWidth(3);
    }

    Ellipse getEllipse() {
        return ellipse;
    }

    Rectangle getTrunk() {
        return trunk;
    }
}