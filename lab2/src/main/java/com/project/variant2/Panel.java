package com.project.variant2;

import com.project.Frame;
import com.project.GraphicsHelper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.stream.IntStream;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private final static short FRAME_WIDTH = 1500;
    private final static short FRAME_HEIGHT = 1000;
    private final static byte INDENT = 20;

    private final static short[][] PARAMS = {
            {50, 175, 100},
            {130, 210, 45}, {300, 260, 45},
            {20, 90, 20}, {110, 40, 20}, {350, 90, 20}, {440, 40, 20}
    };
    private final static byte TOP = 70;

    private static int contentWidth;
    private static int contentHeight;

    public void paint(Graphics graphics) {
        var g2d = (Graphics2D) graphics;

        var helper = new GraphicsHelper(g2d);
        helper.setRendering();
        helper.drawFrame(
                Color.BLUE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,
                INDENT, INDENT, contentWidth - INDENT * 2, contentHeight - INDENT * 2
        );

//        g2d.translate(contentWidth / 3, contentHeight / 3);

        IntStream.range(0, PARAMS.length).forEach(i -> {
            g2d.setColor(i == 0 ? Color.DARK_GRAY : Color.YELLOW);
            g2d.fillRect(PARAMS[i][0], PARAMS[i][1],
                    i == 0 ? 3 * PARAMS[i][2] : PARAMS[i][2], PARAMS[i][2]);
        });
    }

    public static void main(String... args) {
        var size = Frame.run("Lab 2", FRAME_WIDTH, FRAME_HEIGHT, new Panel());
        contentWidth = size.width;
        contentHeight = size.height;
    }
}