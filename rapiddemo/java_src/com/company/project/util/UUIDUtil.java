package com.company.project.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

	public static String generateUUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}
	public static Long generateLong() {
		Random r = new Random();
		r.setSeed(99999999L);
		return r.nextLong();
	}
	
	public static void main(String[] args) {
		System.out.println(generateUUID());
	}
}
