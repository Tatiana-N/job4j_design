package ru.job4j.solid.lsp;

public class MyBadCashMachine extends CashMachine {
	@Override
	double getMoney(double moneyRequest) {
		//Предусловия не могут быть усилены в подклассе
		if (moneyRequest < 0 || moneyRequest > 2000) {
			throw new IllegalArgumentException();
		}
		double result = moneyRequest - moneyRequest * 0.1;
		//Постусловия не могут быть ослаблены в подклассе
		return result;
	}
}
