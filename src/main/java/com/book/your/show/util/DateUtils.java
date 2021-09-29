package com.book.your.show.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.book.your.show.response.exception.DateFormateCustomException;

public class DateUtils {
	
	@Autowired
	private static MessageSource messages;

	private DateUtils() {
		throw new IllegalStateException();
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


	public static Date today() {
		return new Date();
	}

	public static String todayStr() {
		return sdf.format(today());
	}
	
	public static Date dateConversion(String start) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date formatedDate = formatter.parse(start);
			return formatedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DateFormateCustomException(
					messages.getMessage("date.formate.error", new Object[0], new Locale("US")));
		}
	}
	
	public static Date dateConversionOnlyForDate(String dateString) {
		try {
			Date formatedDate = sdf.parse(dateString);
			return formatedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DateFormateCustomException(
					messages.getMessage("date.formate.error", new Object[0], new Locale("US")));
		}
	}

}
