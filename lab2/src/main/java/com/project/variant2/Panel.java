package com.project.variant2;

import com.project.Frame;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private final static int FRAME_WIDTH = 1500;
    private final static int FRAME_HEIGHT = 1000;

    private static int contentWidth;
    private static int contentHeight;

    public static void main(String... args) {
        var size = Frame.run("Lab 2", FRAME_WIDTH, FRAME_HEIGHT, new Panel());
        contentWidth = size.width;
        contentHeight = size.height;
    }
}