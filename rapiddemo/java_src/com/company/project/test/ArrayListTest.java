package com.company.project.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

	/**
	 *
	 * @author qincd
	 * @throws IOException 
	 * @date Nov 17, 2014 10:37:45 AM
	 */
	public static void main(String[] args) throws IOException {
		// 测试以不同的顺序往ArrayList中添加数据，最后MD5的数据是否是一样的。
		List<FieldObj> fields = new ArrayList<FieldObj>();
		fields.add(new FieldObj("name",getBytes("张三丰"),"1"));
		fields.add(new FieldObj("age",getBytes("20"),"1"));
		fields.add(new FieldObj("time",getBytes(System.currentTimeMillis()+""),"1"));
		
		test(fields);
		
		List<FieldObj> fields2 = new ArrayList<FieldObj>();
		fields2.add(new FieldObj("time",getBytes(System.currentTimeMillis()+""),"1"));
		fields2.add(new FieldObj("age",getBytes("20"),"1"));
		fields2.add(new FieldObj("name",getBytes("张三丰"),"1"));
		
		test(fields2);
	}
	
	public static void test(List<FieldObj> fields) throws IOException {
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		for (int i=0,j=fields.size();i < j;i++) {
			byteout.write(fields.get(i).getValue());
		}
		
		String md5data = md5(byteout.toByteArray());
		System.out.println("md5data:" + md5data);
		byteout.close();
	}
	
	public static String md5(byte[] data) {
		if (data == null || data.length == 0)
			return "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return encodeHex(md.digest(data));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static String encodeHex(byte[] data) {
		if (data == null || data.length == 0)
			return "";
		
		StringBuffer buffer = new StringBuffer();
		for (int i=0;i<data.length;i++) {
			if ((data[i] & 0xff) < 10) {
				buffer.append("0");
			}
			
			buffer.append(Long.toHexString(data[i] & 0xff));
		}
		
		return buffer.toString().toUpperCase();
	}

	public static byte[] getBytes(String value) {
		if (value == null || value.length() == 0)
			return null;
		try {
			return value.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
class FieldObj implements Serializable {
	private String name;
	private byte[] value;
	private String format;
	
	public FieldObj() {}
	public FieldObj(String name,byte[] value,String format) {
		this.setName(name);
		this.setValue(value);
		this.setFormat(format);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public byte[] getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(byte[] value) {
		this.value = value;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	
}
