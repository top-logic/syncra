package com.top_logic.synchra.util;

import java.util.Map;

import com.top_logic.basic.config.InstantiationContext;
import com.top_logic.layout.DisplayContext;
import com.top_logic.mig.html.layout.LayoutComponent;
import com.top_logic.synchra.model.interfaces.SinglePart;
import com.top_logic.tool.boundsec.AbstractCommandHandler;
import com.top_logic.tool.boundsec.HandlerResult;

/**
 * Imports a zip file with pictures for {@link SinglePart}
 */
public class OpenImportPicturesDialog extends AbstractCommandHandler {

	/**
	 * new Instance
	 */
	public OpenImportPicturesDialog(InstantiationContext context, Config config) {
		super(context, config);
	}

	@Override
	public HandlerResult handleCommand(DisplayContext displayContext, LayoutComponent component, Object model,
			Map<String, Object> someArguments) {
		return new ImportPicturesDialog(component).open(displayContext);
	}
}