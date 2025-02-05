package com.top_logic.synchra.importer.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.top_logic.basic.StringServices;
import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.importer.ImportSession;
import com.top_logic.synchra.importer.ImportUtil;
import com.top_logic.synchra.model.ModelFactory;

/**
 * transforms an excel value in the TLObject with the given id. The id is the value of the TLObject
 * for idAttribute
 */
public class TLObjectTransformer implements ValueTransformer {

	public interface Config extends PolymorphicConfiguration {

		/**
		 * @return the name of the {@link TLClass} to which the transformer transforms
		 */
		String getTlClass();

		/**
		 * @return the name of the {@link TLClass} to which the transformer creates when no object
		 *         is existing if no create class is given no objects are created
		 */
		String getCreateTlClass();

		/**
		 * @return the name of the attribute which provides the primary key
		 */
		String getIdAttribute();

	}

	private TLClass _tlClass;

//	private TLProperty _idAttribute;
	private String _idAttribute;

	private ImportSession _session;

	private TLClass _tlCreateClass;

	public TLObjectTransformer(InstantiationContext context, Config config) {
		_tlClass = ImportUtil.getTlClass(config.getTlClass());
		_idAttribute = config.getIdAttribute();
//		_idAttribute = ImportUtil.getProperty(config.getTlClass() + "#" + config.getIdAttribute());
		String createTlClass = config.getCreateTlClass();
		if (!StringServices.isEmpty(createTlClass)) {
			_tlCreateClass = ImportUtil.getTlClass(createTlClass);
		}
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
			Object tValue = obj.tValueByName(_idAttribute);
			if (id.equals(tValue)) {
				return obj;
			}
		}
		if (_tlCreateClass != null) {
			TLObject tlObject = ModelFactory.getInstance().createObject(_tlCreateClass);
			tlObject.tUpdateByName(_idAttribute, id);
//			tlObject.tUpdate(_idAttribute, id);
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
