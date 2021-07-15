package ru.job4j.solid;


import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
	public <T> T max(List<T> value, Comparator<T> comparator) {
		return getMinOrMax(value, comparator, predicate -> predicate < 0);
	}
	
	public <T> T min(List<T> value, Comparator<T> comparator) {
		return getMinOrMax(value, comparator, predicate -> predicate > 0);
	}
	
	private <T> T getMinOrMax(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
		if (value.size() == 0) {
			return null;
		}
		T temp = value.get(0);
		
		for (T element : value) {
			if (predicate.test(comparator.compare(temp, element))) {
				temp = element;
			}
		}
		return temp;
	}
}

