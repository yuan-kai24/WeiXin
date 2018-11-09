package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.po.DownloadVoice;
import com.imooc.util.CheckUtil;
import com.imooc.util.MessageUtil;
import com.imooc.util.WeiXinUtil;

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
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
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
            String content = map.get("Content");//内容
            String msgType = map.get("MsgType");//类型
            // ----------------------------------------------------------------

            // ----------------------------------------------------------------

            String message = null;

            if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                switch (content) {
                    case "?":
                    case "？":
                        message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText("输入编号编号进行操作！"));
                        break;
                    case "1":
                        message = MessageUtil.initText(toUserName, fromUserName, "还真输。啥也没有");
                        break;
                    case "2":
                        message = MessageUtil.textImgeMessage(toUserName, fromUserName, "哈哈哈哈哈哈");
                        break;
                    case "3":
                        message = MessageUtil.initText(toUserName, fromUserName, "语音识别为：" + DownloadVoice.asr());
                        break;
                    default:
                        String con = "你好：" + fromUserName + "\r\n" + "你发送了：" + content;
                        message = MessageUtil.initText(toUserName, fromUserName, con);

                        break;
                }
            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                //区分事件推送
                String event = map.get("Event");
                //关注

                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(event)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText("感谢您的关注，请输入以下编号进行操作"));

                }
            }  else if(MessageUtil.MESSAGE_VOICE.equals(msgType)){
                byte[] voice = DownloadVoice.SendGET(map.get("MediaId"));
                StringBuffer asr = new StringBuffer(DownloadVoice.asr(voice));
//                System.out.println(map.get("Recognition")); // 微信自带
                asr.deleteCharAt(asr.length()-1);
                asr.append("。");
                message = MessageUtil.initText(toUserName, fromUserName, "语音消息：" + asr+"\r\n"+WeiXinUtil.getAccessToken().getToken());
            }else{
                message = MessageUtil.initText(toUserName, fromUserName, "发送类型：" + msgType);

            }
            out.print(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}

