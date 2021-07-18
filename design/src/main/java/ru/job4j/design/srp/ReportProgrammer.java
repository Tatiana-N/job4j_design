package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportProgrammer implements Report {
	Store store;
	
	public ReportProgrammer(Store store) {
		this.store = store;
	}
	
	@Override
	public String generate(Predicate<Employee> filter) {
		ReportEngine reportEngine = new ReportEngine(store);
		return HtmlConverter.stringToHTMLString(reportEngine.generate(filter));
	}
}
