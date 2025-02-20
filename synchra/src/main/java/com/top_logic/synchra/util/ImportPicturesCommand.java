package com.top_logic.synchra.util;

import com.top_logic.basic.io.BinaryContent;
import com.top_logic.knowledge.service.PersistencyLayer;
import com.top_logic.knowledge.service.Transaction;
import com.top_logic.synchra.importer.PictureImporter;

public class ImportPicturesCommand extends AbstractImport {

	public ImportPicturesCommand(ImportPicturesDialog importPicturesDialog) {
		super(importPicturesDialog);
	}

	@Override
	protected void performImport() {
		BinaryContent data = getImportDialog().getUploadField().getDataItem();
		PictureImporter importer = new PictureImporter(data);

		try (Transaction ta = PersistencyLayer.getKnowledgeBase().beginTransaction()) {
			importer.performImport();
			ta.commit();
		}

	}
}
