package com.project.shape;

import javafx.scene.shape.Polygon;

import java.util.Objects;

public class Triangle {
    private Polygon polygon;

    public Triangle(double middleX, double middleY, double indent) {
        /*
         * calculate half of the triangle's side length
         * to find the X coordinates of the remaining tops
         * */
        var halfLengthSide = calculateHalfSide(middleY - indent);
        this.polygon = new Polygon(
                middleX, indent,
                middleX - halfLengthSide, middleY,
                middleX + halfLengthSide, middleY
        );
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        var triangle = (Triangle) o;
        return Objects.equals(polygon, triangle.polygon);
    }

    public int hashCode() {
        return Objects.hash(polygon);
    }

    // calculates half of the triangle's side length by median length
    private static double calculateHalfSide(double medianLength) {
        return medianLength / Math.sqrt(3);
    }
}