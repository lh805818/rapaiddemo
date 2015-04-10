package com.company.project.util;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import com.company.project.model.menu.AccessToken;
import com.company.project.model.menu.CommandButton;
import com.company.project.model.menu.ComplexButton;
import com.company.project.model.menu.Menu;
import com.company.project.model.menu.ViewButton;

public class WeixinUtilTest {

	/**
	 *
	 * @author qincd
	 * @date Nov 6, 2014 9:57:54 AM
	 */
	public static void main(String[] args) {
		// 1).获取access_token
		AccessToken accessToken = WeixinUtil.getAccessToken(WeixinUtil.APPID, WeixinUtil.APP_SECRET);
		// 2).创建菜单
		testCreateMenu(accessToken);
		String filePath = "C:\\Users\\qince\\Pictures\\壁纸20141029091612.jpg";
		JSONObject uploadJsonObj = testUploadMedia(filePath,"image",accessToken.getToken());
		if (uploadJsonObj == null) {
			System.out.println("上传图片失败");
			return;
		}
		
		int errcode = 0;
		if (uploadJsonObj.containsKey("errcode")) {
			errcode = uploadJsonObj.getInt("errcode");
		}
		if (errcode == 0) {
			System.out.println("图片上传成功");
			
			String mediaId = uploadJsonObj.getString("media_id");
			long createAt = uploadJsonObj.getLong("created_at");
			System.out.println("--Details:");
			System.out.println("media_id:" + mediaId);
			System.out.println("created_at:" + createAt);
		}
		else {
			System.out.println("图片上传失败！");
			
			String errmsg = uploadJsonObj.getString("errmsg");
			System.out.println("--Details:");
			System.out.println("errcode:" + errcode);
			System.out.println("errmsg:" + errmsg);
		}
		
		String mediaId = "6W-UvSrQ2hkdSdVQJJXShwtFDPLfbGI1qnbNFy8weZyb9Jac2xxxcAUwt8p4sYPH";
		String filepath = testDownloadMedia(accessToken.getToken(), mediaId, "d:/test");
		System.out.println(filepath);
	}

	/**
	 * 创建菜单
	 *
	 * @author qincd
	 * @date Nov 6, 2014 4:14:10 PM
	 */
	public static void testCreateMenu(AccessToken accessToken) {
		Menu menu = new Menu();
		
		// 菜单1
		ComplexButton cb0 = new ComplexButton();
		cb0.setName("超值预定");
		
		ViewButton cb01 = new ViewButton();
		cb01.setName("团购订单");
		cb01.setType("view");
		cb01.setUrl("http://www.meituan.com");
		
		ViewButton cb02 = new ViewButton();
		cb02.setName("微信团购");
		cb02.setType("view");
		cb02.setUrl("http://www.weixin.com");
		
		cb0.setSub_button(new ViewButton[]{cb01,cb02});
		
		// 菜单2
		ComplexButton cb1 = new ComplexButton();
		cb1.setName("我的服务");
		
		ViewButton cb11 = new ViewButton();
		cb11.setName("办登机牌");
		cb11.setType("view");
		cb11.setUrl("http://qincdtest.ematong.com/rapiddemo/pages/wxtest.jsp");
		
		ViewButton cb12 = new ViewButton();
		cb12.setName("航班动态");
		cb12.setType("view");
		cb12.setUrl("http://www.meituan.com");
		
		ViewButton cb13 = new ViewButton();
		cb13.setName("里程查询");
		cb13.setType("view");
		cb13.setUrl("http://www.meituan.com");
		
		cb1.setSub_button(new ViewButton[]{cb11,cb12,cb13});
		
		// 菜单3
		ComplexButton cb2 = new ComplexButton();
		cb2.setName("我的测试");
		
		CommandButton cb21 = new CommandButton();
		cb21.setName("回复文字");
		cb21.setType("click");
		cb21.setKey("reply_words");
		
		CommandButton cb22 = new CommandButton();
		cb22.setName("回复音乐");
		cb22.setType("click");
		cb22.setKey("reply_music");
		
		CommandButton cb23 = new CommandButton();
		cb23.setName("回复图文");
		cb23.setType("click");
		cb23.setKey("reply_news");
		
		CommandButton cb24 = new CommandButton();
		cb24.setName("回复链接");
		cb24.setType("click");
		cb24.setKey("reply_link");
		
		cb2.setSub_button(new CommandButton[]{cb21,cb22,cb23,cb24});
		
		menu.setButton(new ComplexButton[]{cb0,cb1,cb2});
		String menuJsonString = JSONObject.fromObject(menu).toString();
		System.out.println(menuJsonString);
		WeixinUtil.createMenu(menu, accessToken.getToken());
	}

	/**
	 * 上传多媒体文件到微信
	 *
	 * @author qincd
	 * @date Nov 6, 2014 4:15:14 PM
	 */
	public static JSONObject testUploadMedia(String filePath,String type,String accessToken) {
		try {
			return WeixinUtil.uploadMediaToWX(filePath, type, accessToken);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 从微信下载多媒体文件
	 *
	 * @author qincd
	 * @date Nov 6, 2014 4:56:25 PM
	 */
	public static String testDownloadMedia(String accessToken,String mediaId,String fileSaveDir) {
		try {
			return WeixinUtil.downloadMediaFromWx(accessToken, mediaId, fileSaveDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
