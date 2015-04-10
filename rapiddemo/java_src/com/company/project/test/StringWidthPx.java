package com.company.project.test;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.hibernate.mapping.Array;

import net.sf.json.JSONObject;

/**
 * 使用Graphics.getFontMetrics().getStringWidth()计算字符串所占的宽度。
 * @author qince
 *
 */
public class StringWidthPx {

	/**
	 *
	 * @author qincd
	 * @email qincd@hyxt.com
	 * @date Dec 11, 2014 2:22:19 PM
	 */
	public static void main(String[] args) {
//		String str1 = "JAVA";
//		String str2 = "string";
//		JFrame f = new JFrame();
//		f.setVisible(true);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setBounds(new Rectangle(500,300));
//		Graphics2D g = (Graphics2D) f.getGraphics();
//		Font font = new Font("宋体",Font.PLAIN,24);
//		g.setFont(font);
//		g.drawString(str1, 100, 100);
//		int str2Width = g.getFontMetrics().stringWidth(str2);
//		System.out.println(str2Width);
//		int str2Height = g.getFontMetrics().getHeight();
//		System.out.println(str2Height);
//		g.drawString(str2, 100 + str2Width, 100);
//		g.dispose();
		
		JSONObject obj1 = new JSONObject();
		
		List<Map<String,String>> photos = new ArrayList<Map<String,String>>();
		
		Map<String,String> map1 = new HashMap<String, String>();
		map1.put("photo_url", "1.jpng");
		
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("photo_url", "1.jpng");
		photos.add(map1);
		photos.add(map2);
		obj1.put("photos", photos);
		
		System.out.println(obj1.toString());

		try {
			System.out.println(URLEncoder.encode("今天/刘德华","utf8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
