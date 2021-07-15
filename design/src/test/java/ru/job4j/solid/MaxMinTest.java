package ru.job4j.solid;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {
	private static final List<Integer> INTEGERS = Arrays.asList(4, 56, 12, -5, 0, 7, 90);
	private static final List<Integer> EMPTY = new ArrayList<>();
	private static final List<String> STRINGS = Arrays.asList("a", "af", "trs", "", "0", "GYdsd", "90");
	private static final MaxMin MAX_MIN = new MaxMin();
	
	@Test
	public void max() {
		Assert.assertEquals((long) MAX_MIN.max(INTEGERS, Comparator.comparingInt(x -> x)), 90L);
		Assert.assertEquals(MAX_MIN.max(STRINGS, Comparator.comparingInt(String::length)), "GYdsd");
		Assert.assertNull(MAX_MIN.max(EMPTY, Comparator.comparingInt(x -> x)));
	}
	
	@Test
	public void min() {
		Assert.assertEquals((long) MAX_MIN.min(INTEGERS, Comparator.comparingInt(x -> x)), -5L);
		Assert.assertEquals(MAX_MIN.min(STRINGS, Comparator.comparingInt(String::length)), "");
		Assert.assertNull(MAX_MIN.min(EMPTY, Comparator.comparingInt(x -> x)));
	}
}