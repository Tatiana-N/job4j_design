package ru.job4j.solid.lsp.products;

import java.util.Calendar;

public class Util {
	public static int getPercent(Food food) {
		if (food.createDate.after(food.expiryDate)) {
			throw new RuntimeException("it's impossible");
		}
		long now = Calendar.getInstance().getTimeInMillis();
		long expiryDateTime = food.getExpiryDate().getTimeInMillis();
		long createDateTime = food.getCreateDate().getTimeInMillis();
		long milliSecondsGone = now - createDateTime;
		long milliSecondsToExpiry = expiryDateTime - createDateTime;
		return (int) ((double) milliSecondsGone / (double) milliSecondsToExpiry * 100);
	}
}
