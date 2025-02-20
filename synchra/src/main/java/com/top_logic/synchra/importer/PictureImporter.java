package com.top_logic.synchra.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.top_logic.basic.Logger;
import com.top_logic.basic.Settings;
import com.top_logic.basic.io.BinaryContent;
import com.top_logic.basic.io.StreamUtilities;
import com.top_logic.basic.io.binary.BinaryData;
import com.top_logic.basic.io.binary.BinaryDataFactory;
import com.top_logic.element.model.imagegallery.GalleryImage;
import com.top_logic.element.model.imagegallery.ImageGalleryFactory;
import com.top_logic.synchra.model.ModelFactory;
import com.top_logic.synchra.model.interfaces.Component;
import com.top_logic.synchra.util.AttributedUtil;

/**
 * Imports data from a zip file
 */
public class PictureImporter {

	private BinaryContent _data;

	public PictureImporter(BinaryContent data) {
		_data = data;
	}

	public void performImport() {
		// read zip entries
		Map<String, BinaryData> dataByName;
		try {
			dataByName = getDataByName();
		} catch (Exception ex) {
			throw asRuntimeException(ex);
		}

		// get all components
		Map<String, Component> componentsByName = getComponentsByName();

		// pair entries and components by name

		// log unpaired entries
		// create pictures in components
		Set<String> componentNames = componentsByName.keySet();
		for (String pictureName : dataByName.keySet()) {
			if (!componentNames.contains(pictureName)) {
				Logger.warn("No component for picture " + pictureName, PictureImporter.class);
			} else {
				createPicture(pictureName, dataByName.get(pictureName), componentsByName.get(pictureName));
			}
		}
	}

	private void createPicture(String pictureName, BinaryData binaryData, Component component) {
		List<? extends GalleryImage> pictures = component.getPictures();
		for (GalleryImage picture : pictures) {
			if (pictureName.equals(picture.getName())) {
				// picture existing, do not import again
				Logger.info("Skip import existing picture " + pictureName, PictureImporter.class);
				return;
			}
		}
		Logger.info("Import picture " + pictureName, PictureImporter.class);
		GalleryImage image = ImageGalleryFactory.getInstance().createGalleryImage();
		image.setImage(binaryData);
		image.setName(pictureName);
		component.addPicture(image);
	}

	private Map<String, Component> getComponentsByName() {
		List<Component> allComponents =
			AttributedUtil.getAllInstancesOfType(ModelFactory.getComponentType(), Component.class);
		Map<String, Component> componentsByName = new HashMap();
		for (Component comp : allComponents) {
			String name = comp.getName();
			name = name.replaceAll("ä", "ae");
			name = name.replaceAll("ö", "oe");
			name = name.replaceAll("ü", "ue");
			name = name.replaceAll("ß", "ss");
			componentsByName.put(name, comp);
		}
		return componentsByName;
	}

	private Map<String, BinaryData> getDataByName() throws Exception {
		Map<String, BinaryData> dataByName = new HashMap();
		File zipFile = getZipFile();
		try (ZipFile zf = new ZipFile(zipFile)) {
			Enumeration<? extends ZipEntry> entries = zf.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				BinaryData data = readEntry(zf, zipEntry);
				if (data != null) {
					String name = zipEntry.getName();
					int idx = name.indexOf(".");
					if (idx > -1) {
						name = name.substring(0, idx);
					}
					dataByName.put(name, data);
				}
			}
			zf.close();
		}
		return dataByName;
	}

	private BinaryData readEntry(ZipFile zf, ZipEntry zipEntry) throws Exception {
		String zipEntryName = zipEntry.getName();
		if (zipEntryName.endsWith(".png")) {
			return readPicture(zf, zipEntry);
		}
		return null;
	}

	private BinaryData readPicture(ZipFile zf, ZipEntry zipEntry) throws Exception {
		try (InputStream inputStream = zf.getInputStream(zipEntry)) {
			return BinaryDataFactory.createFileBasedBinaryData(inputStream);
		}
	}

	private File getZipFile() throws Exception {
		return dumpToFile(_data.getStream());
	}

	private static File dumpToFile(InputStream content) throws IOException, FileNotFoundException {
		File file = File.createTempFile("FileBasedBinaryData", ".bin", Settings.getInstance().getTempDir());
		try (OutputStream output = new FileOutputStream(file)) {
			StreamUtilities.copyStreamContents(content, output);
		}
		return file;
	}


	public static RuntimeException asRuntimeException(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException(e);
	}

}
