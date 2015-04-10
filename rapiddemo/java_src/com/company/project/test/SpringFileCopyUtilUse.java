package com.company.project.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Properties;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import com.company.project.model.TuserInfo;

public class SpringFileCopyUtilUse {

	/**
	 *
	 * @author qincd
	 * @date Nov 24, 2014 9:41:59 AM
	 */
	public static void main(String[] args) {
		try {
			FileCopyUtils.copy("hello world".getBytes(), new File("d:/test.txt"));
			Properties pros = PropertiesLoaderUtils.loadAllProperties("log4j.properties");
			System.out.println(pros.getProperty("log4j.appender.stdout"));
			String javascriptEscape = JavaScriptUtils.javaScriptEscape("<html><p>hello world</p></html>");
			System.out.println(javascriptEscape);
			String output = HtmlUtils.htmlEscape("<html><p>hello world</p></html><script>alert('haha');</script>");
			System.out.println(output);
			
			TuserInfo u = new TuserInfo(111L);
			Field f = ReflectionUtils.findField(TuserInfo.class, "userId");
			f.setAccessible(true);
			Object userid = f.get(u);
			System.out.println(userid);
		
			ReflectionUtils.doWithMethods(TuserInfo.class, new ReflectionUtils.MethodCallback(){
				@Override
				public void doWith(Method method)
						throws IllegalArgumentException, IllegalAccessException {
					
				}
			});
			
			// 注解使用示例
			
			//MyTestController mtc = new MyTestController();
			// 类级别的注解
			RequestMapping rm = AnnotationUtils.findAnnotation(MyTestController.class, RequestMapping.class);
			if (rm != null) {
				String[] values = rm.value();
				if (values != null && values.length > 0) {
					for (String value : values) {
						System.out.println(value);
					}
				}
			}
			
			// 方法级别的注解
			ReflectionUtils.doWithMethods(MyTestController.class, new ReflectionUtils.MethodCallback() {
				@Override
				public void doWith(Method method)
						throws IllegalArgumentException, IllegalAccessException {
					RequestMapping rm = AnnotationUtils.findAnnotation(method, RequestMapping.class);
					if (rm != null) {
						String[] values = rm.value();
						if (values != null && values.length > 0) {
							for (String value : values) {
								System.out.println(value);
							}
						}
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
