package io.silverdev637.sjge.engine;

import java.awt.Graphics;

public abstract class Sprite {
	
	String name = null;
	
	public Sprite() {}
	
	public abstract void init();

	public abstract void update(double delta);
	
	public abstract void draw(Graphics g);
	
	@Override
	public final String toString() {
		if (name != null)
			return name;
		return super.toString();
	}
	
}
