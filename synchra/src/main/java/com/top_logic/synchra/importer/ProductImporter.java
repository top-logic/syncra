package com.top_logic.synchra.importer;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.model.ModelFactory;
import com.top_logic.synchra.model.interfaces.ConstructionGroup;
import com.top_logic.synchra.model.interfaces.Product;

public class ProductImporter extends TypeImporter {

	public ProductImporter(InstantiationContext context, Config config) {
		super(context, config);
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
		// store it in the session
		register(constructionGroup);

		// con -> x -> y -> $x.set(`syn:Product#buildGroup`,
		// new(`syn:ConstructionGroup`, transient: false)
		// ..set(`syn:ConstructionGroup#name`,"BG "+$x.get(`syn:Product#name`))
		// )
	}

}