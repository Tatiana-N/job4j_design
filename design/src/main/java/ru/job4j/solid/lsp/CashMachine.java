package ru.job4j.solid.lsp;

public class CashMachine {
	double getMoney(double moneyRequest) {
		//Предусловие
		if (moneyRequest < 0) {
			throw new IllegalArgumentException();
		}
		double result = moneyRequest - moneyRequest * 0.1;
		//Постусловие
		if (result > 0) {
			return result;
		}
		throw new IllegalArgumentException();
	}
}
