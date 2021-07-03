package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
	
	protected final Map<K, SoftReference<V>> cache = new HashMap<>();
	
	public void put(K key, V value) {
		cache.put(key, new SoftReference<>(value));
		System.out.printf("Файл %s добавлен в кеш \n", key);
	}
	
	public V get(K key) {
		SoftReference<V> softReference = cache.get(key);
		V value;
		if (softReference == null || (value = softReference.get()) == null) {
			return load(key);
		}
		System.out.printf("Файл с именем %s загружен из кеша \n", key);
		return value;
	}
	
	protected abstract V load(K key);
}