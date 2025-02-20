package com.top_logic.synchra.util;

import com.top_logic.layout.form.constraints.FilenameEndConstraint;
import com.top_logic.mig.html.layout.LayoutComponent;

public class ImportPicturesDialog extends AbstractImportDialog {

	/**
	 * Creates a {@link I18NConstants}.
	 * 
	 */
	public ImportPicturesDialog(LayoutComponent context) {
		super(I18NConstants.IMPORT_PICTURES, context);
	}

	@Override
	protected AbstractImport getImportCommand() {
		return new ImportPicturesCommand(this);
	}

	@Override
	protected FilenameEndConstraint getFileNameConstraint() {
		return new FilenameEndConstraint(".zip", I18NConstants.ONLY_ZIP_FILES);
	}
}
