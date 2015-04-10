package com.company.project.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javacommon.base.BaseRestSpringController;

@Controller
@RequestMapping(value="/httppro")
public class HttpProController extends BaseRestSpringController<Object, Integer>{

	/**
	 * 使用响应头location和302状态码实现请求重定向(地址栏中的地址发生改变)  
	 *
	 * @author qincd
	 * @date Nov 10, 2014 10:18:54 AM
	 */
	@RequestMapping(value="/location")
	public void test1(HttpServletResponse response) {
		response.setStatus(302);
		response.setHeader("Location", "pages/jsonp_demo_jquery.html");
	}
	
	/**
	 * 使用响应头Content-Encoding实现数据的压缩输出  
	 *
	 * @author qincd
	 * @throws IOException 
	 * @date Nov 10, 2014 10:33:10 AM
	 */
	@RequestMapping(value="/gzip")
	public void test2(HttpServletResponse response) throws IOException {
		String outstr = "这是输出的内容。。。。。这是输出的内容。。。。。\n这是输出的内容。。。。。这是输出的内容。。。。。\n这是输出的内容。。。。。这是输出的内容。。。。。";
		ByteArrayOutputStream bytesout = new ByteArrayOutputStream();
		System.out.println("压缩前数据大小：" + outstr.length());
		
		GZIPOutputStream gzip = new GZIPOutputStream(bytesout);
		gzip.write(outstr.getBytes());
		gzip.close();
		
		byte[] data = bytesout.toByteArray();
		System.out.println("压缩后数据大小：" + data.length);
		
		// 通知浏览器数据采用压缩格式  
		response.setCharacterEncoding("utf-8");
		response.addHeader("Content-Encoding", "gzip");
		response.addHeader("Content-Length", data+"");
		response.getOutputStream().write(data);
	}
	
	/**
	 * 使用响应头content-type设置服务器返回给client的数据类型  
	 *
	 * @author qincd
	 * @throws IOException 
	 * @date Nov 10, 2014 10:44:39 AM
	 */
	@RequestMapping(value="/contenttype")
	public void test3(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.addHeader("Content-Type", "image/png");
		String file = request.getSession().getServletContext().getRealPath("/") + "/images/IMG_0535.PNG";
		OutputStream out = response.getOutputStream();
		InputStream in = new FileInputStream(file);
		byte[] data = new byte[1024];
		int len = -1;
		while ((len = in.read(data)) != -1) {
			out.write(data,0,len);
		}
		out.close();
		in.close();
	}
	
	/**
	 * 使用响应头refresh实现页面的定时刷新
	 *
	 * @author qincd
	 * @throws IOException 
	 * @date Nov 10, 2014 11:20:12 AM
	 */
	@RequestMapping(value="/refresh")
	public void test4(HttpServletResponse response) throws IOException {
		response.addHeader("refresh", "1;url='http://www.baidu.com'");
		response.getOutputStream().write("页面加载中...".getBytes());
	}
	
	/**
	 * 使用响应头content-disposition实现客户机用下载的方式打开数据资源  
	 *
	 * @author qincd
	 * @date Nov 10, 2014 11:24:00 AM
	 */
	@RequestMapping(value="/download")
	public void test5(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String filename = "中文文档.xls";
		filename = URLEncoder.encode(filename,"utf-8");
		response.addHeader("Content-dispostion", "attachment;filename=" + filename);
		response.setContentType("application/vnd.ms-excel");
		String file = request.getSession().getServletContext().getRealPath("/") + "/images/周大福定制组工作周报（2014-10-08至2014-10-11）.xls";
		OutputStream out = response.getOutputStream();
		InputStream in = new FileInputStream(file);
		byte[] data = new byte[1024];
		int len = -1;
		while ((len = in.read(data)) != -1) {
			out.write(data,0,len);
		}
		out.close();
		in.close();
	}
}
