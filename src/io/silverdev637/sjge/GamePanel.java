package io.silverdev637.sjge;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.util.Map.Entry;
import javax.swing.JPanel;

import io.silverdev637.sjge.engine.GraphicsUI;
import io.silverdev637.sjge.engine.Layer;

final class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -2033176178334552546L;
	
	private Thread gameThread;
	
	protected double worldScale;
	private int worldOffsetX;
	private int worldOffsetY;

	
	// Start engine
	public void start() {
		if (Core.game.isRunning()) {
			System.out.println("Game already started. Skipping this starting sequence...");
			return;
		}

	    Log.info("Loading game resources...");
	    
	    load();
	    
		init();
		
		Core.window.setVisible(true);
		recalcViewport();
	    Log.info("Requesting focus...");
	    Core.window.setFocusable(true);
        Core.window.requestFocus();
		
        Core.game.running = true;
		Log.info("Starting game thread...");
		gameThread = new Thread(this);
		gameThread.start();
		Log.done("Game thread started at " + Core.targetFPS() + " fps.");
		Log.info("Game thread running at " + String.valueOf(1000d / (double) Core.targetFPS()) + "ms.");
	}
	
	// Load all resources
	public void load() {
		int count = 0;
	    
	    Log.info("Registering listeners:");
        Log.info("Registering key listener...");
        Core.window.addKeyListener(Core.input);
        count++;
        
        Log.info("Registering mouse listener...");
        Core.window.addMouseListener(Core.input);
        count++;
        
        Log.info("Registering mouse motion listener...");
        Core.window.addMouseMotionListener(Core.input);
        count++;
        
        Log.info("Registering mouse wheel listener...");
        Core.window.addMouseWheelListener(Core.input);
        count++;
        
        Log.info("Registering component resized listener");
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	Core.updateWindowDimensions();
            	recalcViewport();
            }
        });
        count++;
	    
	    
		// load images
		// load sounds
		// load fonts
		// read Configuration
	    
	    if (count > 0) Log.done("Resources loaded: " + count);
	    else Log.info("Resources loaded: " + count);
	}
	
	
	// Initializate game logic, global variables, etc
	public void init() {
		// Game initial logic
		
		try { Core.game.getActiveScene().init(); }
		catch (Exception e) {
			Log.error("No active scene found", e);
		}
	}

	
	// Run game code
	@Override
	public void run() {
	    long start;
	    long elapsed;
	    long sleep;

	    long lastTime = System.nanoTime();
	    long deltaTime;
	    
	    while (Core.game.isRunning()) {
	        start = System.currentTimeMillis();

	        // ---- deltaTime ----
	        long now = System.nanoTime();
	        deltaTime = (now - lastTime) / 1_000;
	        lastTime = now;
	        // -------------------

	        Core.game.getActiveScene().update(deltaTime);
	        for (Entry<Integer, Layer> entry : Core.game.getActiveScene().layers.entrySet()) {
	            Layer layer = entry.getValue();
	            layer.update(deltaTime);
	        }
	        
	        if (!Core.game.skipRepaint) repaint();
	        else Log.warn("Skipping repaint...");
	        Core.game.skipRepaint = false;
	        
	        Core.input.update();

	        elapsed = System.currentTimeMillis() - start;
	        sleep = Core.frameTime() - elapsed;

	        if (sleep > 0) {
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                Log.error("Sleep interrupted on thread execution", e);
	            }
	        }
	    }
	}

	
	// Paint
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;
	    AffineTransform original = g2.getTransform();

	    // ---------- WORLD ----------
	    double visibleW = Core.resolutionX();
	    double visibleH = Core.resolutionY();


	    g2.translate(worldOffsetX, worldOffsetY);
	    g2.scale(worldScale, worldScale);

	    // cámara centrada
	    g2.translate(
	        -Core.game.getActiveCamera().getX() + visibleW / 2,
	        -Core.game.getActiveCamera().getY() + visibleH / 2
	    );


	    for (Layer layer : Core.game.getActiveScene().layers.values()) {
	        layer.draw(g2);
	    }

	    Core.game.getActiveScene().draw(g2);

	    // ---------- RESTORE ----------
	    g2.setTransform(original);

	    GraphicsUI g3 = new GraphicsUI(g2);
	    
	    Core.game.getActiveScene().drawUI(g3);
	    
	    g2 = (Graphics2D) g3.get();

	    // ---------- CLEAN ----------
	    g2.setTransform(original);
	}



	
	private void recalcViewport() {
	    int windowW = Core.getViewportWidth();
	    int windowH = Core.getViewportHeight();

	    int virtualW = Core.resolutionX();
	    int virtualH = Core.resolutionY();

	    double scaleX = windowW / (double) virtualW;
	    double scaleY = windowH / (double) virtualH;

	    worldScale = Math.min(scaleX, scaleY);

	    int scaledW = (int) (virtualW * worldScale);
	    int scaledH = (int) (virtualH * worldScale);

	    worldOffsetX = (windowW - scaledW) / 2;
	    worldOffsetY = (windowH - scaledH) / 2;
	}


}
