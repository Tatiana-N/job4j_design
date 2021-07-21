package ru.job4j.solid.ocp;

import ru.job4j.design.srp.Employee;

import java.util.List;

public class Statistic {
	// 1 нарушение не сможем подложить другую реализацию класса
	CounterSalary counterSalary;
	List<Employee> employeeList;
	
	public Statistic(CounterSalary counterSalary, List<Employee> employeeList) {
		this.counterSalary = counterSalary;
		this.employeeList = employeeList;
	}
	
	public void getStatistic() {
		for (Employee employee : employeeList) {
			counterSalary.count(employee);
		}
		
	}
}
