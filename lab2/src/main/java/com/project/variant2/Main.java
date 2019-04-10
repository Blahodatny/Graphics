package com.project.variant2;

import com.project.Window;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JPanel implements ActionListener {
    private final static short FRAME_WIDTH = 1500;
    private final static short FRAME_HEIGHT = 1000;
    private final static byte INDENT = 20;

    private final static short[][] PARAMS = {
            {-165, -82, 165},
            {-105, -2, 75},
            {-65, 28, 20},
            {5, 28, 20},
            {-100, -52, 8},
            {-65, -72, 8},
            {55, -52, 8},
            {90, -72, 8}
    };

    private static short contentWidth;
    private static short contentHeight;

    private final static byte DELAY = 50;
    private final static short RADIUS = 350;
    private double theta = 0;
    private double scale = 1;
    private double delta = 0.01;

    private Main() {
        new Timer(DELAY, this).start();
    }

    public void paint(Graphics graphics) {
        var g2d = (Graphics2D) graphics;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, contentWidth, contentHeight);

        var drawer = new ShapeDrawer(g2d);
        drawer.setRendering();
        drawer.drawFrame(
                Color.BLUE,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER,
                INDENT,
                INDENT,
                contentWidth - INDENT * 2,
                contentHeight - INDENT * 2
        );

        g2d.translate(contentWidth / 2, contentHeight / 2);
        g2d.translate(RADIUS * Math.sin(theta), RADIUS * Math.cos(theta));
        g2d.scale(scale, scale);

        drawer.drawRectangles(
                PARAMS,
                Color.BLUE,
                new Color(77, 19, 21),
                Color.YELLOW
        );

        drawer.drawRoof(
                PARAMS,
                INDENT * 3,
                new GradientPaint(5, 25, Color.CYAN, 20, 2, Color.YELLOW, true)
        );
    }

    public void actionPerformed(ActionEvent e) {
        delta = scale < 0.01 || scale > 0.99 ? -delta : delta;
        scale += delta;
        theta += 0.1;
        repaint();
    }

    public static void main(String... args) {
        var size = Window.configure(
                "Lab 2",
                FRAME_WIDTH,
                FRAME_HEIGHT,
                new Main()
        );
        contentWidth = (short) size.width;
        contentHeight = (short) size.height;
    }
}