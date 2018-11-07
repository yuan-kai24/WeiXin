package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.util.CheckUtil;
import com.imooc.util.MessageUtil;

@WebServlet("/wx")
public class WeixinServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out = resp.getWriter();
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        if(CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(req);
            //微信号
            String fromUserName = map.get("FromUserName");//发送方
            String toUserName = map.get("ToUserName");//开发者
            //数据
            String content = map.get("Content");//类容
            String msgType = map.get("MsgType");//类型


            String message = null;

            if(MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                if("？".equals(content))
                {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else if("1".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName,"还真输。啥也没有");
                }else {
                    String con = "你好：" + fromUserName + "\r\n" + "你发送了："+content;
                    message = MessageUtil.initText(toUserName, fromUserName,con);
                }
            }else if(MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                //区分事件推送
                String event = map.get("Event");
                //关注

                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(event)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            }else {
                if(MessageUtil.MESSAGE_IMAGE.equals(msgType)){//图片
                    System.out.println("================================================================");
                    message = MessageUtil.textImgeMessage(toUserName,fromUserName,"哈哈哈哈哈哈");
                }else{
                    message = MessageUtil.initText(toUserName, fromUserName, "emmmmmmmm："+msgType);
                }

            }
            out.print(message);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }

}

