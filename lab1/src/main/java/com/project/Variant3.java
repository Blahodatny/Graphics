package com.project;

import java.util.stream.IntStream;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Variant3 extends Program {
    private final static byte INDENT = 20;

    private final static Color[] COLORS = {
            Color.GREEN, Color.YELLOW, Color.RED
    };
    private final static double COEFFICIENT = 1.78;
    private final static byte RADIUS = 14;

    private static Polygon triangle(double middleX, double middleY,
            double indent, Color color) {
        /*
         * calculate half of the triangle's side length
         * to find the X coordinates of the remaining tops
         * */
        var halfLengthSide = (middleY - indent) / 2;
        var polygon = new Polygon(
                middleX,
                indent,
                middleX - halfLengthSide,
                middleY,
                middleX + halfLengthSide,
                middleY
        );
        polygon.setFill(color);
        return polygon;
    }

    public Color setBackgroundColor() { return Color.GRAY; }

    public void run(Group root) {
        root.getChildren().addAll(
                triangle(MIDDLE_X, MIDDLE_Y, INDENT, Color.RED),
                triangle(MIDDLE_X, MIDDLE_Y - INDENT, INDENT * 3, Color.WHITE),
                new Rectangle(
                        MIDDLE_X - (INDENT >> 1),
                        MIDDLE_Y,
                        INDENT,
                        MIDDLE_Y - INDENT
                )
        );

        IntStream.range(0, COLORS.length)
                .forEach(i -> root.getChildren()
                        .add(new Circle(
                                MIDDLE_X,
                                MIDDLE_Y - INDENT * (i + 1) * COEFFICIENT,
                                RADIUS,
                                COLORS[i]
                        )));
    }
}