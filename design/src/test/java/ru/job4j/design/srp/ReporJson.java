package ru.job4j.design.srp;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ReporJson implements Report {
	private final Store store;
	
	public ReporJson(Store store) {
		this.store = store;
	}
	
	@Override
	public String generate(Predicate<Employee> filter) {
		List<Employee> employees = store.findBy(filter);
		StringBuilder sb = new StringBuilder();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		sb.append("{\"Employees\":");
		Iterator<Employee> employeeIterator = employees.iterator();
		while (employeeIterator.hasNext()) {
			Employee employee = employeeIterator.next();
			JSONObject jsonObject = new JSONObject(employee);
			jsonObject.put("name", employee.getName());
			jsonObject.put("hired", df.format(employee.getHired().getTime()));
			jsonObject.put("fired", df.format(employee.getFired().getTime()));
			jsonObject.put("salary", employee.getSalary());
			sb.append(jsonObject.toString());
			if (employeeIterator.hasNext()) {
				sb.append(",");
			}
		}
		sb.append(System.lineSeparator()).append("}");
		return sb.toString();
	}
}
