package com.project;

import java.util.stream.IntStream;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Variant2 extends Program {
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

    public Color setBackgroundColor() {
        return Color.DARKBLUE;
    }

    public void run(Group root) {
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
    }
}