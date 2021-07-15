package ru.job4j.tdd;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CinemaTest {
	
	@Test
	public void find() {
		Cinema cinema = new Cinema3D();
		cinema.add(new Session3D());
		List<Session> sessions = cinema.find(session -> true);
		assertThat(sessions, is(Arrays.asList(new Session3D())));
	}
	
	@Test
	public void buy() {
		Account account = new AccountCinema();
		Cinema cinema = new Cinema3D();
		Calendar date = Calendar.getInstance();
		date.set(2020, 10, 10, 23, 00);
		Ticket ticket = cinema.buy(account, 1, 1, date);
		assertThat(ticket, is(new Ticket3D()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void incorrectData() {
		Account account = new AccountCinema();
		Cinema cinema = new Cinema3D();
		Calendar date = Calendar.getInstance();
		date.set(1820, 10, 10, 23, 00);
		Ticket ticket = cinema.buy(account, 1, 1, date);
		assertThat(ticket, is(new Ticket3D()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void wrongTicket() {
		Account account = new AccountCinema();
		Cinema cinema = new Cinema3D();
		Calendar date = Calendar.getInstance();
		date.set(2020, 10, 10, 23, 00);
		Ticket ticket = cinema.buy(account, -1, 1, date);
		assertThat(ticket, is(new Ticket3D()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void boughtTicket() {
		Account account = new AccountCinema();
		Cinema cinema = new Cinema3D();
		Calendar date = Calendar.getInstance();
		date.set(2020, 10, 10, 23, 00);
		Ticket ticket1 = cinema.buy(account, 1, 1, date);
		Ticket ticket2 = cinema.buy(account, 1, 1, date);
	}
	
	@Test
	public void add() {
		Cinema cinema = new Cinema3D();
		Session3D session3D = new Session3D();
		cinema.add(session3D);
		List<Session> sessions = cinema.find(session -> true);
		assertThat(sessions.get(0), is(session3D));
	}
}