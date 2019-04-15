package com.project.lab;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

class Animation {
    final private static byte CYCLE_COUNT = 2;
    final private static Duration TIME = Duration.millis(2000);

    static void run(Group root) {
        var scale = new ScaleTransition(TIME, root);
        scale.setToX(-1f);
        scale.setToY(1f);
        scale.setCycleCount(CYCLE_COUNT + 2);
        scale.setAutoReverse(true);

        var translate = new TranslateTransition(TIME, root);
        translate.setFromX(50);
        translate.setToX(750);
        translate.setCycleCount(CYCLE_COUNT + 2);
        translate.setAutoReverse(true);

        var rotate = new RotateTransition(TIME, root);
        rotate.setByAngle(360f);
        rotate.setCycleCount(CYCLE_COUNT);
        rotate.setAutoReverse(true);

        var parallel = new ParallelTransition();
        parallel.getChildren().addAll(translate, scale, rotate);
        parallel.setCycleCount(Timeline.INDEFINITE);
        parallel.play();
    }
}