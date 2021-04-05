package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

public class SimpleMapTest {
    @Test
    public void testInsert() {
        SimpleMap<Integer, String> map = new SimpleMap<>(0.75);
        map.insert(1, "Apple");
        map.insert(2, "Bpple");
        map.insert(3, "Cpple");
        map.insert(4, "Dpple");
        map.insert(10, "Fpple");
        Assert.assertEquals(map.get(1), "Apple");
        Assert.assertEquals(map.get(2), "Bpple");
        Assert.assertEquals(map.get(3), "Cpple");
        Assert.assertEquals(map.get(4), "Dpple");
        Assert.assertEquals(map.get(10), "Fpple");
    }

    @Test
    public void testGet() {
        SimpleMap<Integer, String> map = new SimpleMap<>(0.75);
        Assert.assertTrue(map.insert(17, "Apple"));
        Assert.assertFalse(map.insert(1, "Bpple"));
        Assert.assertTrue(map.insert(22, "Cpple"));
        Assert.assertTrue(map.insert(20, "Dpple"));
        Assert.assertTrue(map.insert(21, "Fpple"));
        Assert.assertNull(map.get(6));
        Assert.assertNull(map.get(1));
        Assert.assertNotNull(map.get(20));
    }

    @Test
    public void testDelete() {
        SimpleMap<Integer, String> map = new SimpleMap<>(0.75);
        map.insert(1, "Apple");
        map.insert(2, "Bpple");
        map.insert(3, "Cpple");
        Assert.assertTrue(map.delete(2));
        Assert.assertTrue(map.insert(10, "Fpple"));
        Assert.assertFalse(map.delete(2));
    }
}