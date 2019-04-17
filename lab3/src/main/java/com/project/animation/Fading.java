package com.project.animation;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Fading extends Animation {
    void animate(Group root) {
        var rectangle = new Rectangle(10, 10, 100, 100);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        rectangle.setFill(Color.RED);
        root.getChildren().add(rectangle);

        var fade = new FadeTransition(Duration.millis(3000), rectangle);
        fade.setFromValue(1.0);
        fade.setToValue(0.1);
        fade.setCycleCount(Timeline.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();
    }
}