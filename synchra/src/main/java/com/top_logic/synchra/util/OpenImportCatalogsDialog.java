package com.top_logic.synchra.util;

import java.util.Map;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.layout.DisplayContext;
import com.top_logic.mig.html.layout.LayoutComponent;
import com.top_logic.tool.boundsec.AbstractCommandHandler;
import com.top_logic.tool.boundsec.HandlerResult;

/**
 * opens the dialog for the import of catalogs
 */
public class OpenImportCatalogsDialog extends AbstractCommandHandler {

	/**
	 * new Instance
	 */
	public OpenImportCatalogsDialog(InstantiationContext context, Config config) {
		super(context, config);
	}

	@Override
	public HandlerResult handleCommand(DisplayContext displayContext, LayoutComponent component, Object model,
			Map<String, Object> someArguments) {
		return new ImportCatalogsDialog(component).open(displayContext);
	}

}
