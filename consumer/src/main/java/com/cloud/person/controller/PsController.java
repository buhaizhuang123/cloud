package com.cloud.person.controller;

import com.cloud.common.Page;
import com.cloud.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.concurrent.DelegatingSecurityContextScheduledExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author haizhuangbu
 * @date 2022/6/8 10:11
 * @mark PsController
 */
@RequestMapping("ps")
@RestController
@Async
public class PsController {

    @Autowired
    private ProductService productService;

    @RequestMapping("list")
    public Object psList(Page page) {
        return productService.listPs(page);
    }

    @RequestMapping("/getContext")
    @Async
    public String getContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        async();
        return "name : " + name + " password : " + authentication.getCredentials();
    }

    public void async() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new DelegatingSecurityContextRunnable(() -> {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            String name = authentication.getName();
            System.out.println(name);
        }));

    }


}
