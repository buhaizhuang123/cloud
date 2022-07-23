package com.cloud.chain.tomcat.server.ts;

import com.cloud.chain.tomcat.server.main.MyTomcat;
import org.junit.jupiter.api.Test;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:58
 * @mark TomcatTs
 */
public class TomcatTs {

    @Test
    public void start() {
        new MyTomcat().start();


    }


}
