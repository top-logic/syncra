package com.top_logic.synchra.importer.attribute;

import java.util.List;

import com.top_logic.model.DerivedTLTypePart;
import com.top_logic.model.TLObject;
import com.top_logic.synchra.importer.ImportSession;
import com.top_logic.synchra.importer.SynchraImporter;
import com.top_logic.synchra.importer.transformer.ValueTransformer;

public abstract class AttributeImport {

	private int _col;

	private DerivedTLTypePart _part;

	/**
	 * translates the excel value in a java value or object
	 */
	private ValueTransformer _valueProvider;

	public AttributeImport(DerivedTLTypePart part, ValueTransformer valueProvider) {
		_part = part;
		_valueProvider = valueProvider;
		_col = -10;
	}

	public abstract void performImport(TLObject object, List<Object> values);

	protected Object getImportValue(Object excelValue) {
		return _valueProvider.get(excelValue);
	}

	public int getColumn() {
		return _col;
	}

	public void setColumn(int col) {
		_col = col;
	}

	public boolean hasColumn() {
		return _col > -10;
	}

	/**
	 * @return the german name of the part
	 */
	public String getHeaderName() {
		return SynchraImporter.label(_part);
	}

	public String getName() {
		return getPart().getName();
	}

	public DerivedTLTypePart getPart() {
		return _part;
	}

	public void prepare(ImportSession session) {
		_valueProvider.prepare(session);

	}

}