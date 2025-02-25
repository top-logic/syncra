package com.top_logic.synchra.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.top_logic.basic.config.DefaultInstantiationContext;
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
public class OpenImportSynchraDialog extends AbstractCommandHandler {

	public interface Config extends AbstractCommandHandler.Config {
		List<PolymorphicConfiguration<TypeImporter>> getImporters();
	}

	/**
	 * new Instance
	 */
	public OpenImportSynchraDialog(InstantiationContext context, Config config) {
		super(context, config);
	}

	@Override
	public HandlerResult handleCommand(DisplayContext displayContext, LayoutComponent component, Object model,
			Map<String, Object> someArguments) {
		return new ImportSynchraDialog(component, getImporters()).open(displayContext);
	}

	private List<TypeImporter> getImporters() {
		DefaultInstantiationContext ctx = new DefaultInstantiationContext(OpenImportSynchraDialog.class);
		List<TypeImporter> importers = new ArrayList<>();
		for (PolymorphicConfiguration<TypeImporter> typeImporter : getMyConfig().getImporters()) {
			importers.add(ctx.getInstance(typeImporter));
		}
		return importers;
	}

	private Config getMyConfig() {
		return (Config) super.getConfig();
	}


}
