package ru.job4j.design.srp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		JSONArray jsonArray = new JSONArray();
		for (Employee employee : employees) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", employee.getName());
			jsonObject.put("hired", df.format(employee.getHired().getTime()));
			jsonObject.put("fired", df.format(employee.getFired().getTime()));
			jsonObject.put("salary", employee.getSalary());
			jsonArray.put(jsonObject);
			
		}
		return jsonArray.toString();
	}
}
