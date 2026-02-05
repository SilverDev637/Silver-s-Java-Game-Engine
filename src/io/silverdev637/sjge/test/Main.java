package io.silverdev637.sjge.test;

import java.awt.Color;
import java.awt.event.KeyEvent;

import io.silverdev637.sjge.Game;
import io.silverdev637.sjge.input.Input;
import io.silverdev637.sjge.input.InputMap;

public class Main {
	
	public static Game myGame;
	
	public static Input input = new Input();
	
    public static void main(String[] args) {
    	myGame = new Game();
    	
    	loadInputMaps();
    	
    	myGame.setActiveScene(new ICS());
    	myGame.setInput(input);
    	myGame.setCaption("SJGE - Test");
    	myGame.setBackgroundColor(new Color(24, 24, 24));
    	myGame.setWindowSize(960, 720);
    	myGame.setResolution(960, 720);
    	myGame.start();
    }
    
    static void loadInputMaps() {
    	input.clearInputMaps();
    	input.addInputMap(new InputMap("up", KeyEvent.VK_W, KeyEvent.VK_UP));
    	input.addInputMap(new InputMap("down", KeyEvent.VK_S, KeyEvent.VK_DOWN));
    	input.addInputMap(new InputMap("left", KeyEvent.VK_A, KeyEvent.VK_LEFT));
    	input.addInputMap(new InputMap("right", KeyEvent.VK_D, KeyEvent.VK_RIGHT));
    	input.addInputMap(new InputMap("interact", KeyEvent.VK_E, KeyEvent.VK_SPACE));
    }
}
