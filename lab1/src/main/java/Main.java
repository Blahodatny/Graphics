import com.project.shape.Triangle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {
    private final static int MIDDLE_X = 300;
    private final static int MIDDLE_Y = 200;
    private final static int INDENT = 20;

    private final static Color[] COLORS = {Color.GREEN, Color.YELLOW, Color.RED};
    private final static double COEFFICIENT = 1.78;
    private final static int RADIUS = 14;

    public void start(Stage stage) {
        var root = new Group();
        var scene = new Scene(root, MIDDLE_X * 2, MIDDLE_Y * 2);
        scene.setFill(Color.GRAY);

        root.getChildren().addAll(
                new Triangle(MIDDLE_X, MIDDLE_Y, INDENT, Color.RED).getPolygon(),
                new Triangle( // draws inner triangle
                        MIDDLE_X,
                        MIDDLE_Y - INDENT,
                        INDENT * 3,
                        Color.WHITE
                ).getPolygon(),
                new Rectangle( // draws column
                        MIDDLE_X - (INDENT >> 1),
                        MIDDLE_Y,
                        INDENT,
                        MIDDLE_Y - INDENT
                )
        );

        IntStream // draw circles
                .range(0, COLORS.length)
                .forEach(i -> root.getChildren().add(
                        new Circle(
                                MIDDLE_X,
                                MIDDLE_Y - INDENT * (i + 1) * COEFFICIENT,
                                RADIUS,
                                COLORS[i]
                        )
                ));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}