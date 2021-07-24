package ru.job4j.solid.lsp.products;

import ru.job4j.solid.lsp.products.api.ParserDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ParseDateToCalendar implements ParserDate<Calendar> {
	private final SimpleDateFormat sdf;
	
	public ParseDateToCalendar(String format) {
		this.sdf = new SimpleDateFormat(format);
	}
	
	@Override
	public Calendar parseString(String date) {
		
		Calendar dateCalendar = Calendar.getInstance();
		try {
			dateCalendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			throw new RuntimeException("пишите дату в формате dd.MM.yyyy");
		}
		return dateCalendar;
	}
	
	@Override
	public DateFormat getFormatter() {
		return sdf;
	}
}
