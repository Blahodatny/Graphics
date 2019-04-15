package com.project;

import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ChristmasTree extends Application {
    final private static short WIDTH = 1600;

    public void start(Stage primaryStage) {
        var root = new Group();

        root.getChildren().addAll(TreeForm.getEllipse(), TreeForm.getStem());

        IntStream.range(0, TreeForm.getCrown().length)
                .forEach(i -> root.getChildren().add(TreeForm.getCrown()[i]));

        root.getChildren().add(ChristmasTreeStar.getStar());

        int balls_amount = 21;
        Circle[] balls = new Circle[balls_amount];
        Arc[] balls_light = new Arc[balls_amount];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Circle(0, 0, 18);
            balls[i].setStroke(Color.BLACK);
            balls[i].setStrokeWidth(3);
            if (i < 3)
                balls[i].setFill(Color.BLUE);
            else if (i < 6)
                balls[i].setFill(Color.ORANGE);
            else if (i < 9)
                balls[i].setFill(Paint.valueOf("#00FF00"));
            else if (i < 12)
                balls[i].setFill(Color.YELLOW);
            else if (i < 15)
                balls[i].setFill(Color.PURPLE);
            else if (i < 19)
                balls[i].setFill(Color.RED);
            else
                balls[i].setFill(Color.CYAN);
            balls_light[i] = new Arc(100, 100, 15, 15, 90, 135);
            balls_light[i].setType(ArcType.ROUND);
            balls_light[i].setStroke(Color.WHITE);
            balls_light[i].setFill(Color.WHITE);
            balls_light[i].setOpacity(0.5);
        }
        // blue balls
        setBallCoords(balls[0], 430, 250, balls_light[0]);
        setBallCoords(balls[1], 470, 460, balls_light[1]);
        setBallCoords(balls[2], 260, 505, balls_light[2]);
        // orange balls
        setBallCoords(balls[3], 370, 274, balls_light[3]);
        setBallCoords(balls[4], 410, 460, balls_light[4]);
        setBallCoords(balls[5], 405, 590, balls_light[5]);
        // lime balls
        setBallCoords(balls[6], 350, 230, balls_light[6]);
        setBallCoords(balls[7], 355, 470, balls_light[7]);
        setBallCoords(balls[8], 280, 573, balls_light[8]);
        // yellow balls
        setBallCoords(balls[9], 430, 400, balls_light[9]);
        setBallCoords(balls[10], 330, 510, balls_light[10]);
        setBallCoords(balls[11], 510, 500, balls_light[11]);
        // purple balls
        setBallCoords(balls[12], 330, 330, balls_light[12]);
        setBallCoords(balls[13], 500, 370, balls_light[13]);
        setBallCoords(balls[14], 420, 530, balls_light[14]);
        // red balls
        setBallCoords(balls[15], 465, 310, balls_light[15]);
        setBallCoords(balls[16], 305, 430, balls_light[16]);
        setBallCoords(balls[17], 340, 570, balls_light[17]);
        setBallCoords(balls[18], 545, 540, balls_light[18]);
        // cyan balls
        setBallCoords(balls[19], 380, 345, balls_light[19]);
        setBallCoords(balls[20], 466, 583, balls_light[20]);

        IntStream.range(0, balls.length)
                .forEach(i -> root.getChildren()
                        .addAll(balls[i], balls_light[i]));

        //        Animation.run(root);

        primaryStage.setTitle("Christmas Tree");
        primaryStage.setScene(new Scene(root, WIDTH, WIDTH / 2));
        primaryStage.show();
    }

    private void setBallCoords(Circle cir, int x, int y, Arc arc) {
        cir.setCenterX(x);
        cir.setCenterY(y);
        arc.setCenterX(x);
        arc.setCenterY(y);
    }

    public static void main(String... args) {
        launch(args);
    }
}