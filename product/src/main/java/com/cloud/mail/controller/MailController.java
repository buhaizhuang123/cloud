package com.cloud.mail.controller;

import org.elasticsearch.threadpool.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author haizhuangbu
 * @date 2024/3/1 10:13
 * @mark MailController
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("send")
    public String sendMsg() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("buhaizhuang123@163.com");
        mimeMessageHelper.setSubject("大优惠");
        mimeMessageHelper.setText("特大优惠、测试");
        mimeMessageHelper.setTo("buhaizhuang123@163.com");
        mailSender.send(mimeMessage);

        return "发送成功";
    }

    public static void main(String[] args) {

        Runnable runnable = () -> {
            for (int j = 0; j < 100; j++) {
                AddCount.add();
            }
        };

        System.out.println("预想值 " + 1000);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable);

            thread.start();

        }
        System.out.println("实际值 " + AddCount.get());


    }


}
