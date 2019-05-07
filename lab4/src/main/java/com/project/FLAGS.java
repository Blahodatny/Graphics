package com.project;

import com.sun.j3d.utils.geometry.Primitive;

public final class FLAGS {
    public static int get() {
        return Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
    }
}