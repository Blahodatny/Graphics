package com.project.variant2;

import com.project.GraphicsHelper;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Arrays;
import java.util.stream.IntStream;

class ShapeDrawer extends GraphicsHelper {
    ShapeDrawer(Graphics2D g2d) { super(g2d); }

    void drawRectangles(short[][] params, Color rectangle, Color house,
            Color square) {
        IntStream.range(0, params.length)
                .forEach(i -> {
                    getG2D().setColor(i == 0 ?
                            rectangle :
                            i == 1 ? house : square);
                    getG2D().fillRect(
                            params[i][0],
                            params[i][1],
                            (i == 0 ? 2 : i == 1 ? 3 : 1) * params[i][2],
                            params[i][2]
                    );
                });
    }

    void drawRoof(short[][] params, int indent, GradientPaint paint) {
        var triangle = new GeneralPath();
        triangle.moveTo(params[1][0], params[1][1]);
        Arrays.stream(new double[][]{
                {params[1][0] + params[1][2] * 3, params[1][1]},
                {params[6][0] - indent, params[6][1] + params[6][2]},
                {params[1][0], params[1][1]}
        }).forEach(point -> triangle.lineTo(point[0], point[1]));
        triangle.closePath();

        getG2D().setPaint(paint);
        getG2D().fill(triangle);
    }
}