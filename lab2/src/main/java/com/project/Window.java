package com.project;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
    public static Dimension configure(String title, int width, int height,
            Component component) {
        var frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(component);
        frame.setVisible(true);

        return frame.getContentPane().getSize();
    }
}