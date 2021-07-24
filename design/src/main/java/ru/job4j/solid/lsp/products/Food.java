package ru.job4j.solid.lsp.products;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
@Setter
@Getter
public class Food {
	String name;
	Calendar expiryDate;
	Calendar createDate;
	double price;
	double discount;
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
	
	public Food(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
		this.name = name;
		this.expiryDate = expiryDate;
		this.createDate = createDate;
		this.price = price;
		this.discount = discount;
	}
	
	public Food(String name, String expiryDate, String createDate, double price, double discount) {
		this.name = name;
		Calendar expiryDateFromString = Calendar.getInstance();
		Calendar createDateFromString = Calendar.getInstance();
		try {
			expiryDateFromString.setTime(sdf.parse(expiryDate));
			createDateFromString.setTime(sdf.parse(createDate));
		} catch (ParseException e) {
			throw new RuntimeException("пишите дату в формате dd.MM.yyyy");
		}
		this.expiryDate = expiryDateFromString;
		this.createDate = createDateFromString;
		this.price = price;
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		return "Food{" + "name='" + name + '\'' + ", expiryDate=" + sdf.format(expiryDate.getTime()) + ", createDate=" + sdf.format(createDate.getTime()) + ", price=" + price + ", discount=" + discount + '}';
	}
}
