package com.top_logic.synchra.util;

import com.top_logic.basic.io.BinaryContent;
import com.top_logic.knowledge.service.PersistencyLayer;
import com.top_logic.knowledge.service.Transaction;
import com.top_logic.layout.DisplayContext;
import com.top_logic.layout.basic.Command;
import com.top_logic.tool.boundsec.HandlerResult;

/**
 * Imports calalogs from an xlsx file
 */
public class CatalogsImportCommand implements Command {

	private ImportCatalogsDialog _importCatalogsDialog;

	public CatalogsImportCommand(ImportCatalogsDialog importCatalogsDialog) {
		_importCatalogsDialog = importCatalogsDialog;
	}

	@Override
	public HandlerResult executeCommand(DisplayContext context) {
		BinaryContent data = _importCatalogsDialog.getUploadField().getDataItem();

		CatalogImporter importer = new CatalogImporter(data);
		// read data
		importer.run();

		importer.checkData();

		try (Transaction ta = PersistencyLayer.getKnowledgeBase().beginTransaction()) {
			importer.performImport();
			ta.commit();
		}

		// data should be an xlsx file
		_importCatalogsDialog.getCatalogGrid().invalidate();
		_importCatalogsDialog.getDialogModel().getCloseAction().executeCommand(context);
		return HandlerResult.DEFAULT_RESULT;
	}

}
