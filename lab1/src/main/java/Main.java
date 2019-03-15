import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Main extends Application {
    private final static int WIDTH = 600;
    private final static int HEIGHT = 400;
    private final static int INDENT = 10;

    public void start(Stage stage) {
        var root = new Group();
        var scene = new Scene(root, WIDTH, HEIGHT);

        var middleX = WIDTH >> 1;
        var middleY = HEIGHT >> 1;
        var medianLength = middleY - INDENT;
        var polygon = new Polygon(
                middleX, INDENT,
                middleX - medianLength / Math.sqrt(3), middleY,
                middleX + medianLength / Math.sqrt(3), middleY
        );
        root.getChildren().add(polygon);

        var polygon1 = new Polygon(

        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}