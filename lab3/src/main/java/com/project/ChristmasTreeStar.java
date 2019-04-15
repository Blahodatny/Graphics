package com.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

class ChristmasTreeStar {
    final private static byte HYPOTENUSE = 50;
    final private static short START_X = 360;
    final private static short START_Y = 140;

    private static Path star;

    static {
        var bigLeg = HYPOTENUSE * Math.sin(Math.toRadians(54));
        var smallLeg = HYPOTENUSE * Math.cos(Math.toRadians(54));
        var middleBigLeg = 2 * bigLeg * Math.cos(Math.toRadians(36));
        var middleSmallLeg = 2 * bigLeg * Math.sin(Math.toRadians(36));
        star = new Path();
        star.getElements().addAll(
                new MoveTo(START_X, START_Y),
                new LineTo(START_X + bigLeg * 2, START_Y),
                new LineTo(
                        START_X + (2 * bigLeg - middleBigLeg),
                        START_Y + middleSmallLeg
                ),
                new LineTo(START_X + bigLeg, START_Y - smallLeg),
                new LineTo(START_X + middleBigLeg, START_Y + middleSmallLeg),
                new LineTo(START_X, START_Y)
        );
        star.setFill(Color.RED);
        star.setStrokeWidth(3);
    }

    static Path getStar() {
        return star;
    }
}