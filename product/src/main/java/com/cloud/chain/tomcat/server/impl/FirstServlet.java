package com.cloud.chain.tomcat.server.impl;

import com.cloud.chain.tomcat.dto.BzRequest;
import com.cloud.chain.tomcat.dto.BzResponse;
import com.cloud.chain.tomcat.server.MyServlet;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:31
 * @mark FirstServlet
 */
public class FirstServlet extends MyServlet {
    @Override
    public void doPost(BzRequest request, BzResponse response) {
        response.writer("this is firstServlet");
    }

    @Override
    public void doGet(BzRequest request, BzResponse response) {
        this.doPost(request, response);
    }
}
