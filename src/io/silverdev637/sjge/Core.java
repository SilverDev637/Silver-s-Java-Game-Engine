package io.silverdev637.sjge;


import javax.swing.JFrame;

import io.silverdev637.sjge.input.Input;

public final class Core {
	protected static Game game;
	
	protected static Input input;
	
	// FPS
	private static int targetFPS = 60;
	private static long frameTime = 1000 / targetFPS;
	
	protected static double resolutionScale;
	
	/**
	 * @hidden
	 */
	private static boolean scaleResolutionToWindow = false;
	
	// Window
    protected static JFrame window;
	
	public static int resolutionX() {
		if (scaleResolutionToWindow)
			game.resolution[0] = (int) (game.canvas.getWidth() / resolutionScale);
		return game.resolution[0];
	}
	public static int resolutionY() {
		if (scaleResolutionToWindow)
			game.resolution[1] = (int) (game.canvas.getHeight() * resolutionScale);
		return game.resolution[1];
	}
	
	public static int resolutionX(int resX) {
		int oldResX = game.resolution[0];
		game.resolution[0] = resX;
		return oldResX;
	}
	public static int resolutionY(int resY) {
		int oldResY = game.resolution[1];
		game.resolution[1] = resY;
		return oldResY;
	}
	
	public static int getWindowWidth() {
		return window.getWidth();
	}
	public static int getWindowHeight() {
		return window.getHeight();
	}
	
	public static int getViewportWidth() {
		return game.canvas.getWidth();
	}
	public static int getViewportHeight() {
		return game.canvas.getHeight();
	}
	
	public static double getVisibleWorldWidth() {
	    return Core.getViewportWidth() / game.canvas.worldScale;
	}

	public static double getVisibleWorldHeight() {
	    return Core.getViewportHeight() / game.canvas.worldScale;
	}

	
	protected static void updateWindowDimensions() {
		game.windowSize[0] = window.getWidth();
		game.windowSize[1] = window.getHeight();
	}
	
	public static int targetFPS() {
		return targetFPS;
	}
	
	public static void targetFPS(int newTargetFPS) {
		targetFPS = newTargetFPS;
		frameTime = 1000 / targetFPS;
	}
	
	public static long frameTime() {
		return frameTime;
	}
}
