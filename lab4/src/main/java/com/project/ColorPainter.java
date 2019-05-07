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
    private static Color3f emissive = new Color3f(new Color(0x4C0E51));
    // навколишнє світло
    private static Color3f ambient = new Color3f(new Color(0x5B070C));
    // світло, що розсіюється
    private static Color3f diffuse = new Color3f(new Color(0xD3DB23));
    // світло, що відбивається
    private static Color3f specular = new Color3f(new Color(0x1A1A46));
    private static Color4f boundary = new Color4f(0.0f, 1.0f, 1.0f, 0.0f);

    public static Appearance getAppearence() {
        var appearance = new Appearance();
        appearance.setMaterial(
                new Material(ambient, emissive, diffuse, specular, 1.0f));
        return appearance;
    }

    public static Appearance getAppearence(String file) {
        var texture = new TextureLoader(file, "LUMINANCE", new Container())
                .getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(boundary);
        var attributes = new TextureAttributes();
        attributes.setTextureMode(TextureAttributes.MODULATE);
        var appearence = getAppearence();
        appearence.setTexture(texture);
        appearence.setTextureAttributes(attributes);
        return appearence;
    }
}