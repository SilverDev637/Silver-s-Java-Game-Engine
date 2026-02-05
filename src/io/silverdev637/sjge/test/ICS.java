package io.silverdev637.sjge.test;

import static io.silverdev637.sjge.test.Main.input;
import static io.silverdev637.sjge.test.Main.myGame;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import io.silverdev637.sjge.engine.GraphicsUI;
import io.silverdev637.sjge.engine.Scene;

public class ICS extends Scene {
	// ICS — Integrated Control System
	Player player = new Player(480, 360);
	
	@Override
	public void init() {
		newLayer("Object", 100);
		add(player, 5, "Object");
		myGame.getActiveCamera().setX(480).setY(360);
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, 960, 720);
	}

	@Override
	public void drawUI(GraphicsUI g) {
		g.setColor(Color.GREEN);
		g.drawString("ICS — Integrated Control System v2.0.3", 10, 20);
		
		g.drawString("Mouse x: " + input.getMouseX() + ", y: " + input.getMouseY(), 10, 40);
		
		String s = "All rights reserved @ 022";
		
		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(s);
		
		g.setAnchors(1.0, 1.0);
		g.drawString(s, -width-20, -20);
		
	}

}
