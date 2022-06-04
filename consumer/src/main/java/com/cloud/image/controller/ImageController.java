package com.cloud.image.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2022/6/4 17:48
 * @mark ImageController
 */
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private Producer producer;

    /**
     * 生成图片验证啊
     */
    @RequestMapping("generate")
    public Object captcha(HttpServletResponse response) throws IOException {
        String key = UUID.randomUUID().toString();
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

//        ServletOutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64";
        String base64Image = str + base64Encoder.encode(outputStream.toByteArray());
//        response.setContentType("application/jpeg");
        System.out.println(base64Image);
        return base64Image;
    }

}
