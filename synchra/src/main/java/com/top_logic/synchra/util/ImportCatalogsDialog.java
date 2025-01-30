package com.top_logic.synchra.util;

import java.util.List;

import com.top_logic.layout.DisplayDimension;
import com.top_logic.layout.DisplayUnit;
import com.top_logic.layout.basic.CommandModel;
import com.top_logic.layout.basic.CommandModelFactory;
import com.top_logic.layout.basic.CommandModelUtilities;
import com.top_logic.layout.form.component.FormComponent;
import com.top_logic.layout.form.constraints.FilenameEndConstraint;
import com.top_logic.layout.form.model.DataField;
import com.top_logic.layout.form.model.FormContext;
import com.top_logic.layout.form.model.FormFactory;
import com.top_logic.layout.messagebox.MessageBox;
import com.top_logic.layout.messagebox.MessageBox.ButtonType;
import com.top_logic.layout.messagebox.SimpleFormDialog;
import com.top_logic.mig.html.layout.LayoutComponent;
import com.top_logic.tool.boundsec.compound.gui.admin.rolesProfile.RolesProfileUploadFieldListener;
import com.top_logic.util.Resources;

/**
 * Dialog to choose an xlsx file for import of catalogs
 */
public class ImportCatalogsDialog extends SimpleFormDialog {

	private final LayoutComponent _catalogGrid;

	/**
	 * Creates a {@link I18NConstants}.
	 */
	public ImportCatalogsDialog(LayoutComponent catalogGrid) {
		super(I18NConstants.IMPORT_CATALOGS_DIALOG,
			DisplayDimension.dim(380, DisplayUnit.PIXEL),
			DisplayDimension.dim(210, DisplayUnit.PIXEL));
		_catalogGrid = catalogGrid;
	}

	@Override
	protected void fillFormContext(FormContext context) {
		DataField uploadField = FormFactory.newDataField(INPUT_FIELD);
		uploadField.addConstraint(new FilenameEndConstraint(".xls", I18NConstants.ONLY_XLXS_FILES));
		context.addMember(uploadField);
	}

	@Override
	protected void fillButtons(List<CommandModel> buttons) {
		buttons.add(MessageBox.button(ButtonType.CANCEL, getDialogModel().getCloseAction()));
		CommandModel updateCommand = CommandModelFactory.commandModel(new CatalogsImportCommand(this));
		updateCommand.setLabel(Resources.getInstance().getString(I18NConstants.IMPORT_CATALOGS));
		CommandModelUtilities.setNonExecutable(updateCommand, I18NConstants.IMPORT_CATALOGS_NOTHING_SELECTED);
		getUploadField().addValueListener(new RolesProfileUploadFieldListener(updateCommand));
		buttons.add(updateCommand);
	}

	/**
	 * The component displaying the roles-profiles or a part of them.
	 * <p>
	 * After the import this component is invalidated and (if it's a {@link FormComponent}) its
	 * {@link FormContext} is removed.
	 * </p>
	 * 
	 * @return Is null, if there is no component that should to be invalidated.
	 */
	protected LayoutComponent getCatalogGrid() {
		return _catalogGrid;
	}

	/**
	 * The field into which the roles-profile file is uploaded.
	 */
	protected DataField getUploadField() {
		return (DataField) getFormContext().getField(INPUT_FIELD);
	}

}
