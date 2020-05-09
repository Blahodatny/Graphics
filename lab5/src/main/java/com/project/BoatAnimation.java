package com.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Vector3f;

class BoatAnimation implements ActionListener, KeyListener {
    private static final float DISTANCE = 0.01f;

    private final TransformGroup BOAT;
    private final Transform3D TRANSFORM3D = new Transform3D();

    private float x = 0;
    private float y = 0;

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean fromLeftToRight = false;
    private boolean fromRightToLeft = false;

    BoatAnimation(TransformGroup boat) {
        this.BOAT = boat;
        this.BOAT.getTransform(this.TRANSFORM3D);
        new Timer(20, this).start();
    }

    private void Move() {
        x = left ? x - DISTANCE : right ? x + DISTANCE : x;
        y = up ? y + DISTANCE : down ? y - DISTANCE : y;
        TRANSFORM3D.setTranslation(new Vector3f(x, y, 0));

        if (fromLeftToRight || fromRightToLeft) {
            var rotation = new Transform3D();
            rotation.rotY(fromLeftToRight ? 0.05f : -0.05f);
            TRANSFORM3D.mul(rotation);
        }
        BOAT.setTransform(TRANSFORM3D);
    }

    private void setKey(char key, boolean value) {
        if (key == 'w' || key == 'ц')
            up = value;
        else if (key == 's' || key == 'ы' || key == 'і')
            down = value;
        else if (key == 'a' || key == 'ф')
            left = value;
        else if (key == 'd' || key == 'в')
            right = value;
        else if (key == 'e' || key == 'у')
            fromLeftToRight = value;
        else if (key == 'q' || key == 'й')
            fromRightToLeft = value;
    }

    public void actionPerformed(ActionEvent e) { Move(); }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) { setKey(e.getKeyChar(), true); }

    public void keyReleased(KeyEvent e) { setKey(e.getKeyChar(), false); }
}