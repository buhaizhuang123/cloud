package com.cloud.sne.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author haizhuangbu
 * @date 2022/12/27 16:52
 * @mark DownLoadController
 */
@RestController
@RequestMapping("/file")
public class DownLoadController {

    /**
     * 文件下载接口
     */
    @RequestMapping(value = "/downLoad",method = RequestMethod.GET)
    public void downLoad(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/x-msdownload"); // 下载
        response.setContentType("image/jpg"); // 预览
        String fileName = "/Users/haizhuangbu/Desktop/布海壮_140121199703203613.jpg";
        // 下载
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
        // 预览
        response.setHeader("Content-Disposition", "inline;filename=" + new String(fileName.getBytes()));
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            byte[] bytes = new byte[fileInputStream.available()];
            int len = 0;
            ServletOutputStream outputStream = response.getOutputStream();

            while ((len = fileInputStream.read(bytes) ) > 0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
