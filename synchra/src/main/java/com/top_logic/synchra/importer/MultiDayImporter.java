package com.top_logic.synchra.importer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.top_logic.basic.Logger;
import com.top_logic.basic.io.BinaryContent;
import com.top_logic.basic.io.binary.BinaryData;
import com.top_logic.knowledge.service.SimpleDBExecutor;

public class MultiDayImporter extends ZipImporter implements FileImporter {

	private List<TypeImporter> _importers;

	public MultiDayImporter(BinaryContent data, List<TypeImporter> importers) {
		super(data);
		_importers = importers;
	}

	@Override
	public void performImportWithCommit() {
		// read zip entries
		Map<Integer, BinaryData> importPerDay = getExcelDataByDay();

		if (importPerDay.isEmpty()) {
			// only pictures
			importPictures();
		} else {
			List<Integer> days = new ArrayList<>(importPerDay.keySet());
			Collections.sort(days);
			int oldDay = -1;
			for (Integer day : days) {
				Logger.info("Import day " + day, MultiDayImporter.class);
				boolean importPictures = false;
				if (oldDay > -1) {
					shiftTime(day - oldDay);
				} else {
					importPictures = true;
				}
				oldDay = day;
				BinaryData data = importPerDay.get(day);
				performImport(data);

				if (importPictures) {
					Logger.info("Import pictures ", MultiDayImporter.class);
					importPictures();
				}
			}
		}
	}

	private Map<Integer, BinaryData> getExcelDataByDay() {
		Map<String, BinaryData> dataByName = getDataByName();
		Map<Integer, BinaryData> excelDataByDay = new HashMap<>();

		for (Entry<String, BinaryData> entry : dataByName.entrySet()) {
			String name = entry.getKey();
			// name should be like <text>-<num>.xls
			if (name.endsWith(".xls")) {
				int idxPoint = name.lastIndexOf(".");
				int idxHypen = name.lastIndexOf("-");
				if (idxHypen > -1) {
					String num = name.substring(idxHypen + 1, idxPoint);
					excelDataByDay.put(Integer.parseInt(num), entry.getValue());
				}
			}
		}
		return excelDataByDay;
	}

	private void importPictures() {
		PictureImporter pictureImporter = new PictureImporter(getZipReader());
		pictureImporter.performImportWithCommit();

	}

	/**
	 * @param data
	 *        imports the Excel data
	 */
	private void performImport(BinaryData data) {
		SynchraImporter importer = new SynchraImporter(data, _importers);
		importer.performImportWithCommit();

	}

	private void shiftTime(int numberOfDays) {
		// beware to have a long in the multiplication
		long millis = 1000 * 3600 * 24l * numberOfDays;

		String h2Sql = "update revision set \"date\"=\"date\"-" + millis;
		SimpleDBExecutor db = new SimpleDBExecutor();
		db.beginTransaction();
		try {
			db.executeUpdate(h2Sql);
			db.commitTransaction();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			db.closeTransaction();
		}

	}


}
