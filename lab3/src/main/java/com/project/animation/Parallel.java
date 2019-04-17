package com.project.animation;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Parallel extends Animation {
    void animate(Group root) {
        var rectangle = new Rectangle(10, 200, 50, 50);
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        rectangle.setFill(Color.DARKBLUE);
        rectangle.setTranslateX(50);
        rectangle.setTranslateY(75);
        root.getChildren().add(rectangle);

        var fade = new FadeTransition(Duration.millis(3000), rectangle);
        fade.setFromValue(1.0f);
        fade.setToValue(0.3f);
        fade.setCycleCount(2);
        fade.setAutoReverse(true);

        var translate =
                new TranslateTransition(Duration.millis(2000), rectangle);
        translate.setFromX(50);
        translate.setToX(350);
        translate.setCycleCount(2);
        translate.setAutoReverse(true);

        var rotate = new RotateTransition(Duration.millis(3000), rectangle);
        rotate.setByAngle(180f);
        rotate.setCycleCount(4);
        rotate.setAutoReverse(true);

        var scale = new ScaleTransition(Duration.millis(2000), rectangle);
        scale.setToX(2f);
        scale.setToY(2f);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);

        var parallel = new ParallelTransition();
        parallel.getChildren().addAll(fade, translate, rotate, scale);
        parallel.setCycleCount(Timeline.INDEFINITE);
        parallel.play();
    }
}