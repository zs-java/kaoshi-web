package com.xzcoder.kaoshi.admin.controller.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * DateConvert
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String source) {

		//实现将日期串转换成java.util.Date类型
		String pattern = source.indexOf(":") > -1 ? "yyyy-MM-dd HH:mm" : "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//如果参数绑定失败返回null
		return null;
	}

}
