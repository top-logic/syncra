package com.top_logic.synchra.util;

import java.util.List;

import com.top_logic.layout.DisplayDimension;
import com.top_logic.layout.DisplayUnit;
import com.top_logic.layout.ResPrefix;
import com.top_logic.layout.basic.CommandModel;
import com.top_logic.layout.basic.CommandModelFactory;
import com.top_logic.layout.basic.CommandModelUtilities;
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

public abstract class AbstractImportDialog extends SimpleFormDialog {

	private LayoutComponent _contextComponent;

	/**
	 * new instance
	 */
	public AbstractImportDialog(ResPrefix resourcePrefix, LayoutComponent contextComponent) {
		super(resourcePrefix,
			DisplayDimension.dim(380, DisplayUnit.PIXEL),
			DisplayDimension.dim(210, DisplayUnit.PIXEL));
		_contextComponent = contextComponent;
	}

	/**
	 * Invalidates the context component of the import
	 */
	protected void invalidateContext() {
		_contextComponent.invalidate();
	}

	@Override
	protected void fillFormContext(FormContext context) {
		DataField uploadField = FormFactory.newDataField(INPUT_FIELD);
		uploadField.addConstraint(getFileNameConstraint());
		context.addMember(uploadField);
	}

	protected abstract FilenameEndConstraint getFileNameConstraint();

	@Override
	protected void fillButtons(List<CommandModel> buttons) {
		buttons.add(MessageBox.button(ButtonType.CANCEL, getDialogModel().getCloseAction()));
		CommandModel updateCommand = CommandModelFactory.commandModel(getImportCommand());
		updateCommand.setLabel(Resources.getInstance().getString(getResourcePrefix().key("import")));
		CommandModelUtilities.setNonExecutable(updateCommand, getResourcePrefix().key("nothingSelected"));

		getUploadField().addValueListener(new RolesProfileUploadFieldListener(updateCommand));
		buttons.add(updateCommand);
	}

	protected abstract AbstractImport getImportCommand();


	/**
	 * The field into which the roles-profile file is uploaded.
	 */
	protected DataField getUploadField() {
		return (DataField) getFormContext().getField(INPUT_FIELD);
	}

}
