package com.top_logic.synchra.importer;

import java.util.Collections;
import java.util.Set;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.importer.attribute.AttributeImport;
import com.top_logic.synchra.model.ModelFactory;
import com.top_logic.synchra.model.interfaces.ConstructionGroup;
import com.top_logic.synchra.model.interfaces.Product;

public class ProductImporter extends TypeImporter {

	public ProductImporter(InstantiationContext context, Config config) {
		super(context, config);
	}
	public ProductImporter(TLClass tlClass, Set<AttributeImport> attributes, String idAttribute) {
		super(tlClass, attributes, Collections.singletonList(idAttribute));
	}

	/**
	 * @param newObject
	 *        the new created object
	 */
	@Override
	public void afterCreation(TLObject newObject) {
		Product product = (Product) newObject;

		// create constructionGroup for the new product
		ConstructionGroup constructionGroup = ModelFactory.getInstance().createConstructionGroup();
		constructionGroup.setName("BG " + product.getName());
		product.setBuildGroup(constructionGroup);
		// con -> x -> y -> $x.set(`fma:Product#buildGroup`,
		// new(`fma:ConstructionGroup`, transient: false)
		// ..set(`fma:ConstructionGroup#name`,"BG "+$x.get(`fma:Product#name`))
		// )
	}

}