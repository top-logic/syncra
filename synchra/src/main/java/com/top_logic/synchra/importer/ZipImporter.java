package com.top_logic.synchra.importer;

import java.util.Map;

import com.top_logic.basic.io.BinaryContent;
import com.top_logic.basic.io.binary.BinaryData;

public abstract class ZipImporter {

	private ZipReader _zipReader;

	public ZipImporter(BinaryContent data) {
		this(new ZipReader(data));
	}

	public ZipImporter(ZipReader reader) {
		_zipReader = reader;
	}

	protected Map<String, BinaryData> getDataByName() {
		return getZipReader().getDataByName();
	}

	protected ZipReader getZipReader() {
		return _zipReader;
	}

}
