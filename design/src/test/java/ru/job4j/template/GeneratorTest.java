package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GeneratorTest {
	@Test
	public void produceCorrect() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "Axel");
		map.put("subject ", "you");
		Generator generator = new GeneratorImpl();
		String produced = generator.produce("I am a ${name}, Who are ${subject}?", map);
		assertThat(produced, is("I am a Axel, Who are you?"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void produceNotEnoughFields() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "Axel");
		Generator generator = new GeneratorImpl();
		generator.produce("I am a ${name}, Who are ${subject}?", map);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void produceMoreFields() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "Axel");
		map.put("subject ", "you");
		map.put("object ", "me");
		Generator generator = new GeneratorImpl();
		generator.produce("I am a ${name}, Who are ${subject}?", map);
	}
}