package ua.kerberos.search.specification.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.HashMap;

public class SomeTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 255, 255 * 255, 255 * 255 * 255, 0, 256, 256 * 256, 256 * 256 * 256})
	public void test(int h) {
		//return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

		System.out.println(h + " : " + ((h) ^ (h >>> 16)));

		System.out.println(Integer.toBinaryString(h));
		System.out.println(Integer.toBinaryString(((h) ^ (h >>> 16))));
	}
}
