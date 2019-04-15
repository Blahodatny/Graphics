package com.project.lab;

import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChristmasTree extends Application {
    final private static short WIDTH = 1600;

    public void start(Stage primaryStage) {
        var root = new Group();

        root.getChildren().addAll(TreeForm.getEllipse(), TreeForm.getStem());

        var crown = TreeForm.getCrown();
        IntStream.range(0, crown.length)
                .forEach(i -> root.getChildren().add(crown[i]));

        root.getChildren().add(ChristmasTreeStar.getStar());

        var balls = ChristmasBalls.getBalls();
        var arcs = ChristmasBalls.getArcs();
        IntStream.range(0, balls.length)
                .forEach(i -> root.getChildren().addAll(balls[i], arcs[i]));

        Animation.run(root);

        primaryStage.setTitle("Christmas Tree");
        primaryStage.setScene(new Scene(root, WIDTH, WIDTH / 2));
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}