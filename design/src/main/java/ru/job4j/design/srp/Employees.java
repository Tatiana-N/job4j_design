package ru.job4j.design.srp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {
	@XmlElement(name = "employee")
	List<Employee> list = new ArrayList<>();
	
	public Employees() {
	}
	
	public Employees(List<Employee> employee) {
		list.addAll(employee);
	}
	
	public List<Employee> getEmployees() {
		return list;
	}
}

