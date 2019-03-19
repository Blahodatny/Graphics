package com.project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;

public class GraphicsHelper {
    private Graphics2D g2d;

    public GraphicsHelper(Graphics2D g2d) {
        this.g2d = g2d;
    }

    protected Graphics2D getG2d() {
        return g2d;
    }

    public void setRendering() {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
    }

    public void drawFrame(
            Color color, int cap, int join, int x, int y, int width, int height) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(16, cap, join));
        g2d.drawRect(x, y, width, height);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphicsHelper)) return false;
        var that = (GraphicsHelper) o;
        return Objects.equals(g2d, that.g2d);
    }

    public int hashCode() {
        return Objects.hash(g2d);
    }
}