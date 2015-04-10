package com.company.project.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Util {
	private static Logger log = Logger.getLogger(Util.class);

	/**
	 * sha1加密
	 *
	 * @author qincd
	 * @date Nov 3, 2014 4:16:39 PM
	 */
	public static String sha1(String str) {
		if (str == null || str.length() == 0) return "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] bytes = md.digest(str.getBytes());
			return byte2Hex(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.error("SHA1加密出错：" + e.getMessage());
			throw new RuntimeException("SHA1加密出错");
		}
	}
	
	public static String byte2Hex(byte[] data) {
		if (data == null || data.length == 0) return "";
		
		StringBuilder sbu = new StringBuilder();
		
		for (int i=0;i<data.length;i++) {
			if ((data[i] & 0xff) < 0x10) {
				sbu.append("0");
			}
			sbu.append(Integer.toHexString(data[i] & 0xff));
		}
		
		return sbu.toString();
	}
	
}
