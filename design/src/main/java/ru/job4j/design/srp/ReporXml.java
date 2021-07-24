package ru.job4j.design.srp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

public class ReporXml implements Report {
	private final Store store;
	
	public ReporXml(Store store) {
		this.store = store;
	}
	
	@Override
	public String generate(Predicate<Employee> filter) {
		List<Employee> employeeList = store.findBy(filter);
		StringBuilder result = new StringBuilder();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		result.append("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>")
				.append(System.lineSeparator());
		for (Employee worker : employeeList) {
			result.append(String.format("<employee name=%s fired=%s hired=%s  salary=%.2f/>",
					worker.getName(), df.format(worker.getHired().getTime()), df.format(worker.getFired().getTime()), worker.getSalary()))
					.append(System.lineSeparator());
		}
		return result.toString();
	}
}
