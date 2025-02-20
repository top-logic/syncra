package com.top_logic.synchra.util;

import com.top_logic.layout.DisplayContext;
import com.top_logic.layout.basic.Command;
import com.top_logic.tool.boundsec.HandlerResult;

public abstract class AbstractImport implements Command {

	private AbstractImportDialog _importDialog;

	public AbstractImport(AbstractImportDialog importCatalogsDialog) {
		_importDialog = importCatalogsDialog;
	}

	protected AbstractImportDialog getImportDialog() {
		return _importDialog;
	}

	@Override
	public HandlerResult executeCommand(DisplayContext context) {
		performImport();
		getImportDialog().invalidateContext();
		getImportDialog().getDialogModel().getCloseAction().executeCommand(context);
		return HandlerResult.DEFAULT_RESULT;
	}

	protected abstract void performImport();

}
