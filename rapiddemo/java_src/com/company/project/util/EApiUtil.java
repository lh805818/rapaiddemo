package com.company.project.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.company.project.model.TradePack;

public class EApiUtil {

	public static String callInnerSign(TradePack tradePack) {
		if (tradePack == null) {
			return null;
		}
		
		tradePack.setTradeTime(new Date());
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			out.write(getBytes(tradePack.getAppId()));
			out.write(getBytes(tradePack.getMyFlowNo()));
			out.write(getBytes(tradePack.getTradeNo()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			out.write(getBytes(sdf.format(tradePack.getTradeTime())));
			
			return md5Hash(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String md5Hash(byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return encodeHex(md.digest(data));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String encodeHex(byte[] data) {
		int len = data.length;
		StringBuilder sbu = new StringBuilder();
		
		for (int i=0;i<len;i++) {
			if (((int)data[i] & 0xff) < 0x10) {
				sbu.append("0");
			}
			
			sbu.append(Integer.toString((int)data[i] & 0xff, 16));
		}
		
		return sbu.toString().toUpperCase();
	}
	
	public static byte[] getBytes(String str) {
		if (str == null) {
			return null;
		}
		
		try {
			return str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
