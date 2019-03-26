package com.project.variant3;

import com.project.Window;
import com.project.GraphicsHelper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.JPanel;

class Application extends JPanel {
    private final static short FRAME_WIDTH = 1500;
    private final static short FRAME_HEIGHT = 1000;
    private final static byte INDENT = 20;

    private final static Color[] COLORS = {Color.GREEN, Color.YELLOW, Color.RED};
    private final static byte COEFFICIENT = 6;
    private final static byte RADIUS = 90;

    private static short contentWidth;
    private static short contentHeight;

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

        var helper = new GraphicsHelper(g2d);
        helper.setRendering();
        helper.drawFrame(
                Color.BLUE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                INDENT, INDENT, contentWidth - INDENT * 2, contentHeight - INDENT * 2
        );

        var middleX = contentWidth / 2;
        var middleY = contentHeight / 2;

        g2d.setPaint(new GradientPaint(20, 50, Color.RED,
                10, 300, Color.ORANGE, true));
        g2d.fillPolygon(triangle(middleX, middleY, INDENT));

        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(triangle(middleX, middleY - INDENT, INDENT * 3));

        IntStream.range(0, COLORS.length).forEach(i -> {
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
        var size = Window.configure("Lab 2", FRAME_WIDTH, FRAME_HEIGHT,
                new Application());
        contentWidth = (short) size.width;
        contentHeight = (short) size.height;
    }
}