package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportXml implements Report {
	private final Store store;
	
	public ReportXml(Store store) {
		this.store = store;
	}
	
	@Override
	public String generate(Predicate<Employee> filter) {
		List<Employee> employeeList = store.findBy(filter);
		Employees employees = new Employees(employeeList);
		StringWriter sw = new StringWriter();
		try {
			JAXBContext jc2 = JAXBContext.newInstance(Employees.class);
			Marshaller marshaller2 = jc2.createMarshaller();
			marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller2.marshal(employees, sw);
			System.out.println(sw.toString());
			return sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
