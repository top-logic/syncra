/*
 * SPDX-FileCopyrightText: 2025 (c) Business Operation Systems GmbH <info@top-logic.com>
 * 
 * SPDX-License-Identifier: LicenseRef-BOS-TopLogic-1.0
 */
package com.top_logic.synchra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.io.Files;

/**
 * Geplante Vorgehensweise:
 * 1) Ersetzen der Werte in den model-Dateien und Factories (Methode "changeModelFiles()"
 * 2) Generieren der neuen Wrapper-Dateien in Eclipse, fuer ontoloy, assembly, tag
 * 3) Verschieben der alten Wrapper-Dateien (Methode "moveOldAndgeneratedFiles()") 
 */
public class ChangeModelFiles {

    public static String MODELS_DIR="C:\\Users\\fma\\git\\difa-product\\com.top-logic.mercedes-benz.difa-product\\src\\main\\webapp\\WEB-INF\\model\\";

	public static String LAYOUTS_DIR =
		"C:\\Users\\fma\\git\\tl-synchra\\synchra\\src\\main\\webapp\\WEB-INF\\layouts\\my.macapp\\";

    public static void main(String[] args) throws Exception {   
        ChangeModelFiles cmf = new ChangeModelFiles();
		cmf.changeLayoutFiles();

//        cmf.changeModelFiles();
//        cmf.moveOldAndgeneratedFiles();
    }

    private void moveOldAndgeneratedFiles() {
    }

    private Set<File> _modelFiles;

	private Set<File> _layoutFiles;
    private List<Replacement> _replacements;

	private List<Replacement> _layoutReplacements;
    private List<Replacement> _factoryReplacements;


	private void changeLayoutFiles() throws Exception {
		initLayoutReplacements();
		getLayoutFiles();
		rewriteLayoutFiles();
	}

	private void rewriteLayoutFiles() throws Exception {
		for (File file : _layoutFiles) {
			rewriteLayoutFile(file);
		}
	}

	private void rewriteLayoutFile(File file) throws Exception {
		String content = readFile(file);
		content = performLayoutReplacements(content);
		writeFile(file, content);
	}

	private String performLayoutReplacements(String content) {
		for (Replacement rep : _layoutReplacements) {
			content = rep.replace(content);
		}
		return content;
	}

	private void getLayoutFiles() {
		_layoutFiles = new HashSet<>();
		collectLayoutFiles(new File(LAYOUTS_DIR));
	}

	private void collectLayoutFiles(File file) {
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				collectLayoutFiles(child);
			}
		} else {
			_layoutFiles.add(file);
		}
	}

	private void initLayoutReplacements() {
		_layoutReplacements = new ArrayList<>();
		_layoutReplacements.add(new Replacement("PDX.", "synchra."));
		_layoutReplacements.add(new Replacement("my.macapp", "synchra"));
	}

	private void changeModelFiles() throws Exception {
        initReplacements();
        getModelFiles();
        rewriteModelFiles();
        rewriteFactories();
    }

    private void rewriteFactories() throws Exception {
        String name = MODELS_DIR+"..\\conf\\daimlerPvmConf.config.xml";
        File configFile = new File(name);
        String content = readFile(configFile);
        for(Replacement rep : _factoryReplacements) {
            content = rep.replace(content);
        }
        writeFile(configFile, content);
    }

    private void initReplacements() {
        _replacements = new ArrayList<>();
        _factoryReplacements = new ArrayList<>();
        
        addFactoryReplacement("de.ascon_systems.daimler.pvm.assembly.model.AssemblyFactory");
        addFactoryReplacement("de.ascon_systems.daimler.pvm.model.frozenzone.FrozenZoneFactory");
        addFactoryReplacement("de.ascon_systems.daimler.pvm.grippercoverage.model.ontology.GripperCoverageFactory");
        addFactoryReplacement("de.ascon_systems.daimler.pvm.model.ontology.OntologyFactory");
        addFactoryReplacement("de.ascon_systems.daimler.pvm.system.PvmSystemModelFactory");
        addFactoryReplacement("de.ascon_systems.daimler.pvm.model.favorite.FavoriteFactory");
        addFactoryReplacement("de.ascon_systems.daimler.pvm.model.productstructure.importer.ProductStructureImportFactory");
        addFactoryReplacement("de.ascon_systems.daimler.pvm.model.tag.TagFactory");
        
        replaceModel(".pvm.assembly.model.interfaces");
        replaceModel(".pvm.assembly.model.impl"); 
        replaceModel(".pvm.model.frozenzone.");
        replaceModel(".grippercoverage.model.ontology."); 
        replaceModel(".pvm.model.ontology.");
        addReplacement(".pvm.session.",".pvm.session1.");
        addReplacement(".pvm.system.",".pvm.system1.");
        replaceModel(".pvm.model.favorite."); 
        replaceModel(".pvm.model.productstructure.");
        replaceModel(".pvm.model.tag."); 
        replaceModel(".pvm.model.favorite.");
    }
    
    
    
    private void addFactoryReplacement(String source) {
        String target = source.replace("model", "model1");
        Replacement replacement = new Replacement(source, target);
        _factoryReplacements.add(replacement);
        _replacements.add(replacement);
    }
    
    
    private void replaceModel(String source) {
        String target = source.replace("model", "model1");
        _replacements.add(new Replacement(source, target));
    }
    

    private void addReplacement(String source, String target) {
        _replacements.add(new Replacement(source, target));
    }

    private void rewriteModelFiles() throws Exception {
        for(File file : _modelFiles) {
			rewriteModelFile(file);
        }
    }

	private void rewriteModelFile(File file) throws Exception {
        String content = readFile(file);
        content = performReplacements(content);
        writeFile(file, content);
    }

    private String performReplacements(String content) {
        for(Replacement rep : _replacements) {
            content = rep.replace(content);
        }
        return content;
    }

    private void writeFile(File file, String content) throws IOException {
        Files.write(content, file, Charset.forName("UTF-8"));
    }

    private String readFile(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    private void getModelFiles() {
        _modelFiles = new HashSet<>();
        for(File child : new File(MODELS_DIR).listFiles()) {
            _modelFiles.add(child);
        }
    }

    private static class Replacement{

        private String _target;
        private String _source;

        public Replacement(String source, String target) {
            _source = source.replaceAll("\\.", "\\\\\\.");
            _target = target.replaceAll("\\.", "\\\\\\.");
        }

        public String replace(String content) {
            return content.replaceAll(_source,  _target);
        }
        
    }


}
