package com.cloud.photo;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/9/30 14:05
 * @mark PhotoController
 */
@Controller
@RequestMapping("photo")
public class PhotoController {

    @RequestMapping("photo")
    public void photo(HttpServletResponse response) throws IOException {
        // 预览
//        response.setContentType("application/octet-stream");
        // 下载
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setContentType("image/jpg;charset=utf-8");
        response.setContentType("application/x-jpg");
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(new FileInputStream("/Users/haizhuangbu/Desktop/1961660706877_.pic.jpg"),outputStream);
    }


    public static void main(String[] args) {

       String r =  "a-zA-Z|\\-";

        String s = "-a12".replaceAll(r, "");
        System.out.println("s = " + s);


    }


}
