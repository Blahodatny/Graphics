package com.project;

import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;

public class ColorPainter {
    // світло, що випромінюється
    private static final Color3f EMISSIVE = new Color3f(new Color(0x4C0E51));
    // навколишнє світло
    private static final Color3f AMBIENT = new Color3f(new Color(0x5B070C));
    // світло, що розсіюється
    private static final Color3f DIFFUSE = new Color3f(new Color(0xD3DB23));
    // світло, що відбивається
    private static final Color3f SPECULAR = new Color3f(new Color(0x1A1A46));
    private static final Color4f BOUNDARY = new Color4f(0.0f, 1.0f, 1.0f, 0.0f);

    public static Appearance getAppearance() {
        var appearance = new Appearance();
        appearance.setMaterial(
                new Material(AMBIENT, EMISSIVE, DIFFUSE, SPECULAR, 1.0f));
        return appearance;
    }

    public static Appearance getAppearance(String file) {
        var texture = new TextureLoader(file, "LUMINANCE", new Container())
                .getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(BOUNDARY);
        var attributes = new TextureAttributes();
        attributes.setTextureMode(TextureAttributes.MODULATE);
        var appearence = getAppearance();
        appearence.setTexture(texture);
        appearence.setTextureAttributes(attributes);
        return appearence;
    }
}