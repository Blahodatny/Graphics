package com.project.castle;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;

import static com.project.ColorPainter.getAppearence;

class CastleBody {
    static Box getTower() {
        return new Box(
                0.2f,
                0.2f,
                0.125f,
                Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,
                getAppearence("lab4/src/main/resources/image.jpg")
        );
    }
}