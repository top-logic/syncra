package com.top_logic.synchra.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.top_logic.basic.Settings;
import com.top_logic.basic.io.BinaryContent;
import com.top_logic.basic.io.StreamUtilities;
import com.top_logic.basic.io.binary.BinaryData;
import com.top_logic.basic.io.binary.BinaryDataFactory;

/**
 * reads a zip file and has a map with the filenames and the contents
 */
public class ZipReader {

	private Map<String, BinaryData>  _dataByName;

	/**
	 * @param data the content of the zip file to read
	 */
	public ZipReader (BinaryContent data) {
		try {
			init(data);
		}
		catch(Exception e) {
			throw asRuntimeException(e);
		}
	}

	public Map<String, BinaryData> getDataByName() {
		return _dataByName;
	}

	private void init(BinaryContent data) throws Exception {
		_dataByName = new HashMap<>();
		File zipFile = getZipFile(data);
		try (ZipFile zf = new ZipFile(zipFile)) {
			Enumeration<? extends ZipEntry> entries = zf.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				if (!zipEntry.isDirectory()) {
					String name = getSimpleName(zipEntry);
					BinaryData content = readEntry(zf, zipEntry, name);
					if (data != null) {
						_dataByName.put(name, content);
					}
				}
			}
			zf.close();
		}
	}

	private String getSimpleName(ZipEntry zipEntry) {
		String name = zipEntry.getName();
		int idx = name.lastIndexOf("/");
		if (idx > -1) {
			name = name.substring(idx + 1);
		}
		return name;
	}

	private Map<String, BinaryData> getPicturesByName() throws Exception {
		Map<String, BinaryData> dataByName = getDataByName();
		Map<String, BinaryData> picturesByName = new HashMap<>();

		for (Entry<String, BinaryData> entry : dataByName.entrySet()) {
			String name = entry.getKey();
			if (name.endsWith(".png")) {
				name = name.substring(0, name.length() - 4);
				picturesByName.put(name, entry.getValue());
			}
		}

		return picturesByName;
	}


	private BinaryData readEntry(ZipFile zf, ZipEntry zipEntry, String simpleName) throws Exception {
		String contentType = BinaryData.CONTENT_TYPE_OCTET_STREAM;
		try (InputStream inputStream = zf.getInputStream(zipEntry)) {
			// do this for the POI reader. It needs a file ending with "xls"
			String name = Math.random() + "_" + simpleName;
			return BinaryDataFactory.createFileBasedBinaryData(inputStream, contentType, name);
		}
	}

	private File dumpToFile(InputStream content) throws IOException, FileNotFoundException {
		File file = File.createTempFile("FileBasedBinaryData", ".bin", Settings.getInstance().getTempDir());
		try (OutputStream output = new FileOutputStream(file)) {
			StreamUtilities.copyStreamContents(content, output);
		}
		return file;
	}

	private File getZipFile(BinaryContent data) throws Exception {
		return dumpToFile(data.getStream());
	}

	public RuntimeException asRuntimeException(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException(e);
	}

}

