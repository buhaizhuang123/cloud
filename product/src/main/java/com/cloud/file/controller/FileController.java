package com.cloud.file.controller;

import com.cloud.file.util.QrCodeUtils;
import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2023/3/11 11:54
 * @mark FileController
 */
@RestController
@RequestMapping("file")
public class FileController {

    /**
     * 二维码展示
     */
    @RequestMapping("show")
    public void show(HttpServletResponse response) throws IOException, WriterException {
        ServletOutputStream outputStream = response.getOutputStream();
        QrCodeUtils.createCodeToFile("李佳君是个大傻逼！！！",outputStream);
    }

}
