package com.company.project.model;

import java.util.ArrayList;
import java.util.List;

import com.company.project.model.resp.Articles;
import com.company.project.model.resp.NewsMessage;
import com.company.project.util.MessageUtil;
import com.company.project.util.UUIDUtil;

public class Test {

	/**
	 *
	 * @author qincd
	 * @date Nov 5, 2014 9:42:02 AM
	 */
	public static void main(String[] args) {
		NewsMessage nm = new NewsMessage();
		nm.setMsgId(UUIDUtil.generateLong());
		nm.setMsgType(MessageUtil.MESSAGG_TYPE_NEWS);
		nm.setCreateTime(System.currentTimeMillis());
		nm.setFromUserName("fromUser");
		nm.setToUserName("toUser");
		
		List<Articles> articles = new ArrayList<Articles>();
		Articles a = new Articles();
		nm.setArticles(articles);
		nm.setArticleCount(articles.size());
		
		System.out.println(MessageUtil.NewsMessageToXml(nm));
	}

}
