package ru.job4j.design.srp;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReportTest {
	@Test
	public void whenOldGenerated() {
		MemStore store = new MemStore();
		Calendar now = Calendar.getInstance();
		Employee worker = new Employee("Ivan", now, now, 100);
		store.add(worker);
		Report engine = new ReportEngine(store);
		StringBuilder expect = new StringBuilder().append("Name; Hired; Fired; Salary;").append(System.lineSeparator()).append(worker.getName()).append(";").append(worker.getHired()).append(";").append(worker.getFired()).append(";").append(worker.getSalary()).append(";").append(System.lineSeparator());
		assertThat(engine.generate(em -> true), is(expect.toString()));
	}
	
	@Test
	public void forProgrammerReport() {
		MemStore store = new MemStore();
		Calendar now = Calendar.getInstance();
		Employee worker = new Employee("Ivan", now, now, 100);
		store.add(worker);
		Report programmer = new ReportProgrammer(store);
		//Через месяц применения системы отчетности отдел программистов потребовал ответы в виде html.
		StringBuilder expect = new StringBuilder().append("<br>Name; Hired; Fired; Salary;</br>").append(System.lineSeparator()).append("<br>").append(worker.getName()).append(";").append(worker.getHired()).append(";").append(worker.getFired()).append(";").append(worker.getSalary()).append(";").append("</br>").append(System.lineSeparator());
		assertThat(programmer.generate(em -> true), is(expect.toString()));
	}
	
	@Test
	public void forAccountantReport() {
		MemStore store = new MemStore();
		Calendar now = Calendar.getInstance();
		Employee worker = new Employee("Ivan", now, now, 100);
		store.add(worker);
		Report programmer = new ReportAccountant(store);
		//Отдел бухгалтерии попросил изменить вид зарплаты.
		StringBuilder expect = new StringBuilder().append("Name; Hired; Fired; Salary;").append(System.lineSeparator()).append(worker.getName()).append(";").append(worker.getHired()).append(";").append(worker.getFired()).append(";").append(String.format("%.2f", worker.getSalary())).append(";").append(System.lineSeparator());
		assertThat(programmer.generate(em -> true), is(expect.toString()));
	}
	
	@Test
	public void forHRReport() {
		MemStore store = new MemStore();
		Calendar now = Calendar.getInstance();
		Employee worker1 = new Employee("Ivan", now, now, 100);
		Employee worker2 = new Employee("Antony", now, now, 200);
		store.add(worker1);
		store.add(worker2);
		Report programmer = new ReportHr(store);
		//Отдел HR попросил выводить сотрудников в порядке убывания зарплаты и убрать поля даты найма и увольнения.
		StringBuilder expect = new StringBuilder().append("Name; Salary;").append(System.lineSeparator()).append(worker2.getName()).append(";").append(worker2.getSalary()).append(";").append(System.lineSeparator()).append(worker1.getName()).append(";").append(worker1.getSalary()).append(";").append(System.lineSeparator());
		assertThat(programmer.generate(em -> true), is(expect.toString()));
	}
	
	@Test
	public void xmlFormat() {
		MemStore store = new MemStore();
		Calendar now = Calendar.getInstance();
		Employee worker = new Employee("Ivan", now, now, 100);
		Employee worker2 = new Employee("Antony", now, now, 200);
		store.add(worker);
		store.add(worker2);
		Report someOne = new ReportXml(store);
		//Кто-то захотел отчет в формате XML.
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		StringBuilder expect = new StringBuilder().append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n<employees>").append(String.format("\n<employee>\n<name>%s</name>\n<hired>%s</hired>\n<fired>%s</fired>\n<salary>%s</salary>\n</employee>", worker.getName(), df.format(worker.getHired().getTime()), df.format(worker.getFired().getTime()), worker.getSalary())).append(String.format("\n<employee>\n<name>%s</name>\n<hired>%s</hired>\n<fired>%s</fired>\n<salary>%s</salary>\n</employee>", worker2.getName(), df.format(worker2.getHired().getTime()), df.format(worker2.getFired().getTime()), worker2.getSalary())).append("\n</employees>\n");
		assertThat(someOne.generate(em -> true).replace(" ", ""), is(expect.toString().replace(" ", "")));
	}
	
	@Test
	public void jsonFormat() {
		MemStore store = new MemStore();
		Calendar now = Calendar.getInstance();
		Employee worker = new Employee("Ivan", now, now, 100);
		Employee worker2 = new Employee("Antony", now, now, 200);
		store.add(worker);
		store.add(worker2);
		Report someOne = new ReporJson(store);
		//Кто-то захотел отчет в формате json.
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		StringBuilder expect = new StringBuilder().append("[").append(String.format("{" + "\"fired\":\"%s\"," + "\"name\":\"%s\"," + "\"hired\":\"%s\"," + "\"salary\":%s}", df.format(worker.getHired().getTime()), worker.getName(), df.format(worker.getFired().getTime()), (int) worker.getSalary())).append(",").append(String.format("{" + "\"fired\":\"%s\"," + "\"name\":\"%s\"," + "\"hired\":\"%s\"," + "\"salary\":%s}]", df.format(worker2.getHired().getTime()), worker2.getName(), df.format(worker2.getFired().getTime()), (int) worker2.getSalary()));
		System.out.println(someOne.generate(em -> true));
		assertThat(someOne.generate(em -> true), is(expect.toString()));
	}
}