package com.cloud.chain.netty.service;

import com.cloud.chain.netty.dto.GRequest;
import com.cloud.chain.netty.dto.GResponse;
import com.cloud.chain.tomcat.dto.BzRequest;
import com.cloud.chain.tomcat.dto.BzResponse;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:27
 * @mark MyTomcat
 */
public abstract class TmServlet {

    public void service(GRequest request, GResponse response) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doPost(GRequest request, GResponse response);

    public abstract void doGet(GRequest request, GResponse response);


}
