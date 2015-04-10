package com.company.project.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javacommon.base.BaseRestSpringController;

@Controller
@RequestMapping(value="/ajaxFileUpload")
public class AjaxFileUploadController extends BaseRestSpringController<Object, Object>{

	@RequestMapping(value="/upload")
	@ResponseBody
	public Map<String, String> uplaod(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("error", "");
		map.put("msg", "上传成功！");
		
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 设置缓存大小，如果文件大小超过缓存大小，将使用临时目录作为缓存
		dfif.setSizeThreshold(1024*1024);
		// 保存到项目决定位置下的upload文件夹，比如：d:/tomcat/webapps/rapiddemo/upload/xxxxxxxxx.jpg
		String webRoot = request.getSession().getServletContext().getRealPath("/") + "upload/";
		// 项目相对位置（用于在页面显示），比如：/rapiddemo/upload/xxxxxxxxx.jpg
		String webRootRelative = request.getContextPath() + "/upload/";
		File uploadDir = new File(webRoot);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		String ts = null;
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			for (int i=0;i<fileItemList.size();i++) {
				FileItem fi = fileItemList.get(i);
				if (fi.isFormField()) {
					String filedName = fi.getFieldName();
					String filedValue = fi.getString();
					System.out.println(filedName + ":" + filedValue);
					
					if (filedName.equals("ts")) {
						ts = filedValue;
					}
				}
				else {
					// 文件
					String fileNameFullPath = fi.getName();
					// 取文件后缀
					String fileExt = fileNameFullPath.substring(fileNameFullPath.lastIndexOf("."));
					// 随机文件名：yyyymmddhhmiss+3位随机数
					String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + new Random().nextInt(1000) + fileExt;
					fi.write(new File(webRoot + fileName));
					System.out.println("文件【" + fileNameFullPath + "】上传成功！\n路径：" + (webRoot + fileName));
					map.put("savePath",(webRootRelative + fileName));
					System.out.println("文件上传时间：" + ts);
				}
			}
		} catch (FileUploadException e) {
			map.put("error", "上传失败：" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			map.put("error", "上传失败：" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
}
