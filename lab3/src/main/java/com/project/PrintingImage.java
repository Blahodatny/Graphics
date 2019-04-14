package com.project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrintingImage extends Application{

	private HeaderBitmapImage image; // �������� ����, ��� ������ ��'��� � ����������� ��� ��������� ����������
	private int numberOfPixels; // �������� ���� ��� ���������� ������� ������ � ������ ��������
	
	public PrintingImage()
	{}
	
	public PrintingImage(HeaderBitmapImage image) // �������������� ����������� �����������
	{
		this.image = image;
	}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ReadingImageFromFile.loadBitmapImage("./lab3/src/main/resources" +
				"/bmp/trajectory.bmp");
		this.image = ReadingImageFromFile.pr.image;
		int width = (int)this.image.getWidth();
		int height = (int)this.image.getHeight();
		int half = (int)image.getHalfOfWidth();
		
		Group root = new Group();	
		Scene scene = new Scene (root, width, height);
		scene.setFill(Color.BLACK);
		Circle cir;
		
		int let = 0;
		int let1 = 0;
		int let2 = 0;
		char[][] map = new char[width][height];
		// �������� ���������� ����� ��� �����
		BufferedInputStream reader = new BufferedInputStream (new FileInputStream("pixels.txt"));
		
	
		for(int i=0;i<height;i++)     // ���� �� ����� ���������� �� �����
		   { 
		   for(int j=0;j<half;j++)         // ���� �� ����� ���������� �� ������
		   {
		          let = reader.read();  // ������� ���� ������ � �����
		          let1=let;
		          let2=let;    
		          let1=let1&(0xf0);   // ������� ���� - ������ ������
		          let1=let1>>4;       // ���� �� 4 ������� 
		          let2=let2&(0x0f);   // �������� ���� - ������ ������          
		           if(j*2<width) // ��� �� 1 ������ ���� 2 ����� ��� ��������� ������ �� �������� ������ ����������
		           {  
		                cir = new Circle ((j)*2,(height-1-i),1, Color.valueOf((returnPixelColor(let1)))); // �� ��������� ������������ 
		                // �������� ���� ������� � 1 ������ �� �������� ���������� �� ��������� ������ returnPixelColor ������� ������
		    			//root.getChildren().add(cir); //������ ��'��� � �����	
		    			 if (returnPixelColor(let1) == "BLACK") // ���� ���� ������ ������, �� ������� � ����� 1
		                 {
		                     map[j*2][height-1-i] = '1';
		                     numberOfPixels++; // �������� ������� ������ ������
		                 }
		                 else
		                 {
		                     map[j*2][height-1-i] = '0'; 
		                 }
		           }
		           if(j*2+1<width) // ��� ������� ������
		           {   
		               cir = new Circle ((j)*2+1,(height-1-i),1,Color.valueOf((returnPixelColor(let2))));
		    		   //root.getChildren().add(cir);
		    		   if (returnPixelColor(let2) == "BLACK")
		                {
		                    map[j*2+1][height-1-i] = '1';
		                    numberOfPixels++;
		                }
		                else
		                {                    
		                    map[j*2+1][height-1-i] = '0'; 
		                }
		           }
		   }
		   }
		primaryStage.setScene(scene); // ���������� �����
	    primaryStage.show(); // ��������� �����
	    
		reader.close();
		
		int[][] black;
		black = new int[numberOfPixels][2];	
		int lich = 0;
		
		BufferedOutputStream writer = new BufferedOutputStream (new FileOutputStream("map.txt")); // �������� ����� ��� ���� �� �������� � ����
		for(int i=0;i<height;i++)     // ���� �� ����� ���������� �� �����
		{ 
		   for(int j=0;j<width;j++)         // ���� �� ����� ���������� �� ������
		   {
			   if (map[j][i] == '1')
			   {
				   black[lich][0] = j;
				   black[lich][1] = i;
				   lich++;
			   }
			   writer.write(map[j][i]);
		   }
		   writer.write(10);
		}
		writer.close();
		
		System.out.println("number of black color pixels = " + numberOfPixels);
		
		Path path2 = new Path();
		for (int l=0; l<numberOfPixels-1; l++)
		{
		   path2.getElements().addAll(
				new MoveTo(black[l][0],black[l][1]),
				new LineTo (black[l+1][0],black[l+1][1])
				);
		}
		
		final Rectangle rectPath = new Rectangle (0, 0, 60, 60);
		rectPath.setArcHeight(10);
		rectPath.setArcWidth(10);
		rectPath.setFill(Color.ORANGE);
		root.getChildren().add(rectPath);
		
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(5000));
		pathTransition.setPath(path2);
		pathTransition.setNode(rectPath);
		pathTransition.play();
		
		Arc arc1 = new Arc(40,340,40,40,30,200);
		arc1.setFill(Color.YELLOW);
		root.getChildren().add(arc1);
		
		Arc arc2 = new Arc(42,340,40,40,160,170);
		arc2.setFill(Color.YELLOW);
		root.getChildren().add(arc2);
		
		Circle circ = new Circle (40,340,10, Color.WHITE);
		circ.setStroke(Color.BLACK);
		circ.setStrokeWidth(2);
		root.getChildren().add(circ);
		
		Arc arc3 = new Arc(40,340,10,10,30,150);
		arc1.setFill(Color.YELLOW);
		root.getChildren().add(arc3);
		
		Circle cir1 = new Circle (40,340,3, Color.BLACK);
		root.getChildren().add(cir1);
		
		RotateTransition rotForArc1 = 
			     new RotateTransition(Duration.millis(500), arc1);
		rotForArc1.setByAngle(20f);
		rotForArc1.setCycleCount(20);
		rotForArc1.setAutoReverse(true);
			
		RotateTransition rotForArc2 = 
			     new RotateTransition(Duration.millis(500), arc2);
		rotForArc2.setByAngle(-20f);
		rotForArc2.setCycleCount(20);
		rotForArc2.setAutoReverse(true);
		
		TranslateTransition tran = new TranslateTransition(Duration.seconds(4.0),arc3);
		tran.setFromX(0);
		tran.setToX(200);
		tran.play();
		
		TranslateTransition tran4 = new TranslateTransition(Duration.seconds(4.0),cir1);
		tran4.setFromX(0);
		tran4.setToX(200);
		tran4.play();
				
		TranslateTransition tran1 = new TranslateTransition(Duration.seconds(4.0),arc1);
		tran1.setFromX(0);
		tran1.setToX(200);
		tran1.play();
		
		TranslateTransition tran2 = new TranslateTransition(Duration.seconds(4.0),arc2);
		tran2.setFromX(0);
		tran2.setToX(200);
		tran2.play();
		
		TranslateTransition tran3 = new TranslateTransition(Duration.seconds(4.0),circ);
		tran3.setFromX(0);
		tran3.setToX(200);
		tran3.play();
		 
		FadeTransition fadeTransition = 
		            new FadeTransition(Duration.seconds(12.0), rectPath);
		        fadeTransition.setFromValue(1.0f);
		        fadeTransition.setToValue(0.0f);
		        fadeTransition.setCycleCount(1);
		        
		        ScaleTransition scaleTransition = 
				        new ScaleTransition(Duration.seconds(20.0), rectPath);
				scaleTransition.setToX(-1f);
				scaleTransition.setToY(-1f);        
		
		
		ParallelTransition parallelTransition = 
				new ParallelTransition();
			parallelTransition.getChildren().addAll(
			           tran,
			           tran1,
			           tran2,
			           tran3,
			           tran4,
			           fadeTransition,
			           rotForArc1,
			           rotForArc2,
			           scaleTransition
			);
			
			parallelTransition.play();	
			
			
	}
	
	private String returnPixelColor (int color) // ����� ��� ������������ ������� 16-������ ����������
	{
		String col = "BLACK";
		switch(color)
		   {
		      case 0: return "BLACK";     //BLACK;
		      case 1: return "LIGHTCORAL";  //LIGHTCORAL;
		      case 2: return "GREEN";     //GREEN
		      case 3: return "BROWN";     //BROWN
		      case 4: return "BLUE";      //BLUE;
		      case 5: return "MAGENTA";   //MAGENTA;
		      case 6: return "CYAN";      //CYAN;
		      case 7: return "LIGHTGRAY"; //LIGHTGRAY;
		      case 8: return "DARKGRAY";  //DARKGRAY;
		      case 9: return "RED";       //RED;
		      case 10:return "LIGHTGREEN";//LIGHTGREEN
		      case 11:return "YELLOW";    //YELLOW;
		      case 12:return "LIGHTBLUE"; //LIGHTBLUE;
		      case 13:return "LIGHTPINK";    //LIGHTMAGENTA
		      case 14:return "LIGHTCYAN";    //LIGHTCYAN;
		      case 15:return "WHITE";    //WHITE;
		   }
		   return col;
	}
		
	public static void main (String args[]) 
	{
	   launch(args);
	}

}
