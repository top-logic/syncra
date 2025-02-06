package com.top_logic.synchra.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.basic.config.PolymorphicConfiguration;
import com.top_logic.layout.DisplayContext;
import com.top_logic.mig.html.layout.LayoutComponent;
import com.top_logic.synchra.importer.TypeImporter;
import com.top_logic.tool.boundsec.AbstractCommandHandler;
import com.top_logic.tool.boundsec.HandlerResult;

/**
 * opens the dialog for the import of catalogs
 */
public class OpenImportCatalogsDialog extends AbstractCommandHandler {

	List<TypeImporter> _importers;
	public interface Config extends AbstractCommandHandler.Config {
		List<PolymorphicConfiguration<TypeImporter>> getImporters();
	}

	/**
	 * new Instance
	 */
	public OpenImportCatalogsDialog(InstantiationContext context, Config config) {
		super(context, config);
		_importers = new ArrayList<>();
		for (PolymorphicConfiguration<TypeImporter> typeImporter : config.getImporters()) {
			_importers.add(context.getInstance(typeImporter));
		}

	}

	@Override
	public HandlerResult handleCommand(DisplayContext displayContext, LayoutComponent component, Object model,
			Map<String, Object> someArguments) {
		return new ImportCatalogsDialog(component, _importers).open(displayContext);
	}

}
