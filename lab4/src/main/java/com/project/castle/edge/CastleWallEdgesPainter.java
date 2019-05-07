package com.project.castle.edge;

import javax.media.j3d.TransformGroup;

public class CastleWallEdgesPainter extends Edge {
    private static final float TOOTH_SIDE_LENGTH = 0.008f;
    private static final float[][] COORDINATES = new float[][]{
            {0.592f, .0f, 0.38f},
            {0.992f, .0f, 0.133f},
            {0.992f, 0.4f, 0.133f},
            {0.992f, -0.4f, 0.133f}
    };

    float getToothSideLength() { return TOOTH_SIDE_LENGTH; }

    public float[][] getVectorCoordinates() { return COORDINATES; }

    public TransformGroup getBuildCallback(float x, float y, float z,
            boolean rotate) {
        return buildEdge(x, y, z, rotate);
    }
}