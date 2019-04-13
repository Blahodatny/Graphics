package com.project;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChristmasTree extends Application {
    public static void main (String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene (root, 1600, 800);

        // prop drawing
        Rectangle prop_under = new Rectangle(330, 625, 140, 75);
        prop_under.setArcHeight(68);
        prop_under.setArcWidth(140);
        prop_under.setFill(Paint.valueOf("#D2B48C"));
        prop_under.setStroke(Color.BLACK);
        prop_under.setStrokeWidth(3);
        root.getChildren().add(prop_under);

        Ellipse prop = new Ellipse(400, 655, 70, 35);
        prop.setFill(Paint.valueOf("#DEB887"));
        prop.setStroke(Color.BLACK);
        prop.setStrokeWidth(3);
        root.getChildren().add(prop);

        Arc prop_light_up_arc = new Arc(400, 655, 68, 33, 100, 170);
        prop_light_up_arc.setType(ArcType.ROUND);
        prop_light_up_arc.setFill(Paint.valueOf("#F5DEB3"));
        Arc prop_light_down_arc = new Arc(400, 655, 45, 33, 100, 170);
        prop_light_down_arc.setType(ArcType.ROUND);
        prop_light_down_arc.setFill(Paint.valueOf("#DEB887"));
        root.getChildren().addAll(prop_light_up_arc, prop_light_down_arc);

        // trunk drawing
        Rectangle trunk = new Rectangle(370, 580, 60, 85);
        trunk.setArcHeight(30);
        trunk.setArcWidth(80);
        trunk.setFill(Color.BROWN);
        trunk.setStroke(Color.BLACK);
        trunk.setStrokeWidth(3);
        root.getChildren().add(trunk);

        // spruce spines drawing
        Arc arc_down_body = new Arc(400, 350, 300, 250, 227, 86);
        arc_down_body.setType(ArcType.ROUND);
        arc_down_body.setStroke(Color.BLACK);
        arc_down_body.setFill(Color.GREEN);
        arc_down_body.setStrokeWidth(3);
        Arc arc_middle_body = new Arc(400, 250, 250, 225, 230, 80);
        arc_middle_body.setType(ArcType.ROUND);
        arc_middle_body.setStroke(Color.BLACK);
        arc_middle_body.setFill(Color.GREEN);
        arc_middle_body.setStrokeWidth(3);
        Arc arc_up_body = new Arc(400, 150, 250, 200, 240, 60);
        arc_up_body.setType(ArcType.ROUND);
        arc_up_body.setStroke(Color.BLACK);
        arc_up_body.setFill(Color.GREEN);
        arc_up_body.setStrokeWidth(3);
        root.getChildren().addAll(arc_down_body, arc_middle_body, arc_up_body);

        // star drawing
        double hypotenuse = 50;
        double big_leg = hypotenuse * Math.sin(Math.toRadians(54));
        double small_leg = hypotenuse * Math.cos(Math.toRadians(54));
        double start_x = 360;
        double start_y = 140;
        double middle_big_leg = (2*big_leg) * Math.cos(Math.toRadians(36));
        double middle_small_leg = (2*big_leg) * Math.sin(Math.toRadians(36));
        System.out.println("big_leg = " + big_leg);
        System.out.println("small_leg = " + small_leg);
        System.out.println("middle_big_leg = " + middle_big_leg);
        System.out.println("middle_small_leg = " + middle_small_leg);
        Path path = new Path();
        MoveTo moveTo = new MoveTo(start_x, start_y);
        LineTo line1 = new LineTo(start_x + big_leg*2, start_y);
        LineTo line2 = new LineTo(start_x + (2*big_leg - middle_big_leg), start_y + middle_small_leg);
        LineTo line3 = new LineTo(start_x + big_leg, start_y - small_leg);
        LineTo line4 = new LineTo(start_x + middle_big_leg, start_y + middle_small_leg);
        LineTo line5 = new LineTo(start_x, start_y);
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2, line3, line4, line5);
        path.setFill(Color.RED);
        path.setStrokeWidth(3);
        root.getChildren().add(path);

        int balls_amount = 21;
        Circle [] balls = new Circle[balls_amount];
        Arc [] balls_light = new Arc[balls_amount];
        for (int i=0; i<balls.length; i++){
            balls[i] = new Circle(0, 0, 18);
            balls[i].setStroke(Color.BLACK);
            balls[i].setStrokeWidth(3);
            if (0 <= i && i < 3){
                balls[i].setFill(Color.BLUE);
            }else if(3 <= i && i < 6){
                balls[i].setFill(Color.ORANGE);
            }else if(6 <= i && i < 9){
                balls[i].setFill(Paint.valueOf("#00FF00"));
            }else if(9 <= i && i < 12){
                balls[i].setFill(Color.YELLOW);
            }else if(12 <= i && i < 15){
                balls[i].setFill(Color.PURPLE);
            }else if(15 <= i && i < 19){
                balls[i].setFill(Color.RED);
            }else if(19 <= i && i < 21){
                balls[i].setFill(Color.CYAN);
            }
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

        for (int i=0; i<balls.length; i++){
            root.getChildren().addAll(balls[i], balls_light[i]);
        }

        // Animation
        int cycleCount = 2; //
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(-1f);
        scaleTransition.setToY(1f);
        scaleTransition.setCycleCount(cycleCount);
        scaleTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(50);
        translateTransition.setToX(750);
        translateTransition.setCycleCount(cycleCount+2);
        translateTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                translateTransition,
                scaleTransition,
                rotateTransition
        );

        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();

        primaryStage.setTitle("Christmas Tree 9000");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setBallCoords(Circle cir, int x, int y, Arc arc){
        cir.setCenterX(x);
        cir.setCenterY(y);
        arc.setCenterX(x);
        arc.setCenterY(y);
    }
}