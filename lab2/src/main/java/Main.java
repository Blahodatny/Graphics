import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Main extends JPanel {
    private final static int FRAME_WIDTH = 1500;
    private final static int FRAME_HEIGHT = 1000;
    private final static int INDENT = 20;

    private final static Color[] COLORS = {Color.GREEN, Color.YELLOW, Color.RED};
    private final static int COEFFICIENT = 6;
    private final static int RADIUS = 75;

    private static int contentWidth;
    private static int contentHeight;

    private static Polygon triangle(int middleX, int middleY, int indent) {
        var halfLengthSide = (middleY - indent) / 2;
        return new Polygon(
                new int[]{middleX, middleX - halfLengthSide, middleX + halfLengthSide},
                new int[]{indent, middleY, middleY},
                3
        );
    }

    public void paint(Graphics graphics) {
        var g2d = (Graphics2D) graphics;

        var rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        var middleX = contentWidth / 4;
        var middleY = contentHeight / 2;

        g2d.setPaint(new GradientPaint(20, 50, Color.RED,
                10, 300, Color.ORANGE, true));
        g2d.fillPolygon(triangle(middleX, middleY, INDENT));

        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(triangle(middleX, middleY - INDENT, INDENT * 3));

        IntStream // draw circles
                .range(0, COLORS.length)
                .forEach(i -> {
                    g2d.setColor(COLORS[i]);
                    g2d.fillOval(
                            middleX - RADIUS / 2,
                            middleY - INDENT * (i + 1) * COEFFICIENT,
                            RADIUS,
                            RADIUS
                    );
                });

        var x1 = middleX - (INDENT >> 1);
        var y3 = 2 * middleY - INDENT;

        var rectangle = new GeneralPath();
        rectangle.moveTo(x1, middleY);
        Arrays.stream(new double[][]{
                {x1 + INDENT, middleY},
                {x1 + INDENT, y3},
                {x1, y3},
                {x1, middleY}
        }).forEach(point -> rectangle.lineTo(point[0], point[1]));
        rectangle.closePath();

        g2d.setColor(Color.BLACK);
        g2d.fill(rectangle);
    }

    public static void main(String... args) {
        var frame = new JFrame("Lab 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Main());
        frame.setVisible(true);

        var size = frame.getContentPane().getSize();
        contentWidth = size.width;
        contentHeight = size.height;
    }
}