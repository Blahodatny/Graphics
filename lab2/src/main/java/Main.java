import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Main extends JPanel implements ActionListener {
    private final static int FRAME_WIDTH = 1500;
    private final static int FRAME_HEIGHT = 1000;
    private final static int INDENT = 20;

    private final static Color[] COLORS = {Color.GREEN, Color.YELLOW, Color.RED};
    private final static int COEFFICIENT = 2;
    private final static int RADIUS = 20;

    private static int contentWidth;
    private static int contentHeight;

    private Timer timer;

    private double theta = 0;
    private double change = 0.1;

    private Main() {
        timer = new Timer(10, this);
        timer.start();
    }

    private static Polygon triangle(int topX, int topY, int indent) {
        var halfLengthSide = (topY - indent) / 2;
        return new Polygon(
                new int[]{topX, topX - halfLengthSide, topX + halfLengthSide},
                new int[]{indent, topY, topY},
                3
        );
    }

    public void paint(Graphics graphics) {
        var g2d = (Graphics2D) graphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(
                new BasicStroke(16, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
        );
        g2d.drawRect(INDENT, INDENT,
                contentWidth - INDENT * 2, contentHeight - INDENT * 2);

        g2d.translate(contentWidth / 2, contentHeight / 2);
       double tx = 200 + 50 * Math.cos(theta);
        double ty = 200 + 50 * Math.sin(theta);

        g2d.translate(tx, ty);

        var middleX = contentWidth / 10;
        var middleY = contentHeight / 5;

        g2d.setPaint(new GradientPaint(20, 50, Color.RED,
                10, 300, Color.ORANGE, true));
//        g2d.fillPolygon(triangle(middleX, middleY, INDENT));

        g2d.fillPolygon(triangle(0, 0, 20));

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

    public void actionPerformed(ActionEvent e) {
        theta += change;
        repaint();
    }
}