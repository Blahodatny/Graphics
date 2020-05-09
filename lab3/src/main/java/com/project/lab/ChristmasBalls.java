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

    private static final Circle[] BALLS = new Circle[BALLS_COUNT];
    private static final Arc[] ARCS = new Arc[BALLS_COUNT];

    static {
        IntStream.range(0, BALLS.length).forEach(i -> {
            BALLS[i] = new Circle(0, 0, RADIUS);
            BALLS[i].setStroke(Color.BLACK);
            BALLS[i].setStrokeWidth(Stroke.WIDTH);
            BALLS[i].setFill(COLORS[i % COLORS.length]);
            BALLS[i].setCenterX(COORDINATES[i][0]);
            BALLS[i].setCenterY(COORDINATES[i][1]);

            ARCS[i] = new Arc(100, 100, 15, 15, 90, 135);
            ARCS[i].setType(ArcType.ROUND);
            ARCS[i].setStroke(Color.WHITE);
            ARCS[i].setFill(Color.WHITE);
            ARCS[i].setOpacity(0.5);
            ARCS[i].setCenterX(COORDINATES[i][0]);
            ARCS[i].setCenterY(COORDINATES[i][1]);
        });
    }

    static Circle[] getBalls() { return BALLS; }

    static Arc[] getArcs() { return ARCS; }
}