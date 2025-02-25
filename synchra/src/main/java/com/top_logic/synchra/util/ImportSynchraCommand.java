package com.top_logic.synchra.util;

import java.util.List;

import com.top_logic.basic.io.BinaryContent;
import com.top_logic.synchra.importer.FileImporter;
import com.top_logic.synchra.importer.MultiDayImporter;
import com.top_logic.synchra.importer.SynchraImporter;
import com.top_logic.synchra.importer.TypeImporter;

/**
 * Imports catalogs from an xls file
 */
public class ImportSynchraCommand extends AbstractImport {

	public ImportSynchraCommand(ImportSynchraDialog importCatalogsDialog) {
		super(importCatalogsDialog);
	}


	@Override
	protected void performImport() {
		ImportSynchraDialog importDialog = (ImportSynchraDialog) getImportDialog();

		BinaryContent data = importDialog.getUploadField().getDataItem();
		List<TypeImporter> importers = importDialog.getImporters();

		FileImporter importer;
		String name = data.getName();
		if (name.endsWith(".xls")) {
			importer = new SynchraImporter(data, importers);
		} else if (name.endsWith(".zip")) {
			importer = new MultiDayImporter(data, importers);
		} else {
			throw new RuntimeException("Can not import file: " + name);
		}
		importer.performImportWithCommit();
	}

}
