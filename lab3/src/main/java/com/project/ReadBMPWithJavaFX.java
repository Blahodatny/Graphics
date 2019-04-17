package com.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ReadBMPWithJavaFX extends Application {
    final private static String BMP = "bmp/tr33.bmp";

    public void start(Stage stage) {
        var image = new Image(BMP);

        var pixelReader = image.getPixelReader();
        System.out.println("Image Width: " + image.getWidth());
        System.out.println("Image Height: " + image.getHeight());
        System.out.println(
                "Pixel Format: " + pixelReader.getPixelFormat().getType());

        // Determine the color of each pixel in the image
        for (var y = 0; y < image.getHeight(); y++)
            for (var x = 0; x < image.getWidth(); x++) {
                var color = pixelReader.getColor(x, y);
                System.out.println(
                        "\nPixel color at coordinates (" + x + "," + y + ") " +
                                color + ":");
                System.out.println(
                        "RGB = " + color.getRed() + " " +
                                color.getGreen() + " " + color.getBlue() +
                                ", Opacity = " + color.getOpacity()
                                + ", Saturation = " + color.getSaturation());
            }

        // Display image on screen
        var root = new StackPane();
        var imageView = new ImageView();
        imageView.setImage(image);
        root.getChildren().add(imageView);
        stage.setTitle("Image Read Test");
        stage.setScene(new Scene(root, image.getWidth(), image.getHeight()));
        stage.show();
    }
}