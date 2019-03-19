package com.project.variant2;

import com.project.Frame;
import com.project.GraphicsHelper;

import com.project.Program;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.stream.IntStream;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private final static int FRAME_WIDTH = 1500;
    private final static int FRAME_HEIGHT = 1000;
    private final static int INDENT = 20;

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

        var params = Program.getPARAMS();

        IntStream.range(0, params.length).forEach(i -> {
            g2d.setColor(i == 0 ? Color.DARK_GRAY : Color.YELLOW);
            g2d.fillRect(params[i][0], params[i][1],
                    i == 0 ? 3 * params[i][2] : params[i][2], params[i][2]);
        });
    }

    public static void main(String... args) {
        var size = Frame.run("Lab 2", FRAME_WIDTH, FRAME_HEIGHT, new Panel());
        contentWidth = size.width;
        contentHeight = size.height;
    }
}