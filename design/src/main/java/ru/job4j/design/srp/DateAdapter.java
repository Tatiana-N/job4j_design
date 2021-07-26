package ru.job4j.design.srp;

import ru.job4j.solid.lsp.products.ParseDateToCalendar;
import ru.job4j.solid.lsp.products.api.ParserDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAdapter extends XmlAdapter<String, Calendar> {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public Calendar unmarshal(String s) {
		ParserDate<Calendar> parseDate = new ParseDateToCalendar("dd-MM-yyyy");
		return parseDate.parseString(s);
	}
	
	@Override
	public String marshal(Calendar v) {
			return dateFormat.format(v.getTime());
	}
}
