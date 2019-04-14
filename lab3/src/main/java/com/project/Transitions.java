package com.project;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Transitions extends Application {

	public static void main (String args[]) {
		 launch(args);
		}
		
		public void start(Stage primaryStage) throws Exception {
			Group root = new Group();
			Scene scene = new Scene (root, 500, 500);		
			
			
			//��������� ������������ ��������� �������
			final Rectangle rect1 = new Rectangle(10, 10, 100, 100);
			rect1.setArcHeight(20);
			rect1.setArcWidth(20);
			rect1.setFill(Color.RED);
			root.getChildren().add(rect1);
			
			// ��������� ������ ���������
			FadeTransition ft = new FadeTransition(Duration.millis(3000), rect1);
			ft.setFromValue(1.0); // ������������ ����������� �������� ��������� ��'����
			ft.setToValue(0.1); // ������������ �������� �������� ��������� ��'����
			ft.setCycleCount(Timeline.INDEFINITE);
			ft.setAutoReverse(true);
			ft.play();
			
			
			
			
			// ��������� ������� ������������ � �������� ������
			final Rectangle rectPath = new Rectangle (0, 0, 40, 40); 
			rectPath.setArcHeight(10);
			rectPath.setArcWidth(10);
			rectPath.setFill(Color.BLUE);
			root.getChildren().add(rectPath);
			
			// ��������� �������� � 2 ��� ���� CubicCurveTo
			Path path = new Path();
			path.getElements().add(new MoveTo(20,20)); // �������� ��������� �������, � ��� ���������� ���������
			path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120)); // ����� �����
			path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));   // ����� �����
			
			// ��������� ������� ���� �� ��������
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(4000)); // ������������ ���� �������
			pathTransition.setPath(path); // ����'�������� ��������
			pathTransition.setNode(rectPath); // ���� ��'����, ���� ���� ����������
			pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); // �������� �������� ��'���� ��� ���
			pathTransition.setCycleCount(Timeline.INDEFINITE); // ������� �������
			pathTransition.setAutoReverse(true); // ��������� ���� �����
			pathTransition.play(); // ���������� �������
			
			
			
			
			
			//��������� ������������
			Rectangle rectParallel = new Rectangle(10,200,50, 50);
			rectParallel.setArcHeight(15);
			rectParallel.setArcWidth(15);
			rectParallel.setFill(Color.DARKBLUE);
			rectParallel.setTranslateX(50);
			rectParallel.setTranslateY(75);
			root.getChildren().add(rectParallel);		
			
			// ��������� ������ ���������
			FadeTransition fadeTransition = 
			     new FadeTransition(Duration.millis(3000), rectParallel);
			fadeTransition.setFromValue(1.0f);
			fadeTransition.setToValue(0.3f);
			fadeTransition.setCycleCount(2);
			fadeTransition.setAutoReverse(true);
			
			// ��������� ������ ����������
			TranslateTransition translateTransition =
			     new TranslateTransition(Duration.millis(2000), rectParallel);
			translateTransition.setFromX(50);
			translateTransition.setToX(350);
			translateTransition.setCycleCount(2);
			translateTransition.setAutoReverse(true);
			
			// ��������� �������� ��'����
			RotateTransition rotateTransition = 
			     new RotateTransition(Duration.millis(3000), rectParallel);
			rotateTransition.setByAngle(180f);
			rotateTransition.setCycleCount(4);
			rotateTransition.setAutoReverse(true);
			
			// ������������� ��'����
			ScaleTransition scaleTransition = 
			     new ScaleTransition(Duration.millis(2000), rectParallel);
			scaleTransition.setToX(2f);
			scaleTransition.setToY(2f);
			scaleTransition.setCycleCount(2);
			scaleTransition.setAutoReverse(true);
			
			// ��������� ��������� ���������� ���������� �������
			ParallelTransition parallelTransition = 
				new ParallelTransition();
			parallelTransition.getChildren().addAll(
			           fadeTransition,
			           translateTransition,
			           rotateTransition,
			           scaleTransition
			);
			
			parallelTransition.setCycleCount(Timeline.INDEFINITE);
			parallelTransition.play();
			  
			
			
			
			
			Rectangle rectSeq = new Rectangle(25,25,50,50);
			rectSeq.setArcHeight(15);
			rectSeq.setArcWidth(15);
			rectSeq.setFill(Color.CRIMSON);
			rectSeq.setTranslateX(50);
			rectSeq.setTranslateY(50);
            root.getChildren().add(rectSeq);	

			fadeTransition = 
			      new FadeTransition(Duration.millis(1000), rectSeq);
			fadeTransition.setFromValue(1.0f);
			fadeTransition.setToValue(0.3f);
			fadeTransition.setCycleCount(1);
			fadeTransition.setAutoReverse(true);
			         
			translateTransition =
			      new TranslateTransition(Duration.millis(2000), rectSeq);
			translateTransition.setFromX(50);
			translateTransition.setFromY(40);
			translateTransition.setToX(375);
			translateTransition.setToY(375);
			translateTransition.setCycleCount(1);
			translateTransition.setAutoReverse(true);
			         
			rotateTransition = 
			     new RotateTransition(Duration.millis(2000), rectSeq);
			rotateTransition.setByAngle(180f);
			rotateTransition.setCycleCount(4);
			rotateTransition.setAutoReverse(true);
			         
			scaleTransition = 
			     new ScaleTransition(Duration.millis(2000), rectSeq);
			scaleTransition.setFromX(1);
			scaleTransition.setFromY(1);
			scaleTransition.setToX(2);
			scaleTransition.setToY(2);
			scaleTransition.setCycleCount(1);
			scaleTransition.setAutoReverse(true);

			SequentialTransition sequentialTransition = new SequentialTransition();
			sequentialTransition.getChildren().addAll(
			     fadeTransition,
			     translateTransition
			);
			
			sequentialTransition.setCycleCount(Timeline.INDEFINITE);
			sequentialTransition.setAutoReverse(true);
	        sequentialTransition.play();
			     
	        
	        
			final Rectangle rectBasicTimeline = new Rectangle(100, 50, 100, 50);
			rectBasicTimeline.setFill(Color.RED);
			root.getChildren().add(rectBasicTimeline);
			       
			final Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.setAutoReverse(true);
			final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 300);
			final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
			timeline.getKeyFrames().add(kf);
			timeline.play();
			
			
			
			
			final Rectangle rectBasicTimeline1 = new Rectangle(200, 100, 200, 100);
			rectBasicTimeline1.setFill(Color.BROWN);
			root.getChildren().add(rectBasicTimeline1);
			final Timeline timeline1 = new Timeline();
			timeline1.setCycleCount(Timeline.INDEFINITE);
			timeline1.setAutoReverse(true);
			final KeyValue kv1 = new KeyValue(rectBasicTimeline1.xProperty(), 300,
			Interpolator.EASE_BOTH);
			final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
			timeline1.getKeyFrames().add(kf1);
			timeline1.play();
			        
			primaryStage.setScene(scene);
			primaryStage.show();
		}


}
