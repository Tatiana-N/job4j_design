package ru.job4j.design.srp;

public class HtmlConverter {
	public static String stringToHTMLString(String stringForConverting) {
		StringBuilder sb = new StringBuilder(stringForConverting.length());
		String[] strings = stringForConverting.split(System.lineSeparator());
		for (String string : strings) {
			sb.append("<br>");
			sb.append(string);
			sb.append("</br>");
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
