package com.xzcoder.kaoshi.admin.controller.convert;

import org.springframework.core.convert.converter.Converter;

/**
 * BooleanConvert
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class BooleanConvert implements Converter<String, Boolean> {

	@Override
	public Boolean convert(String source) {
		return source.equals("1")?true:false;
	}

}
