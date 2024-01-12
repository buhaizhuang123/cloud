package com.cloud.timer.common;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/5/31 14:29
 * @mark MyHandshakeInterceptor
 */
public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    // 握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        //System.out.println("++++++++++++++++ HandshakeInterceptor: beforeHandshake  ++++++++++++++" + attributes);
        if(request instanceof ServerHttpRequest){
            ServletServerHttpRequest servletRequest=(ServletServerHttpRequest) request;
            //获取httpSession，用于拿到登录时保存的用户名
            HttpSession session=servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                if (this.isCopyHttpSessionId()) {
                    String userName = "";
                    if(session.getAttribute("userName")!=null){
                        userName = session.getAttribute("userName").toString();
                    }
                    //attributes.put("HTTP.SESSION.ID",session.getId());  // 保存 sessionid
                    attributes.put("HTTP.SESSION.userName",userName);  // 保存 用户姓名
                	/*for(Object user:attributes.values()){
                		System.out.println(user);
                	}*/
                    //attributes.put("HTTP.SESSION.ID",session.getId());

                }
            }
        }
        //HttpServletRequest servletrequest = ServletActionContext.getRequest();
        //HttpSession session = servletrequest.getSession(true);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    // 握手后
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        //System.out.println("++++++++++++++++ HandshakeInterceptor: afterHandshake  ++++++++++++++");
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
