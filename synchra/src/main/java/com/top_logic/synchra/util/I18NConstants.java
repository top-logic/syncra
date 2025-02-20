/*
* Copyright (c) 2018 Business Operation Systems GmbH. All Rights Reserved.
*/
package com.top_logic.synchra.util;

import com.top_logic.basic.util.ResKey;
import com.top_logic.layout.I18NConstantsBase;
import com.top_logic.layout.ResPrefix;

/**
 * Internationalization of this package.
 *
 * @author <a href=mailto:msi@top-logic.com>Marc Siebenhaar</a>
 */
@SuppressWarnings("javadoc")
public class I18NConstants extends I18NConstantsBase {

	public static ResPrefix IMPORT_PICTURES;

	public static ResPrefix IMPORT_CATALOGS;

	public static ResKey ONLY_ZIP_FILES;

	public static ResKey ONLY_XLS_FILES;

	static {
		initConstants(I18NConstants.class);
	}

}
