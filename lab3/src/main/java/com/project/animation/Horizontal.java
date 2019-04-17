package com.project.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Horizontal extends Animation {
    void animate(Group root) {
        final var rect = new Rectangle(100, 50, 100, 50);
        rect.setFill(Color.RED);
        root.getChildren().add(rect);

        final var time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.setAutoReverse(true);
        time.getKeyFrames()
                .add(new KeyFrame(
                        Duration.millis(1000),
                        new KeyValue(rect.xProperty(), 300)
                ));
        time.play();

        final var rect1 = new Rectangle(200, 100, 200, 100);
        rect1.setFill(Color.BROWN);
        root.getChildren().add(rect1);

        final var time1 = new Timeline();
        time1.setCycleCount(Timeline.INDEFINITE);
        time1.setAutoReverse(true);
        time1.getKeyFrames()
                .add(new KeyFrame(
                        Duration.millis(500),
                        new KeyValue(
                                rect1.xProperty(),
                                300,
                                Interpolator.EASE_BOTH
                        )
                ));
        time1.play();
    }
}