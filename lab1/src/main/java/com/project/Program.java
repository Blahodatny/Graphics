package com.project;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

abstract class Program extends Application {
    final static short MIDDLE_X = 300;
    final static short MIDDLE_Y = 200;

    abstract Color setBackgroundColor();

    abstract void run(Group root);

    public void start(Stage stage) {
        var root = new Group();
        var scene = new Scene(root, MIDDLE_X * 2, MIDDLE_Y * 2);
        scene.setFill(setBackgroundColor());
        run(root);
        stage.setScene(scene);
        stage.show();
    }
}