package com.top_logic.synchra.importer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.top_logic.basic.col.MapUtil;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.util.AttributedUtil;

/**
 * Holds information about existing and during the import created objects
 */
public class ImportSession {

	private Map<TLClass, Set<TLObject>> _newObjects;

	public ImportSession() {
		_newObjects = new HashMap<>();
	}

	/**
	 * @param obj
	 *        the object to register
	 */
	public void register(TLObject obj) {
		TLClass tlClass = (TLClass) obj.tType();
		MapUtil.addObjectToSet(_newObjects, tlClass, obj);
	}

	/**
	 * @return all instances, also the transient ones
	 */
	public Set<TLObject> getAllInstancesOfType(TLClass tlClass) {
		Set<TLObject> all = new HashSet<>(AttributedUtil.getAllInstancesOfType(tlClass, TLObject.class));
		Set<TLObject> newObjs = _newObjects.get(tlClass);
		if (newObjs != null) {
			all.addAll(newObjs);
		}
		return all;
	}

}
