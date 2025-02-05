package com.top_logic.synchra.importer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.top_logic.basic.io.BinaryContent;
import com.top_logic.contact.business.POIExcelImporter;

/**
 * Imports data for synchra
 */
public class SynchraImporter extends POIExcelImporter {

	private List<TypeImporter> _importers;
	private Map<String, TypeImporter> _typeImporters;

	private ImportSession _session;

	public SynchraImporter(BinaryContent importSource, List<TypeImporter> importers) {
		super(importSource);
		setupNew(importers);
	}

	private void setupNew(List<TypeImporter> importers) {
		_importers = new ArrayList<>();
		_typeImporters = new HashMap<>();
		_session = new ImportSession();
		for (TypeImporter importer : importers) {
			registerImporter(importer);
			importer.prepare(_session);
		}
	}

	private void registerImporter(TypeImporter importer) {
		// the sequence of the imports is important
		_importers.add(importer);
		_typeImporters.put(importer.getSheetName(), importer);
	}


	/**
	 * Performs the check for all registered importers in the registering sequence
	 */
	public void checkData() {
		for(TypeImporter importer : _importers) {
			importer.checkData();
		}
	}

	/**
	 * Performs the import for all registered importers in the registering sequence
	 */
	public void performImport() {
		for(TypeImporter importer : _importers) {
			importer.performImport();
		}
	}

	/**
	 * Check that the first row (as found in CSVTokenizer) is as expected.
	 * 
	 * @throws IOException
	 *         when format is not OK.
	 * 
	 * @return false to indicate that no check is needed, (assuming first line contains data)
	 */
	@Override
	protected boolean checkColumnFormat(int aSheetNum, String sheetname, Row row) throws IOException {
		TypeImporter ti = _typeImporters.get(sheetname);
		ti.initHeaders(row);
		return true;
	}

	@Override
	protected void importRow(int aSheetNum, String sheetname, Row row) throws Exception {
		TypeImporter ti = _typeImporters.get(sheetname);
		ti.addRow(row);
	}

	/**
	 * Check if the current sheet defined by numSheet and sheetName should be imported or ignored.
	 * By default, only the first sheet should be imported.
	 */
	@Override
	protected boolean shoudImportSheet(int numSheet, String sheetName) {
		return _typeImporters.keySet().contains(sheetName);
	}

}
