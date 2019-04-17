package com.project.lab;

import java.util.stream.IntStream;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

class ChristmasBalls {
    final private static byte BALLS_COUNT = 21;
    final private static byte RADIUS = 18;
    final private static Paint[] COLORS = new Paint[]{
            Color.BLUE, Color.ORANGE, Paint.valueOf("#00FF00"), Color.YELLOW,
            Color.PURPLE, Color.RED, Color.CYAN
    };
    final private static short[][] COORDINATES = new short[][]{
            {430, 250}, {470, 460}, {370, 274}, {410, 460}, {260, 505},
            {405, 590}, {380, 345}, {350, 230}, {305, 430}, {280, 573},
            {330, 510}, {510, 500}, {330, 330}, {500, 370}, {420, 530},
            {465, 310}, {355, 470}, {340, 570}, {545, 540}, {430, 400},
            {466, 583}
    };

    private static Circle[] balls = new Circle[BALLS_COUNT];
    private static Arc[] arcs = new Arc[BALLS_COUNT];

    static {
        IntStream.range(0, balls.length).forEach(i -> {
            balls[i] = new Circle(0, 0, RADIUS);
            balls[i].setStroke(Color.BLACK);
            balls[i].setStrokeWidth(Stroke.WIDTH);
            balls[i].setFill(COLORS[i % COLORS.length]);
            balls[i].setCenterX(COORDINATES[i][0]);
            balls[i].setCenterY(COORDINATES[i][1]);

            arcs[i] = new Arc(100, 100, 15, 15, 90, 135);
            arcs[i].setType(ArcType.ROUND);
            arcs[i].setStroke(Color.WHITE);
            arcs[i].setFill(Color.WHITE);
            arcs[i].setOpacity(0.5);
            arcs[i].setCenterX(COORDINATES[i][0]);
            arcs[i].setCenterY(COORDINATES[i][1]);
        });
    }

    static Circle[] getBalls() {
        return balls;
    }

    static Arc[] getArcs() {
        return arcs;
    }
}