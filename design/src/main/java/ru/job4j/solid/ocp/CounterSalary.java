package ru.job4j.solid.ocp;

import ru.job4j.design.srp.Employee;
//2 нарушение даже не сможем сделать потомка и переопределить метод count
public final class CounterSalary {
	
	public void count(Employee employee) {
		//как-то считается
	}
}
