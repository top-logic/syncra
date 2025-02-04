package com.top_logic.synchra.importer.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.model.TLProperty;
import com.top_logic.synchra.importer.ImportSession;
import com.top_logic.synchra.model.ModelFactory;

/**
 * transforms an excel value in the TLObject with the given id. The id is the value of the TLObject
 * for idAttribute
 */
public class TLObjectTransformer implements ValueTransformer {

	private TLClass _tlClass;

	private TLProperty _idAttribute;

	private ImportSession _session;

	private boolean _createIfAbsent;

	public TLObjectTransformer(TLClass tlClass, TLProperty idAttribute, boolean create) {
		_tlClass = tlClass;
		_idAttribute = idAttribute;
		_createIfAbsent = create;
	}

	@Override
	public Object get(Object excelValue) {
		String rawValue = (String) excelValue;
		String[] split = rawValue.split(",");
		if (split.length > 1) {
			List<Object> res = new ArrayList<>();
			for (String val : split) {
				res.add(doGet(val.trim()));
			}
			return res;
		} else {
			return doGet(split[0].trim());
		}
	}

	private Object doGet(String id) {
		Set<TLObject> allInstancesOfType = _session.getAllInstancesOfType(_tlClass);
		for (TLObject obj : allInstancesOfType) {
			Object tValue = obj.tValue(_idAttribute);
			if (id.equals(tValue)) {
				return obj;
			}
		}
		if (_createIfAbsent) {
			TLObject tlObject = ModelFactory.getInstance().createObject(_tlClass);
			tlObject.tUpdate(_idAttribute, id);
			_session.register(tlObject);
			return tlObject;
		}
		return null;
	}

	@Override
	public void prepare(ImportSession session) {
		_session = session;
	}

}
