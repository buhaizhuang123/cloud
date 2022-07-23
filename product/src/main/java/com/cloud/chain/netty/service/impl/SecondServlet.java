package com.cloud.chain.netty.service.impl;

import com.cloud.chain.netty.dto.GRequest;
import com.cloud.chain.netty.dto.GResponse;
import com.cloud.chain.netty.service.TmServlet;
import com.cloud.chain.tomcat.dto.BzRequest;
import com.cloud.chain.tomcat.dto.BzResponse;
import com.cloud.chain.tomcat.server.MyServlet;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:33
 * @mark SecondServlet
 */
public class SecondServlet extends TmServlet {
    @Override
    public void doPost(GRequest request, GResponse response) {
        response.writer("this is secondServlet");
    }

    @Override
    public void doGet(GRequest request, GResponse response) {
        this.doPost(request, response);
    }

}
