import com.project.shape.Triangle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private final static int WIDTH = 600;
    private final static int HEIGHT = 400;
    private final static int INDENT = 20;

    public void start(Stage stage) {
        var root = new Group();
        var scene = new Scene(root, WIDTH, HEIGHT);

        var middleX = WIDTH >> 1;
        var middleY = HEIGHT >> 1;

        var triangle = new Triangle(middleX, middleY, INDENT).getPolygon();

        var innerTriangle = new Triangle(
                middleX,
                middleY - (INDENT >> 1),
                INDENT * 2
        ).getPolygon();

        innerTriangle.setFill(Color.WHITE);

        root.getChildren().addAll(triangle, innerTriangle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}