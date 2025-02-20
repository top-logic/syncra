package com.top_logic.synchra.util;

import java.util.List;

import com.top_logic.layout.form.constraints.FilenameEndConstraint;
import com.top_logic.mig.html.layout.LayoutComponent;
import com.top_logic.synchra.importer.TypeImporter;

/**
 * Dialog to choose an xls file for import of catalogs
 */
public class ImportCatalogsDialog extends AbstractImportDialog {

	private List<TypeImporter> _importers;

	/**
	 * Creates a {@link I18NConstants}.
	 * 
	 */
	public ImportCatalogsDialog(LayoutComponent catalogGrid, List<TypeImporter> importers) {
		super(I18NConstants.IMPORT_CATALOGS, catalogGrid);
		_importers = importers;
	}


	@Override
	protected ImportCatalogsCommand getImportCommand() {
		return new ImportCatalogsCommand(this);
	}

	protected List<TypeImporter> getImporters() {
		return _importers;
	}

	@Override
	protected FilenameEndConstraint getFileNameConstraint() {
		return new FilenameEndConstraint(".xls", I18NConstants.ONLY_XLS_FILES);
	}
}
