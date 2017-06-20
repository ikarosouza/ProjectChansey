package br.imd.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String DATE_PATTERN = "dd/MM/yyyy";

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMAT.format(date);
	}
	
	public static Date parse(String date){
		try {
			return DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
