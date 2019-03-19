package com.project.variant3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class JavaFX extends Application {
    private final static int MIDDLE_X = 300;
    private final static int MIDDLE_Y = 200;
    private final static int INDENT = 20;

    private final static Color[] COLORS = {Color.GREEN, Color.YELLOW, Color.RED};
    private final static double COEFFICIENT = 1.78;
    private final static int RADIUS = 14;

    private static Polygon triangle(
            double middleX, double middleY, double indent, Color color) {
        /*
         * calculate half of the triangle's side length
         * to find the X coordinates of the remaining tops
         * */
        var halfLengthSide = (middleY - indent) / 2;
        var polygon = new Polygon(
                middleX, indent,
                middleX - halfLengthSide, middleY,
                middleX + halfLengthSide, middleY
        );
        polygon.setFill(color);
        return polygon;
    }

    public void start(Stage stage) {
        var root = new Group();
        var scene = new Scene(root, MIDDLE_X * 2, MIDDLE_Y * 2);
        scene.setFill(Color.GRAY);

        root.getChildren().addAll(
                triangle(MIDDLE_X, MIDDLE_Y, INDENT, Color.RED),
                triangle(MIDDLE_X, MIDDLE_Y - INDENT, INDENT * 3, Color.WHITE),
                new Rectangle(
                        MIDDLE_X - (INDENT >> 1), MIDDLE_Y, INDENT, MIDDLE_Y - INDENT
                )
        );

        IntStream.range(0, COLORS.length).forEach(i ->
                root.getChildren().add(
                        new Circle(
                                MIDDLE_X,
                                MIDDLE_Y - INDENT * (i + 1) * COEFFICIENT,
                                RADIUS,
                                COLORS[i]
                        )
                )
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}