package com.imooc.util;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.imooc.po.TextImgeMessage;
import com.imooc.po.TextImgeNews;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.imooc.po.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	// evet
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";

	/**
	 * xml转换为map集合
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest req) {
		Map<String, String> map = new HashMap<>();

		//
		SAXReader reader = new SAXReader();

		try {
			// 获取req的输入流
			ServletInputStream inputStream = req.getInputStream();
			Document read = reader.read(inputStream);

			// 获取xml的根元素
			Element rootElement = read.getRootElement();
			// 获取所有实体，放入list集合中
			
			List<Element> elements = rootElement.elements();

			// 将遍历的结果，保存到集合中
			for (Element el : elements) {
				map.put(el.getName(), el.getText());
			}
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 将文本转换为xml
	 *
	 * @return
	 */
	/*
	适用于文本回复等
	 */
	public static String textMessageToXml(Object obj) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", obj.getClass());
		xstream.alias("item", new TextImgeNews().getClass());
		return xstream.toXML(obj);
	}

	/*
	适用于图文消息等
	 */


	/*
	 * 主菜单
	 */
	public static String menuText() {
		StringBuilder str = new StringBuilder();
		str.append("谢谢关注！请选输入编号操作" + "\r\n");
		str.append("1,(⊙﹏⊙）(⊙﹏⊙)（⊙⊙）" + "\r\n");
		str.append("2,懒得写" + "\r\n");
		str.append("3,懒得写*2" + "\r\n");
		str.append("4,懒得写*3" + "\r\n");
		str.append("5,懒得写*4" + "\r\n");
		str.append("*穿越有理，逃跑无罪*"+ "\r\n");
		str.append("--------输入?调出此菜单--------");
		return str.toString();

	}

	// 初始化消息回复
	public static String initText(String toUserName,String fromUserName,String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);//自己的微信号
		text.setToUserName(fromUserName);//发送的用户
		text.setMsgType(MessageUtil.MESSAGE_TEXT);// 发送的类型
		text.setCreateTime(new Date().getTime());// 时间
		text.setContent(content);// 类容

		return MessageUtil.textMessageToXml(text);//转化为xml
	}

	public static String textImgeMessage(String toUserName,String fromUserName,String content){
		TextImgeMessage textImgeMessage = new TextImgeMessage();
		List<TextImgeNews> list = new LinkedList<>();

		TextImgeNews textImgeNews = new TextImgeNews();
		list.add(textImgeNews);

		textImgeMessage.setToUserName(fromUserName);
		textImgeMessage.setFromUserName(toUserName);
		textImgeMessage.setMsgType(MessageUtil.MESSAGE_NEWS);
		textImgeMessage.setArticles(list);
		textImgeMessage.setArticleCount(list.size());


		return MessageUtil.textMessageToXml(textImgeMessage);
	}

}
