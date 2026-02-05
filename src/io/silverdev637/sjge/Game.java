package io.silverdev637.sjge;

import java.awt.Color;

import javax.swing.JFrame;

import io.silverdev637.sjge.engine.Camera;
import io.silverdev637.sjge.engine.Scene;
import io.silverdev637.sjge.input.Input;

public final class Game {
	
	private String caption = "My Game";

	private Color backgroundColor = new Color(255, 255, 255);
	protected int[] windowSize = {960, 720};
	protected int[] resolution = {960, 720};
	
	protected GamePanel canvas;
	
	protected boolean running = false;
	protected boolean skipRepaint = false;
	
	private Scene activeScene;
	private Camera activeCamera = new Camera(0, 0);
	
	
	public final void setActiveScene(Scene newActiveScene) {
		if (!running)
			activeScene = newActiveScene;
		else
			throw (new UnsupportedOperationException("Game already started. Use 'changeActiveScene()' instead"));
	}
	
	public final void changeActiveScene(Scene newActiveScene) {
		if (!running)
			throw (new UnsupportedOperationException("Game has not been started yet. Use 'setActiveScene()' instead"));
		Log.info("Changing scene...");
		activeScene = newActiveScene;
		Log.info("Loading new scene...");
		canvas.init();
		skipRepaint = true;
	}
	
	public final Scene getActiveScene() {
		return activeScene;
	}
	
	public final void changeActiveCamera(Camera newActiveCamera) {
		Log.info("Changing active camera...");
		activeCamera = newActiveCamera;
	}
	
	public final Camera getActiveCamera() {
		return activeCamera;
	}

	public final boolean isRunning() {
		return running;
	}
	
	public final void setCaption(String newCaption) {
		this.caption = newCaption;
		if (Core.window != null)
			Core.window.setTitle(newCaption);
	}
	
	public final String getCaption() {
		return this.caption;
	}
	
	public final void setBackgroundColor(Color color) {
		this.backgroundColor = color;
		try {
			Core.window.setBackground(this.backgroundColor);
			skipRepaint = true;
		} catch (NullPointerException e) {
			Log.warn("No visible window found. Skipping window updates...");
			return;
		}
	}
	
	public final Color getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public final void setWindowSize(int width, int height) {
		Log.info("Resizing main window...");
		this.windowSize = new int[] {width, height};
		try {
			Core.window.setSize(windowSize[0], windowSize[1]);
		} catch (NullPointerException e) {
			Log.warn("No visible window found. Skipping physical resizing...");
			return;
		}
	}
	
	public final void setResolution(int width, int height) {
		Core.resolutionScale = width / height;
		this.resolution = new int[] {width, height};
	}
	
	public final void start() {
		Core.game = this;
		
		Core.window = new JFrame(this.caption);
    	
    	Log.info("Starting game panel...");
    	canvas = new GamePanel();
    	canvas.setBackground(backgroundColor);
        Core.window.add(canvas);
        
        Log.info("Setting window parameters...");
        Core.window.setSize(windowSize[0], windowSize[1]);
        Core.window.setLocationRelativeTo(null);
        Core.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Log.info("Starting game...");
        canvas.start();
        
	}

	public void setInput(Input input) {
		Log.info("Loading input channel...");
		Core.input = input;
		Log.done("Input channel loaded correctly.");
	}
	
}
