package com.top_logic.synchra.importer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;

import com.top_logic.basic.col.ListBuilder;
import com.top_logic.basic.col.SetBuilder;
import com.top_logic.basic.io.BinaryContent;
import com.top_logic.contact.business.POIExcelImporter;
import com.top_logic.layout.provider.MetaLabelProvider;
import com.top_logic.model.TLClass;
import com.top_logic.model.TLProperty;
import com.top_logic.synchra.importer.attribute.AttributeImport;
import com.top_logic.synchra.importer.attribute.PropertyImport;
import com.top_logic.synchra.importer.attribute.ReferenceImport;
import com.top_logic.synchra.importer.transformer.TLEnumerationTransformer;
import com.top_logic.synchra.importer.transformer.TLObjectTransformer;
import com.top_logic.synchra.importer.transformer.ValueTransformer;
import com.top_logic.synchra.model.ModelFactory;
import com.top_logic.synchra.model.interfaces.Activity;
import com.top_logic.synchra.model.interfaces.Company;
import com.top_logic.synchra.model.interfaces.Connection;
import com.top_logic.synchra.model.interfaces.Material;
import com.top_logic.synchra.model.interfaces.PartCatalog;
import com.top_logic.synchra.model.interfaces.Problem;
import com.top_logic.synchra.model.interfaces.Product;
import com.top_logic.synchra.model.interfaces.SinglePart;
import com.top_logic.synchra.risk.RiskFactory;
import com.top_logic.synchra.risk.interfaces.Risk;

/**
 * Imports data for synchra
 */
public class SynchraImporter extends POIExcelImporter {

	private List<TypeImporter> _importers;
	private Map<String, TypeImporter> _typeImporters;

	private ImportSession _session;

	
	public SynchraImporter(BinaryContent importSource) {
		super(importSource);
		setupTypeImporters();
	}

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

	private void setupTypeImporters() {
		
		_importers = new ArrayList<>();
		_typeImporters = new HashMap<>();
		_session = new ImportSession();

		// create the importers.
		// some of them need other ones to be able to access previously created objects
		TypeImporter materialImporter = createMaterialImporter();
		TypeImporter catalogImporter = createCatalogImporter();

		TypeImporter companyImporter = createCompanyImporter();

		TypeImporter productImporter = createProductImporter();
		TypeImporter activityImporter = createActivityImporter();

		TypeImporter partsImporter = createPartsImporter();

		TypeImporter riskImporter = createRiskImporter();
		TypeImporter problemImporter = createProblemImporter();
		TypeImporter connectionImporter = createConnectionImporter();

		// the sequence of registration defines the sequence of imports
		registerImporter(materialImporter);
		registerImporter(catalogImporter);
		registerImporter(companyImporter);
		registerImporter(partsImporter);
		registerImporter(productImporter);
		registerImporter(activityImporter);
		registerImporter(riskImporter);
		registerImporter(problemImporter);
		registerImporter(connectionImporter);

		for (TypeImporter importer : _importers) {
			importer.prepare(_session);
		}

	}

	private TypeImporter createConnectionImporter() {
		Set<AttributeImport> connectionAttrs = getConnectionAttrs();
		List<String> idAttributes = new ListBuilder<String>()
			.add(Connection.CONSISTS_OF_ATTR)
			.add(Connection.USED_ATTR)
			.add(Connection.RULE_ATTR)
			.add(Connection.POSITION_ATTR)
			.toList();

		return createImporter(ModelFactory.getConnectionType(), connectionAttrs, idAttributes);
	}

	private TypeImporter createImporter(TLClass tlClass, Set<AttributeImport> attributes, List<String> idAttributes) {
		return new TypeImporter(tlClass, attributes, idAttributes);
	}

	private TypeImporter createImporter(TLClass tlClass, Set<AttributeImport> attributes, String idAttribute) {
		return createImporter(tlClass, attributes, Collections.singletonList(idAttribute));
	}

	private TypeImporter createProblemImporter() {
		return createImporter(ModelFactory.getProblemType(), getProblemAttrs(), Problem.NAME);
	}

	private TypeImporter createRiskImporter() {
		return createImporter(RiskFactory.getRiskType(), getRiskAttrs(), Risk.NAME);
	}

	private TypeImporter createActivityImporter() {
		return createImporter(ModelFactory.getActivityType(), getActivityAttrs(), Activity.NAME);
	}


	private TypeImporter createProductImporter() {
		Set<AttributeImport> atts = propertyImports(ModelFactory.getNameProductAttr());
		return new ProductImporter(ModelFactory.getProductType(), atts, Product.NAME);
	}

	private TypeImporter createPartsImporter() {
		return createImporter(ModelFactory.getSinglePartType(), getPartsAttrs(), SinglePart.NAME);
	}
	
	private TypeImporter createCompanyImporter() {
		Set<AttributeImport> atts = getCompanyAttrs();
		return createImporter(ModelFactory.getCompanyType(), atts, Company.NAME);
	}

	private TypeImporter createCatalogImporter() {
		Set<AttributeImport> atts = propertyImports(ModelFactory.getNamePartCatalogAttr());
		return createImporter(ModelFactory.getPartCatalogType(), atts, PartCatalog.NAME);
	}

	private TypeImporter createMaterialImporter() {
		Set<AttributeImport> atts = propertyImports(ModelFactory.getNameMaterialAttr());
		return createImporter(ModelFactory.getMaterialType(), atts, Material.NAME);
	}

	private Set<AttributeImport> getConnectionAttrs() {
		return new SetBuilder<AttributeImport>()
			.add(new PropertyImport(ModelFactory.getPositionConnectionAttr()))
			.add(new PropertyImport(ModelFactory.getRuleConnectionAttr()))
			.add(new ReferenceImport(ModelFactory.getConsistsOfConnectionAttr(), getConstructionGroupTransformer()))
			.add(new ReferenceImport(ModelFactory.getUsedConnectionAttr(), getConstructionGroupTransformer()))
			.toSet();
	}

	private Set<AttributeImport> getProblemAttrs() {
		return new SetBuilder<AttributeImport>()
			.add(new PropertyImport(ModelFactory.getNameProblemAttr()))
			.add(new ReferenceImport(ModelFactory.getSolvingActivitiesProblemAttr(), getActivityTransformer()))
			.add(new ReferenceImport(ModelFactory.getComponentsProblemAttr(), getPartsTransformer()))
			.toSet();
	}

	private Set<AttributeImport> getActivityAttrs() {
		Set<AttributeImport> atts = new SetBuilder<AttributeImport>()
			.add(new PropertyImport(ModelFactory.getNameActivityAttr()))
			.add(new PropertyImport(ModelFactory.getCostsActivityAttr()))
			.add(new PropertyImport(ModelFactory.getRiskProbImpactActivityAttr()))
			.add(new PropertyImport(ModelFactory.getRiskCostImpactActivityAttr()))
			.toSet();
		return atts;
	}

	private Set<AttributeImport> getCompanyAttrs() {
		Set<AttributeImport> atts =
			new SetBuilder<AttributeImport>()
				.add(new PropertyImport(ModelFactory.getNameCompanyAttr()))
				.add(new PropertyImport(ModelFactory.getContactPersonCompanyAttr()))
				.add(new PropertyImport(ModelFactory.getPhoneCompanyAttr()))
				.add(new ReferenceImport(ModelFactory.getStructureCompanyAttr(),
					new TLEnumerationTransformer(ModelFactory.getCompanyStructureEnum())))
				.toSet();
		return atts;
	}



	private void registerImporter(TypeImporter importer) {
		// the sequence of the imports is important
		_importers.add(importer);
		_typeImporters.put(importer.getSheetName(), importer);
	}

	private Set<AttributeImport> getRiskAttrs() {
		return new SetBuilder<AttributeImport>()
			.add(new PropertyImport(RiskFactory.getNameRiskAttr()))
			.add(new PropertyImport(RiskFactory.getEstimatedDamageRiskAttr()))
			.add(new PropertyImport(RiskFactory.getEstimatedProbabilityRiskAttr()))
			.add(new ReferenceImport(RiskFactory.getActivitiesRiskAttr(), getActivityTransformer()))
			.toSet();
	}

	private ValueTransformer getActivityTransformer() {
		return getTransformer(ModelFactory.getActivityType(), ModelFactory.getNameActivityAttr());
	}

	private ValueTransformer getPartsTransformer() {
		return getTransformer(ModelFactory.getSinglePartType(), ModelFactory.getNameComponentAttr());
	}

	private ValueTransformer getMaterialTransformer() {
		return getTransformer(ModelFactory.getMaterialType(), ModelFactory.getNameMaterialAttr());
	}

	private ValueTransformer getCatalogTransformer() {
		return getTransformer(ModelFactory.getPartCatalogType(), ModelFactory.getNamePartCatalogAttr());
	}

	private ValueTransformer getCompanyTransformer() {
		return getTransformer(ModelFactory.getCompanyType(), ModelFactory.getNameCompanyAttr());
	}

	private ValueTransformer getConstructionGroupTransformer() {
		return getTransformer(ModelFactory.getConstructionGroupType(), ModelFactory.getNameComponentAttr(), true);
	}

	private ValueTransformer getTransformer(TLClass tlClass, TLProperty idAttribute) {
		return getTransformer(tlClass, idAttribute, false);
	}

	private ValueTransformer getTransformer(TLClass tlClass, TLProperty idAttribute, boolean create) {
		return new TLObjectTransformer(tlClass, idAttribute, create);
	}

	private Set<AttributeImport> getPartsAttrs() {
		return
			new SetBuilder<AttributeImport>()
				.add(new PropertyImport(ModelFactory.getNameComponentAttr()))
				.add(new PropertyImport(ModelFactory.getPriceComponentAttr()))
				.add(new ReferenceImport(ModelFactory.getMaterialSinglePartAttr(), getMaterialTransformer()))
				.add(new ReferenceImport(ModelFactory.getCatalogSinglePartAttr(), getCatalogTransformer()))
				.add(new ReferenceImport(ModelFactory.getCompanySinglePartAttr(), getCompanyTransformer()))
				.toSet();
	}


	private Set<AttributeImport> propertyImports(TLProperty... properties){
		Set<AttributeImport> res = new HashSet<>();
		for(TLProperty property : properties) {
			res.add(new PropertyImport(property));
		}
		return res;
	}


	/**
	 * @param obj
	 *        the object to get a label for
	 * @return the label for the given object in the language of the current user
	 */
	public static String label(Object obj) {
		return MetaLabelProvider.INSTANCE.getLabel(obj);
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
