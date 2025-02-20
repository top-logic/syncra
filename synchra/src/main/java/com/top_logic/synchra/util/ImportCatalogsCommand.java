package com.top_logic.synchra.util;

import java.util.List;

import com.top_logic.basic.io.BinaryContent;
import com.top_logic.knowledge.service.PersistencyLayer;
import com.top_logic.knowledge.service.Transaction;
import com.top_logic.synchra.importer.SynchraImporter;
import com.top_logic.synchra.importer.TypeImporter;

/**
 * Imports catalogs from an xls file
 */
public class ImportCatalogsCommand extends AbstractImport {

	public ImportCatalogsCommand(ImportCatalogsDialog importCatalogsDialog) {
		super(importCatalogsDialog);
	}


	@Override
	protected void performImport() {
		ImportCatalogsDialog importDialog = (ImportCatalogsDialog) getImportDialog();

		BinaryContent data = importDialog.getUploadField().getDataItem();
		List<TypeImporter> importers = importDialog.getImporters();

		SynchraImporter importer = new SynchraImporter(data, importers);
		// read data
		// data should be an xls file
		importer.run();

		importer.checkData();

		try (Transaction ta = PersistencyLayer.getKnowledgeBase().beginTransaction()) {
			importer.performImport();
			ta.commit();
		}
	}

}
