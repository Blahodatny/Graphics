import com.project.shape.Triangle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {
    private final static int WIDTH = 600;
    private final static int HEIGHT = 400;
    private final static int INDENT = 20;
    private final static Color[] COLORS = {Color.GREEN, Color.YELLOW, Color.RED};

    public void start(Stage stage) {
        var root = new Group();
        var scene = new Scene(root, WIDTH, HEIGHT);

        var middleX = WIDTH >> 1;
        var middleY = HEIGHT >> 1;

        var triangle = new Triangle(middleX, middleY, INDENT, Color.RED);

        var innerTriangle = new Triangle(
                middleX,
                middleY - INDENT,
                INDENT * 3,
                Color.WHITE
        );

        root.getChildren().addAll(triangle.getPolygon(), innerTriangle.getPolygon());

        IntStream
                .range(0, COLORS.length)
                .forEach(i -> root.getChildren().add(
                        new Circle(
                                middleX,
                                middleY - INDENT * (i + 1) * 2,
                                10,
                                COLORS[i])
                ));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}