package com.top_logic.synchra.importer.transformer;


import java.util.Date;

import com.top_logic.basic.DateUtil;

/**
 * transformer for dates given by a number relative to today
 * 
 * @author fma
 *
 */
public class RelativeDateTransformer implements ValueTransformer {

	public static final ValueTransformer INSTANCE = new RelativeDateTransformer();

	@Override
	public Object get(Object excelValue) {
		if (excelValue == null) {
			return null;
		}
		Number d = (Number) excelValue;
		int intValue = d.intValue();
		return DateUtil.addDays(new Date(), intValue);
	}

}
