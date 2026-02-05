package io.silverdev637.sjge.input;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to manage named input maps, each containing an array of key codes.
 */
public class InputMap implements Serializable {


	private static final long serialVersionUID = 4869078848071020466L;
	
	private String name;
	private ArrayList<Integer> keys = new ArrayList<>();

    
    /**
     * Creates a new input map with the given name and keys.
     * If a map with the same name exists, it will be overwritten.
     */
    public InputMap(String name, int... keys) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Input map name cannot be null or blank");
        }
        this.name = name;
        for (int key : keys) {
            this.keys.add(key);
        }
    }
    
    @Override
    public String toString() {
    	return this.name;
    }

    /**
     * Edits an existing input map by replacing the key at the given index.
     */
    public void setKeyAt(int keyIndex, int newKey) {
        if (keyIndex < 0 || keyIndex >= keys.size()) {
            throw new IndexOutOfBoundsException("Key index out of bounds: " + keyIndex);
        }
        this.keys.set(keyIndex, newKey);
    }

    /**
     * Returns a copy of the keys for the input map with the given name.
     * Returns an empty array if no such map exists.
     */
    public int[] getKeys() {
        if (keys == null || keys.isEmpty()) {
            return new int[0];
        }

        int[] result = new int[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            result[i] = keys.get(i);
        }
        return result;
    }



    /**
     * Removes a key at the given index.
     */
    public void removeAt(int index) {
        keys.remove(index);
    }
    
    /**
     * Removes the key from this input map.
     */
    public void removeKey(int key) {
    	keys.remove(keys.indexOf(key));
    }
    
    /**
     * Gets the index of the key
     */
    public int getIndexOf(int key) {
    	return keys.indexOf(key);
    }

    /**
     * Checks if a key exists on this input map.
     */
    public boolean contains(int key) {
        return keys.contains(key);
    }

    /**
     * Adds a key to the input map.
     */
    public void addKey(int key) {
    	if (keys.contains(key)) {
    		throw new IllegalArgumentException("Key already exists on this input map.");
    	}
    	keys.add(key);
    }
}
