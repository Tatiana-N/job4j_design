package ru.job4j.solid.lsp.products;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.solid.lsp.products.api.ParserDate;

import java.text.DateFormat;
import java.util.Calendar;

@Setter
@Getter
public class Food {
	String name;
	Calendar expiryDate;
	Calendar createDate;
	double price;
	double discount;
	
	public Food(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
		this.name = name;
		this.expiryDate = expiryDate;
		this.createDate = createDate;
		this.price = price;
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		ParserDate<Calendar> parserDate = new ParseDateToCalendar("dd.MM.yyyy");
		DateFormat sdf = parserDate.getFormatter();
		return "Food{" + "name='" + name + '\'' + ", expiryDate=" + sdf.format(expiryDate.getTime()) + ", createDate=" + sdf.format(createDate.getTime()) + ", price=" + price + ", discount=" + discount + '}';
	}
}
