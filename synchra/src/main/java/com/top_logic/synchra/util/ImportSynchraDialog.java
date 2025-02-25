package com.top_logic.synchra.util;

import java.util.List;

import com.top_logic.basic.io.binary.BinaryData;
import com.top_logic.layout.form.CheckException;
import com.top_logic.layout.form.Constraint;
import com.top_logic.layout.form.model.DataField;
import com.top_logic.mig.html.layout.LayoutComponent;
import com.top_logic.synchra.importer.TypeImporter;
import com.top_logic.util.Resources;

/**
 * Dialog to choose an xls file for import of catalogs
 */
public class ImportSynchraDialog extends AbstractImportDialog {

	private List<TypeImporter> _importers;

	/**
	 * Creates a {@link I18NConstants}.
	 * 
	 */
	public ImportSynchraDialog(LayoutComponent catalogGrid, List<TypeImporter> importers) {
		super(I18NConstants.IMPORT_CATALOGS, catalogGrid);
		_importers = importers;
	}


	@Override
	protected ImportSynchraCommand getImportCommand() {
		return new ImportSynchraCommand(this);
	}

	protected List<TypeImporter> getImporters() {
		return _importers;
	}

	@Override
	protected Constraint getFileNameConstraint() {
		return new Constraint() {
			@Override
			public boolean check(Object value) throws CheckException {
				List<BinaryData> items = DataField.toItems(value);
				for (BinaryData item : items) {
					String lowerCase = item.getName().toLowerCase();
					if (!(lowerCase.endsWith(".xls") || lowerCase.endsWith(".zip"))) {
						String message =
							Resources.getInstance().getMessage(I18NConstants.ONLY_XLS_OR_ZIP_FILES, item.getName());
						throw new CheckException(message);
					}
				}
				return true;
			}
		};
	}
}
