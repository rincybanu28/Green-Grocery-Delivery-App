package com.example.gorcerydelivery.entity;

import java.util.HashMap;
import java.util.Map;

public enum Category {
	
	FRUITS(0),
	VEGITABLES(1);
	   private int value;
	    private static Map map = new HashMap<>();

	    private Category(int value) {
	        this.value = value;
	    }
	    static {
	        for (Category category : Category.values()) {
	            map.put(category.value, category);
	        }
	    }

	    public static Category valueOf(int category) {
	        return (Category) map.get(category);
	    }

	    public int getValue() {
	        return value;
	    }
}
