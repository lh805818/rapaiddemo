package com.company.project.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.experimental.theories.suppliers.TestedOnSupplier;
import org.springframework.stereotype.Service;

import com.company.project.model.resp.Articles;
import com.company.project.model.resp.Music;
import com.company.project.model.resp.MusicMessage;
import com.company.project.model.resp.NewsMessage;
import com.company.project.model.resp.TextMessage;
import com.company.project.util.MessageUtil;
import sun.plugin2.message.Message;

@Service
public class WeixinService {
	public static Logger log = Logger.getLogger(WeixinService.class);

	public String processRequest(HttpServletRequest req) {
		// 解析微信传递的参数
		String str = null;
		try {
			Map<String,String> xmlMap = MessageUtil.parseXml(req);
			str = "请求处理异常，请稍后再试！";
			
			String ToUserName = xmlMap.get("ToUserName");
			String FromUserName = xmlMap.get("FromUserName");
			String MsgType = xmlMap.get("MsgType");
			
			if (MsgType.equals(MessageUtil.MESSAGG_TYPE_TEXT)) {
				// 用户发送的文本消息
				String content = xmlMap.get("Content");
				log.info("用户：[" + FromUserName + "]发送的文本消息：" + content);
				
				// 链接
				if ("1".equals(content)) {
					TextMessage tm = new TextMessage();
					tm.setToUserName(FromUserName);
					tm.setFromUserName(ToUserName);
					tm.setMsgType(MessageUtil.MESSAGG_TYPE_TEXT);
					tm.setCreateTime(System.currentTimeMillis());
					tm.setContent("我的CSDN博客：<a href=\"http://my.csdn.net/qincidong\">CSDN博客</a>\n github博客：<a href=\"http://qincidong.github.io\">github blog</a>");
					return MessageUtil.textMessageToXml(tm);
				}
				else if ("2".equals(content)) { // 图文回复
					NewsMessage nm = new NewsMessage();
					nm.setFromUserName(ToUserName);
					nm.setToUserName(FromUserName);
					nm.setCreateTime(System.currentTimeMillis());
					nm.setMsgType(MessageUtil.MESSAGG_TYPE_NEWS);
					List<Articles> articles = new ArrayList<Articles>();
					Articles e1 = new Articles();
					e1.setTitle("马云接受外媒专访：中国的五大银行想杀了“我”");
					e1.setDescription("阿里巴巴集团上市大获成功，《华尔街日报》日前就阿里巴巴集团、支付宝等话题采访了马云，马云也谈到了与苹果Apple Pay建立电子支付联盟的可能性。本文摘编自《华尔街日报》，原文标题：马云谈阿里巴巴将如何帮助美国出口商，虎嗅略有删节。");
					e1.setPicUrl("http://img1.gtimg.com/finance/pics/hv1/29/53/1739/113092019.jpg");
					e1.setUrl("http://finance.qq.com/a/20141105/010616.htm?pgv_ref=aio2012&ptlang=2052");
					
					Articles e2 = new Articles();
					e2.setTitle("史上最牛登机牌：姓名竟是微博名 涉事航空公司公开致歉");
					e2.setDescription("世上最遥远的距离是飞机在等你登机，你却过不了安检。");
					e2.setPicUrl("http://p9.qhimg.com/dmfd/328_164_100/t011946ff676981792d.png");
					e2.setUrl("http://www.techweb.com.cn/column/2014-11-05/2093128.shtml");
					articles.add(e1);
					articles.add(e2);
					
					nm.setArticles(articles);
					nm.setArticleCount(articles.size());
					
					String newsXml = MessageUtil.NewsMessageToXml(nm);
					log.info("\n"+newsXml);
					return newsXml;
				}
				else if ("3".equals(content)) { // 回复音乐
					MusicMessage mm =  new MusicMessage();
					mm.setFromUserName(ToUserName);
					mm.setToUserName(FromUserName);
					mm.setMsgType(MessageUtil.MESSAGG_TYPE_MUSIC);
					mm.setCreateTime(System.currentTimeMillis());
					Music music = new Music();
					music.setTitle("Maid with the Flaxen Hair");
					music.setDescription("测试音乐");
					music.setMusicUrl("http://yinyueshiting.baidu.com/data2/music/123297915/1201250291415073661128.mp3?xcode=e2edf18bbe9e452655284217cdb920a7a6a03c85c06f4409");
					music.setHQMusicUrl("http://yinyueshiting.baidu.com/data2/music/123297915/1201250291415073661128.mp3?xcode=e2edf18bbe9e452655284217cdb920a7a6a03c85c06f4409");
					mm.setMusic(music);
					
					String musicXml = MessageUtil.MusicMessageToXml(mm);
					log.info("musicXml:\n" + musicXml);
					return musicXml;
				}
				else if ("4".equals(content)) { // 表情发送
					content = "/微笑/撇嘴/色/发呆/得意 公共汽车\ue159";
					TextMessage textMessage = createTextMessage(content,System.currentTimeMillis(),ToUserName,FromUserName);
					String textXml = MessageUtil.textMessageToXml(textMessage);
					log.info("textXml:" + textXml);
					return textXml;
				}
				else if (content.equals("5")) { // 历史上的今天
					String history = TodayInHistoryService.getContent();
					String today = "==历史上的" + TodayInHistoryService.getToday() + "==\n";
					history = today + history;
					TextMessage textMessage = createTextMessage(history,System.currentTimeMillis(),ToUserName,FromUserName);
					return MessageUtil.textMessageToXml(textMessage);
				}
				else if (content.equals("6") || content.startsWith("歌曲")) { // 歌曲点播
					// 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉
					content = content.replaceAll("^歌曲[\\+ ~!@#%^-_=]?","");
					if ("6".equals(content) || "".equals(content)) {
						TextMessage textMessage = createTextMessage(MusicSearchService.getUsage().toString(),System.currentTimeMillis(),ToUserName,FromUserName);
						return MessageUtil.textMessageToXml(textMessage);
					}
					else {
						String[] keywords = content.split("@");
						String title = keywords[0];
						String author = null;
						if (keywords.length == 2) {
							author = keywords[1];
						}
						Music music = new Music();
						// 由于搜索歌曲的时间超过2.5S，微信直接提示当前公众号无法提供服务。
						// 如果有客服接口，可以先回复一条空文本，然后客服推消息给用户。
//						new MusicSearchThread(music,author,title).start();
						music = MusicSearchService.searchMusicByBaidu(author,title);
						if (null == music) {
							TextMessage textMessage = createTextMessage("很抱歉，未搜索到歌曲【"+title+"】",System.currentTimeMillis(),ToUserName,FromUserName);
							return MessageUtil.textMessageToXml(textMessage);
						}
						MusicMessage musicMessage = new MusicMessage();
						musicMessage.setMsgType(MessageUtil.MESSAGG_TYPE_MUSIC);
						musicMessage.setFromUserName(ToUserName);
						musicMessage.setToUserName(FromUserName);
						musicMessage.setCreateTime(System.currentTimeMillis());
						musicMessage.setMusic(music);
						String musicXml = MessageUtil.MusicMessageToXml(musicMessage);
						log.info(musicXml);
						return musicXml;
					}
				}
				else if ("7".equals(content) || content.endsWith("天气")) { // 天气查询
					if ("7".equals(content)) {
						TextMessage textMessage = createTextMessage(WeatherSearchService.getUsage(),System.currentTimeMillis(),ToUserName,FromUserName);
						return MessageUtil.textMessageToXml(textMessage);
					}
					else if (!"天气".equals(content)) {
						// 获取城市名称
						String cityName = content.substring(0,content.lastIndexOf("天气"));
						String wealtherinfo = WeatherSearchService.getWeatherByCityName(cityName);
						TextMessage textMessage = null;
						if (StringUtils.isEmpty(wealtherinfo)) {
							textMessage = createTextMessage("抱歉，未查询到【"+cityName + "】的天气信息",System.currentTimeMillis(), ToUserName, FromUserName);
						}
						else {
							textMessage = createTextMessage(wealtherinfo,System.currentTimeMillis(), ToUserName, FromUserName);
						}
						return MessageUtil.textMessageToXml(textMessage);
					}
				}
				else if ("8".equals(content) || content.startsWith("翻译")) { // 语言翻译
					TextMessage textMessage = null;
					if ("8".equals(content) || "翻译".equals(content)) {
						textMessage = createTextMessage(YouDaoTranslateService.getUsage(),System.currentTimeMillis(),ToUserName, FromUserName);
					}
					else {
						// 截取要翻译的文本
						String txt = content.substring(content.indexOf("翻译")+2);
						String text = YouDaoTranslateService.translate(txt);
						textMessage = createTextMessage(text,System.currentTimeMillis(),ToUserName, FromUserName);
					}

					return MessageUtil.textMessageToXml(textMessage);
				}
				else if ("9".equals(content) || content.startsWith("聊天")) { // 智能聊天机器人
					TextMessage textMessage = null;
					textMessage = createTextMessage("直接输入内容即可。",System.currentTimeMillis(),ToUserName, FromUserName);
					return MessageUtil.textMessageToXml(textMessage);
				}
				else if (content.equalsIgnoreCase("help") || content.equals("？") || content.equals("?")) {
					content = "目前包括的测试功能：\n";
					content += "1  链接\n";
					content += "2  图文\n";
					content += "3  音乐\n";
					content += "4  QQ表情\n";
					content += "5  历史上的今天\n";
					content += "6  歌曲点播\n";
					content += "7  天气查询\n";
					content += "8  语言翻译\n";
					content += "9  聊天机器人\n";
					TextMessage textMessage = createTextMessage(content,System.currentTimeMillis(),ToUserName,FromUserName);
					return MessageUtil.textMessageToXml(textMessage);
				}

				
				// 响应
				// 默认不是功能选项中的功能时，进入智能机器人聊天。
				String reply = SmartRobotChatService.chat(content);
				TextMessage textMessage = createTextMessage(reply,System.currentTimeMillis(),ToUserName,FromUserName);
				return MessageUtil.textMessageToXml(textMessage);
			}
			else if (MsgType.equals(MessageUtil.MESSAGG_TYPE_EVENT)) {
				String event = xmlMap.get("Event");
				if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// 订阅
					TextMessage tm = new TextMessage();
					tm.setToUserName(FromUserName);
					tm.setFromUserName(ToUserName);
					tm.setMsgType(MessageUtil.MESSAGG_TYPE_TEXT);
					tm.setCreateTime(System.currentTimeMillis());
					tm.setContent("你好，欢迎关注[程序员的生活]公众号！[愉快]/呲牙/玫瑰\n目前可以回复文本消息");
					return MessageUtil.textMessageToXml(tm);
				}
				else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅
					log.info("用户【" + FromUserName + "]取消关注了。");
				}
				else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = xmlMap.get("EventKey");
					if (eventKey.equals("reply_words")) { // 点击了回复文字菜单
						TextMessage tm = new TextMessage();
						tm.setToUserName(FromUserName);
						tm.setFromUserName(ToUserName);
						tm.setMsgType(MessageUtil.MESSAGG_TYPE_TEXT);
						tm.setCreateTime(System.currentTimeMillis());
						tm.setContent("你好，你点击了回复文本菜单：\n" );
						
						String xml = MessageUtil.textMessageToXml(tm);
						log.info("xml:" + xml);
						return xml;
					}
					else if (eventKey.equals("reply_music")) {
						MusicMessage mm =  new MusicMessage();
						mm.setFromUserName(ToUserName);
						mm.setToUserName(FromUserName);
						mm.setMsgType(MessageUtil.MESSAGG_TYPE_MUSIC);
						mm.setCreateTime(System.currentTimeMillis());
						Music music = new Music();
						music.setTitle("Maid with the Flaxen Hair");
						music.setDescription("测试音乐");
						music.setMusicUrl("http://yinyueshiting.baidu.com/data2/music/123297915/1201250291415073661128.mp3?xcode=e2edf18bbe9e452655284217cdb920a7a6a03c85c06f4409");
						music.setHQMusicUrl("http://yinyueshiting.baidu.com/data2/music/123297915/1201250291415073661128.mp3?xcode=e2edf18bbe9e452655284217cdb920a7a6a03c85c06f4409");
						mm.setMusic(music);
						
						String musicXml = MessageUtil.MusicMessageToXml(mm);
						log.info("musicXml:\n" + musicXml);
						return musicXml;
					}
					else if (eventKey.equals("reply_news")) {
						NewsMessage nm = new NewsMessage();
						nm.setFromUserName(ToUserName);
						nm.setToUserName(FromUserName);
						nm.setCreateTime(System.currentTimeMillis());
						nm.setMsgType(MessageUtil.MESSAGG_TYPE_NEWS);
						List<Articles> articles = new ArrayList<Articles>();
						Articles e1 = new Articles();
						e1.setTitle("马云接受外媒专访：中国的五大银行想杀了“我”");
						e1.setDescription("阿里巴巴集团上市大获成功，《华尔街日报》日前就阿里巴巴集团、支付宝等话题采访了马云，马云也谈到了与苹果Apple Pay建立电子支付联盟的可能性。本文摘编自《华尔街日报》，原文标题：马云谈阿里巴巴将如何帮助美国出口商，虎嗅略有删节。");
						e1.setPicUrl("http://img1.gtimg.com/finance/pics/hv1/29/53/1739/113092019.jpg");
						e1.setUrl("http://finance.qq.com/a/20141105/010616.htm?pgv_ref=aio2012&ptlang=2052");
						
						Articles e2 = new Articles();
						e2.setTitle("史上最牛登机牌：姓名竟是微博名 涉事航空公司公开致歉");
						e2.setDescription("世上最遥远的距离是飞机在等你登机，你却过不了安检。");
						e2.setPicUrl("http://p9.qhimg.com/dmfd/328_164_100/t011946ff676981792d.png");
						e2.setUrl("http://www.techweb.com.cn/column/2014-11-05/2093128.shtml");
						articles.add(e1);
						articles.add(e2);
						
						nm.setArticles(articles);
						nm.setArticleCount(articles.size());
						
						String newsXml = MessageUtil.NewsMessageToXml(nm);
						log.info("\n"+newsXml);
						return newsXml;				
					}
					else if (eventKey.equals("reply_link")) {
						TextMessage tm = new TextMessage();
						tm.setToUserName(FromUserName);
						tm.setFromUserName(ToUserName);
						tm.setMsgType(MessageUtil.MESSAGG_TYPE_TEXT);
						tm.setCreateTime(System.currentTimeMillis());
						tm.setContent("我的CSDN博客：<a href=\"http://my.csdn.net/qincidong\">我的CSDN博客</a>\n");
						return MessageUtil.textMessageToXml(tm);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("处理微信请求时发生异常：");
		}
		
		return str;
	}

	private TextMessage createTextMessage(String content,Long createTime,String fromUserName,String toUserName) {
		TextMessage textMessage = new TextMessage();
		textMessage.setContent(content);
		textMessage.setCreateTime(createTime);
		textMessage.setFromUserName(fromUserName);
		textMessage.setToUserName(toUserName);
		textMessage.setMsgType(MessageUtil.MESSAGG_TYPE_TEXT);

		return textMessage;
	}

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	/**
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
	 * @param createTime
	 * @return
	 */
	public static String formatTime(Long createTime) {
		// 微信消息中的createTime是距离1970年的秒数，所以这里乘以1000，得到毫秒数
		Date date = new Date(createTime*1000);
		return dateFormat.format(date);
	}
}
