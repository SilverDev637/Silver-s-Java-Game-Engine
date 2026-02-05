package io.silverdev637.sjge.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private static ArrayList<InputMap> inputMaps = new ArrayList<>();
	
	// --- Teclado ---
    private final Set<Integer> keysDown = new HashSet<>();
    private final Set<Integer> keysPressed = new HashSet<>();
    private final Set<Integer> keysReleased = new HashSet<>();

    // --- Mouse ---
    private final Set<Integer> mouseDown = new HashSet<>();
    private final Set<Integer> mousePressed = new HashSet<>();
    private final Set<Integer> mouseReleased = new HashSet<>();

    private int mouseX, mouseY;
    private double scrollVelocity;

    // ------------------------------------------------
    //                  UPDATE
    // ------------------------------------------------
    /**
     * Call ONCE per frame
     */
    public void update() {
        keysPressed.clear();
        keysReleased.clear();
        mousePressed.clear();
        mouseReleased.clear();
        scrollVelocity = 0;
    }

    // ------------------------------------------------
    //              QUERIES - KEYBOARD
    // ------------------------------------------------
    public boolean isKeyDown(int keyCode) {
        return keysDown.contains(keyCode);
    }

    public boolean isKeyPressed(int keyCode) {
        return keysPressed.contains(keyCode);
    }

    public boolean isKeyReleased(int keyCode) {
        return keysReleased.contains(keyCode);
    }

    // ------------------------------------------------
    //                QUERIES - MOUSE
    // ------------------------------------------------
    public boolean isMouseDown(int button) {
        return mouseDown.contains(button);
    }

    public boolean isMousePressed(int button) {
        return mousePressed.contains(button);
    }

    public boolean isMouseReleased(int button) {
        return mouseReleased.contains(button);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
    
    public int getGlobalMouseX() {
        return mouseX;
    }

    public int getGlobalMouseY() {
        return mouseY;
    }

    public double getScrollVelocity() {
        return scrollVelocity;
    }

    // ------------------------------------------------
    //               QUERIES - INPUT MAPS
    // ------------------------------------------------
    public boolean isInputDown(String inputMapName) {
    	return getInputKeysDown(inputMapName) > 0;
    }
    
    public boolean isInputPressed(String inputMapName) {
    	return getInputKeysPressed(inputMapName) > 0;
    }
    
    public boolean isInputReleased(String inputMapName) {
    	return getInputKeysReleased(inputMapName) > 0;
    }
    
    public int getInputKeysDown(String inputMapName) {
    	int result = 0;
    	if (!inputMapExists(inputMapName)) {
    		throw (new IllegalArgumentException("No input map named \"" + inputMapName + "\" found!"));
    	}
    	int[] keys = getInputMap(inputMapName).getKeys();
    	for (int keyEvent : keys) {
    		if (isKeyDown(keyEvent)) result++;
    	}
    	return result;
    }
    
    public int getInputKeysPressed(String inputMapName) {
    	int result = 0;
    	if (!inputMapExists(inputMapName)) {
    		throw (new IllegalArgumentException("No input map named \"" + inputMapName + "\" found!"));
    	}
    	int[] keys = getInputMap(inputMapName).getKeys();
    	for (int keyEvent : keys) {
    		if (isKeyPressed(keyEvent)) result++;
    	}
    	return result;
    }
    
    public int getInputKeysReleased(String inputMapName) {
    	int result = 0;
    	if (!inputMapExists(inputMapName)) {
    		throw (new IllegalArgumentException("No input map named \"" + inputMapName + "\" found!"));
    	}
    	int[] keys = getInputMap(inputMapName).getKeys();
    	for (int keyEvent : keys) {
    		if (isKeyReleased(keyEvent)) result++;
    	}
    	return result;
    }
    
    
    // ------------------------------------------------
    //              LISTENER IMPLEMENTATION
    // ------------------------------------------------
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!keysDown.contains(key)) {
            keysPressed.add(key);
        }
        keysDown.add(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keysDown.remove(key);
        keysReleased.add(key);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if (!mouseDown.contains(button)) {
            mousePressed.add(button);
        }
        mouseDown.add(button);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        mouseDown.remove(button);
        mouseReleased.add(button);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrollVelocity += e.getPreciseWheelRotation();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
	
    public boolean inputMapExists(String name) {
    	for (InputMap inputMap : inputMaps) {
			if (inputMap.toString().equals(name)) return true;
		}
    	return false;
    }
    
	public InputMap getInputMap(String name) {
		for (InputMap inputMap : inputMaps) {
			if (inputMap.toString().equals(name)) return inputMap;
		}
		throw new IllegalArgumentException("No input map named \"" + name + "\" found!");
	}
	
	public void addInputMap(InputMap newInputMap) {
		if (inputMapExists(newInputMap.toString()))
			throw new IllegalArgumentException("The input map named \"" + newInputMap.toString() + "\" already exists!");
		inputMaps.add(newInputMap);
	}
	
	public void clearInputMaps() {
		inputMaps.clear();
	}
}
