package com.cloud.chain.tomcat.server;

import com.cloud.chain.tomcat.dto.BzRequest;
import com.cloud.chain.tomcat.dto.BzResponse;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:27
 * @mark MyTomcat
 */
public abstract class MyServlet {

    public void service(BzRequest request, BzResponse response) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doPost(BzRequest request, BzResponse response);

    public abstract void doGet(BzRequest request, BzResponse response);


}
