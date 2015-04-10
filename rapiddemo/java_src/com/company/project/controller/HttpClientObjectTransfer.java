/**
 * 
 */
package com.company.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javacommon.base.BaseRestSpringController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.project.model.TradePack;
import com.company.project.util.EApiUtil;

/**
 * @author qince
 *
 */
@Controller()
@RequestMapping("/manage/interface")
public class HttpClientObjectTransfer extends BaseRestSpringController<TradePack, Long>{

	/**
	 * 输出对象
	 *
	 * @author 秦慈东
	 * @throws IOException 
	 * @date Oct 10, 2014 5:11:55 PM
	 */
	@RequestMapping("/test/{appId}")
	public String writeObject(@PathVariable String appId,HttpServletResponse response) throws IOException {
		System.out.println("appId:" + appId);
		TradePack tradePack = new TradePack();
		tradePack.setAppId(appId);
		tradePack.setMyFlowNo("M01");
		tradePack.setTradeNo("0324");
		
		String sign = EApiUtil.callInnerSign(tradePack);
		System.out.println("sign:" + sign);
		tradePack.setSign(sign);
		
		ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
		oos.writeObject(tradePack);
		oos.close();
		return null;
	}
	
	/**
	 * 通过commons-fileupload上传文件
	 *
	 * @author 秦慈东
	 * @date Oct 10, 2014 6:05:33 PM
	 */
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request,HttpServletResponse response) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4*1024); // 设置缓冲区大小
		File tmpDir = new File("d:/tmp");
		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}
		factory.setRepository(tmpDir);
		
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setSizeMax(10*1024*1024); // 设置最大文件尺寸
		
		try {
			List<FileItem> files = sfu.parseRequest(request);
			for (FileItem fi : files) {
				if (fi.isFormField()) { // 表单元素
					System.out.println(fi.getFieldName() + "=" + fi.getString());
				}
				else {
					String fileName = fi.getName();
					File storeDir = new File("d:/files/");
					if (!storeDir.exists()) {
						storeDir.mkdirs();
					}
					File saveFile = new File(storeDir,fileName);
					fi.write(saveFile);
					System.out.println("file [" + fileName + "] upload success.");
				}
			}
			response.getWriter().println("file upload successfully.");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
