package com.sgrh.conf;

import java.util.HashMap;
import java.util.Map;

public class CategoryMap {
	public static Map<String,Integer> CATEGORY_MAP;
	static {
		CATEGORY_MAP = new HashMap<>();
		CATEGORY_MAP.put("board of management",1);
		CATEGORY_MAP.put("consultant",2);
		CATEGORY_MAP.put("doctor",3);
		CATEGORY_MAP.put("outsourced",4);
		CATEGORY_MAP.put("pg student",5);
		CATEGORY_MAP.put("staff",6);
		CATEGORY_MAP.put("trustee",7);
	}
}
