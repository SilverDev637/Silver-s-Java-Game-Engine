package io.silverdev637.sjge.engine;

import java.awt.Graphics;
import java.util.Map;
import java.util.TreeMap;

public class Layer {
	private Map<Integer, Sprite> elements = new TreeMap<>();
	
	private String name;
	
	public Layer(String name) {
		this.name = name;
	}
	
	public final void draw(Graphics g) {
		for (Map.Entry<Integer, Sprite> entry : elements.entrySet()) {
            Sprite element = entry.getValue();
            element.draw(g);
        }
	}
	
	public final void update(double delta) {
		for (Map.Entry<Integer, Sprite> entry : elements.entrySet()) {
            Sprite element = entry.getValue();
            element.update(delta);
        }
	}
	
	protected final void add(Sprite element, int zOrder) {
		elements.put(zOrder, element);
	}
	
	@Override
	public final String toString() {
		if (name != null) return name;
		return super.toString();
	}
}
