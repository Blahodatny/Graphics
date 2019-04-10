package com.project.variant2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Program extends Application {
    private final static short WIDTH = 600;
    private final static short HEIGHT = 400;

    private final static short[][] PARAMS = {
            {100, 225, 150},
            {180, 260, 45},
            {300, 260, 45},
            {20, 90, 20},
            {110, 40, 20},
            {350, 90, 20},
            {440, 40, 20}
    };
    private final static byte TOP = 70;

    public void start(Stage stage) {
        var root = new Group();
        var scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.DARKBLUE);

        IntStream.range(0, PARAMS.length)
                .forEach(i -> {
                    var rectangle = new Rectangle(
                            PARAMS[i][0],
                            PARAMS[i][1],
                            i == 0 ? 3 * PARAMS[i][2] : PARAMS[i][2],
                            PARAMS[i][2]
                    );
                    rectangle.setFill(i == 0 ? Color.BROWN : Color.YELLOW);
                    root.getChildren().add(rectangle);
                });

        var triangle = new Polygon(
                PARAMS[0][0],
                PARAMS[0][1],
                PARAMS[0][0] + 3 * PARAMS[0][2],
                PARAMS[0][1],
                PARAMS[5][0] - TOP,
                PARAMS[3][1] + PARAMS[3][2]
        );
        triangle.setFill(Color.GRAY);
        root.getChildren().add(triangle);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}