package com.cloud.image.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

/**
 * @author haizhuangbu
 * @date 2022/6/26 08:22
 * @mark FileUpload
 */
@RequestMapping("file")
@Controller
public class FileUpload {

    @RequestMapping("preview")
    public void preview(@RequestParam("fileUrl") String fileUrl, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        IOUtils.copy(new FileInputStream(fileUrl), response.getOutputStream());
    }


}
