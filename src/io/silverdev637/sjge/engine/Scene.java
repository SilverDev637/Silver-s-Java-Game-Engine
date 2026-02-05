package io.silverdev637.sjge.engine;

import java.awt.Graphics2D;
import java.util.Map;
import java.util.TreeMap;

public abstract class Scene {
	public String name = null;
	public Map<Integer, Layer> layers = new TreeMap<>();
	
	public abstract void init();
	
	public abstract void update(double delta);
	
	public abstract void draw(Graphics2D g);

	public abstract void drawUI(GraphicsUI g);
	
	@Override
	public final String toString() {
		if (name != null)
			return name;
		return super.toString();
	}
	
	public final void add(Sprite element, int zOrder, String layerName) {
		for (Map.Entry<Integer, Layer> entry : layers.entrySet()) {
            Layer layer = entry.getValue();
            if (layer.toString().equals(layerName)) {
            	layer.add(element, zOrder);
            	return;
            }
        }
		throw (new IllegalArgumentException("No layer found on the name of \"" + layerName + "\""));
	}

	public final void newLayer(String name, int weight) {
		for (Map.Entry<Integer, Layer> entry : layers.entrySet()) {
            Layer layer = entry.getValue();
            if (layer.toString().equals(name))
            	throw (new IllegalArgumentException("Layer \"" + name + "\" already exists"));
        }
		layers.put(weight, new Layer(name));
	}
}
