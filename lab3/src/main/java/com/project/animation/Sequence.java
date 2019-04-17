package com.project.animation;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Sequence extends Animation {
    void animate(Group root) {
        var rectangle = new Rectangle(25, 25, 50, 50);
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        rectangle.setFill(Color.CRIMSON);
        rectangle.setTranslateX(50);
        rectangle.setTranslateY(50);
        root.getChildren().add(rectangle);

        var fade = new FadeTransition(Duration.millis(1000), rectangle);
        fade.setFromValue(1.0f);
        fade.setToValue(0.3f);
        fade.setCycleCount(1);
        fade.setAutoReverse(true);

        var translate =
                new TranslateTransition(Duration.millis(2000), rectangle);
        translate.setFromX(50);
        translate.setFromY(40);
        translate.setToX(375);
        translate.setToY(375);
        translate.setCycleCount(1);
        translate.setAutoReverse(true);

        var rotate = new RotateTransition(Duration.millis(2000), rectangle);
        rotate.setByAngle(180f);
        rotate.setCycleCount(4);
        rotate.setAutoReverse(true);

        var scale = new ScaleTransition(Duration.millis(2000), rectangle);
        scale.setFromX(1);
        scale.setFromY(1);
        scale.setToX(2);
        scale.setToY(2);
        scale.setCycleCount(1);
        scale.setAutoReverse(true);

        var sequence = new SequentialTransition();
        sequence.getChildren().addAll(fade, translate);
        sequence.setCycleCount(Timeline.INDEFINITE);
        sequence.setAutoReverse(true);
        sequence.play();
    }
}