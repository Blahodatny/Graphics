package com.project.variant2;

import com.project.Frame;
import com.project.GraphicsHelper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {
    private final static short FRAME_WIDTH = 1500;
    private final static short FRAME_HEIGHT = 1000;
    private final static byte INDENT = 20;

    private final static short[][] PARAMS = {
            {0, 0, 165},
            {60, 80, 75},
            {100, 110, 20}, {170, 110, 20},
            {65, 30, 8}, {100, 10, 8}, {220, 30, 8}, {255, 10, 8}
    };
    private final static byte TOP = 60;

    private static short contentWidth;
    private static short contentHeight;

    private Timer timer;
    private static double theta = 0;

    private Panel() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        var g2d = (Graphics2D) graphics;

        var helper = new GraphicsHelper(g2d);
        helper.setRendering();
        helper.drawFrame(
                Color.BLUE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,
                INDENT, INDENT, contentWidth - INDENT * 2, contentHeight - INDENT * 2
        );

        g2d.translate(contentWidth / 2, contentHeight / 2);

        g2d.translate(200 + 50 * Math.sin(theta), 200 + 50 * Math.cos(theta));

        IntStream.range(0, PARAMS.length).forEach(i -> {
            g2d.setColor(
                    i == 0 ? Color.BLUE :
                            i == 1 ? new Color(77, 19, 21) :
                                    Color.YELLOW
            );
            g2d.fillRect(PARAMS[i][0], PARAMS[i][1],
                    (i == 0 ? 2 : i == 1 ? 3 : 1) * PARAMS[i][2], PARAMS[i][2]);
        });

        var triangle = new GeneralPath();
        triangle.moveTo(PARAMS[1][0], PARAMS[1][1]);
        Arrays.stream(new double[][]{
                {PARAMS[1][0] + PARAMS[1][2] * 3, PARAMS[1][1]},
                {PARAMS[6][0] - TOP, PARAMS[6][1] + PARAMS[6][2]},
                {PARAMS[1][0], PARAMS[1][1]}
        }).forEach(point -> triangle.lineTo(point[0], point[1]));
        triangle.closePath();

        g2d.setPaint(new GradientPaint(5, 25,
                Color.CYAN, 20, 2, Color.YELLOW, true));
        g2d.fill(triangle);
    }

    public void actionPerformed(ActionEvent e) {
        theta += 0.1;
        repaint();
    }

    public static void main(String... args) {
        var size = Frame.run("Lab 2", FRAME_WIDTH, FRAME_HEIGHT, new Panel());
        contentWidth = (short) size.width;
        contentHeight = (short) size.height;
    }
}