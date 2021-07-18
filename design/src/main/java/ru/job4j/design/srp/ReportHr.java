package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHr implements Report {
	
	private final Store store;
	
	public ReportHr(Store store) {
		this.store = store;
	}
	
	@Override
	public String generate(Predicate<Employee> filter) {
		StringBuilder text = new StringBuilder();
		text.append("Name; Salary;");
		text.append(System.lineSeparator());
		List<Employee> storeBy = store.findBy(filter);
		storeBy.sort(Comparator.comparingDouble(employee -> ((Employee) employee).getSalary()).reversed());
		for (Employee employee : storeBy) {
			text.append(employee.getName()).append(";").append(employee.getSalary()).append(";").append(System.lineSeparator());
		}
		return text.toString();
	}
}