package com.project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;

public class GraphicsHelper {
    private final Graphics2D G2D;

    public GraphicsHelper(Graphics2D g2d) { this.G2D = g2d; }

    protected Graphics2D getG2D() { return G2D; }

    public void setRendering() {
        G2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        G2D.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );
    }

    public void drawFrame(Color color, int cap, int join, int x, int y,
            int width, int height) {
        G2D.setColor(color);
        G2D.setStroke(new BasicStroke(16, cap, join));
        G2D.drawRect(x, y, width, height);
    }

    public boolean equals(Object o) {
        return this == o || o instanceof GraphicsHelper that &&
                Objects.equals(G2D, that.G2D);
    }

    public int hashCode() { return Objects.hash(G2D); }
}