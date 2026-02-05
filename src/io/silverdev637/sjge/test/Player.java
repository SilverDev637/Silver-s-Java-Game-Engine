package io.silverdev637.sjge.test;

import static io.silverdev637.sjge.test.Main.myGame;
import static io.silverdev637.sjge.test.Main.input;

import java.awt.Color;
import java.awt.Graphics;

import io.silverdev637.sjge.Core;
import io.silverdev637.sjge.engine.Camera;
import io.silverdev637.sjge.engine.Sprite;

public class Player extends Sprite {
	
    public double x, y;
    public double size = 32;
    public double accel = 1200;
    public double xspeed = 0;
    public double yspeed = 0;
    public double maxSpeed = 350;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
    
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

    public void update(double delta) {
    	
    	double _dt = delta / 1_000_000.0;
    	
    	boolean up = input.isInputDown("up");
    	boolean down = input.isInputDown("down");
    	boolean left = input.isInputDown("left");
    	boolean right = input.isInputDown("right");
    	
    	double friction = Math.pow(0.9, _dt * 60);
    	
    	if (up)    yspeed -= accel * _dt;
        if (down)  yspeed += accel * _dt;
        if (left)  xspeed -= accel * _dt;
        if (right) xspeed += accel * _dt;

        xspeed = Math.max(-maxSpeed, Math.min(xspeed, maxSpeed));
        yspeed = Math.max(-maxSpeed, Math.min(yspeed, maxSpeed));

        if (!up && !down)  yspeed *= friction;
        if (!left && !right) xspeed *= friction;

        x += Math.round(xspeed * _dt);
        y += Math.round(yspeed * _dt);
        
        myGame.getActiveCamera().setX(x).setY(y);
        
        
        if ((x > 960 || y > 720) || (x < 0 || y < 0)) {
        	myGame.changeActiveScene(new ICS());
        }

    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) (x - (size / 2)), (int) (y - (size / 2)), (int) size, (int) size);
    }
}
