package com.maxdev;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class BoatAnimation implements ActionListener, KeyListener {
    private TransformGroup car;
    private Transform3D transform3D = new Transform3D();

    private float x = 0;
    private float y = 0;
    
    private boolean w = false;
    private boolean s = false;
    private boolean a = false;
    private boolean d = false;
    private boolean e = false;
    private  boolean q = false;

    public BoatAnimation(TransformGroup boat) {
        this.car = boat;
        this.car.getTransform(this.transform3D);
                
        Timer timer = new Timer(20, this);
        timer.start();
    }

    private void Move() {


  	  	if (w) {
  	  		y += 0.01f;
  	  	}

  	  	if (s) {
			y -= 0.01f;

	  	}



        if (a) {
			x -= 0.01f;
        }

        if (d) {
        	x += 0.01f;
        }

		transform3D.setTranslation(new Vector3f(x, y, 0));

        if (e){
				Transform3D rotation = new Transform3D();
				rotation.rotY(0.05f);
				transform3D.mul(rotation);

		}

		if (q){

			Transform3D rotation = new Transform3D();
			rotation.rotY(-0.05f);
			transform3D.mul(rotation);

		}

        car.setTransform(transform3D);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	Move();
    }
    
    @Override
    public void keyPressed(KeyEvent ev) {
    	switch (ev.getKeyChar()) {
	    	case 'w': w = true; break;
	    	case 's': s = true; break;
	    	case 'a': a = true; break;
	    	case 'd': d = true; break;
	    	case 'e': e = true; break;
	    	case 'q': q = true; break;

		}
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    	
    }
    
    @Override
    public void keyReleased(KeyEvent ev) {
    	switch (ev.getKeyChar()) {
	    	case 'w': w = false; break;
	    	case 's': s = false; break;
	    	case 'a': a = false; break;
	    	case 'd': d = false; break;
			case 'e': e = false; break;
			case 'q': q = false; break;

    	}
    }
}
