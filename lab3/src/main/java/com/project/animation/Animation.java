package com.project.animation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

abstract class Animation extends Application {
    final private static short LENGTH = 500;

    abstract void animate(Group root);

    public void start(Stage stage) {
        var root = new Group();
        animate(root);
        stage.setScene(new Scene(root, LENGTH, LENGTH));
        stage.show();
    }
}