package com.top_logic.synchra.importer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.top_logic.basic.Logger;
import com.top_logic.basic.io.BinaryContent;
import com.top_logic.basic.io.binary.BinaryData;
import com.top_logic.element.model.imagegallery.GalleryImage;
import com.top_logic.element.model.imagegallery.ImageGalleryFactory;
import com.top_logic.knowledge.service.PersistencyLayer;
import com.top_logic.knowledge.service.Transaction;
import com.top_logic.synchra.model.ModelFactory;
import com.top_logic.synchra.model.interfaces.Component;
import com.top_logic.synchra.util.AttributedUtil;

/**
 * Imports data from a zip file
 */
public class PictureImporter extends ZipImporter {

	public PictureImporter(BinaryContent data) {
		super(data);
	}

	public PictureImporter(ZipReader reader) {
		super(reader);
	}

	public void performImportWithCommit() {
		try (Transaction ta = PersistencyLayer.getKnowledgeBase().beginTransaction()) {
			Map<String, BinaryData> picturesByName = getPicturesByName();
			importPictures(picturesByName);
			ta.commit();
		}
	}


	private Map<String, BinaryData> getPicturesByName() {
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


	private void importPictures(Map<String, BinaryData> picturesByName) {
		// get all components
		Map<String, Component> componentsByName = getComponentsByName();

		// pair entries and components by name

		// log unpaired entries
		// create pictures in components
		Set<String> componentNames = componentsByName.keySet();
		for (String pictureName : picturesByName.keySet()) {
			if (!componentNames.contains(pictureName)) {
				Logger.warn("No component for picture " + pictureName, PictureImporter.class);
			} else {
				createPicture(pictureName, picturesByName.get(pictureName), componentsByName.get(pictureName));
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

}
