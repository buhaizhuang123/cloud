package com.cloud.image.controller;

import com.cloud.person.dao.PersonMapper;
import com.cloud.person.dto.Person;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private Logger log = LoggerFactory.getLogger(ImageController.class);

    /**
     * 生成图片验证啊
     */
    @RequestMapping("generate")
    public Object captcha(HttpServletResponse response) throws IOException {
        String key = UUID.randomUUID().toString();
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        ValueOperations<String, String> redis = stringRedisTemplate.opsForValue();
        redis.set(key, text, 20, TimeUnit.SECONDS);
        log.info("将验证码插入redis");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Image = str + base64Encoder.encode(outputStream.toByteArray());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("value", base64Image);
        return map;
    }


    @RequestMapping("show")
    public void show(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=" +new String("fileName".getBytes("utf-8"),"iso-8859-1")+ ".pdf");
        ServletOutputStream writer = response.getOutputStream();
        String fileName = "/Applications/tools/wd/后端书籍/JVM/深入理解Java虚拟机JVM高级特性与最佳实践.pdf";
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        byte[] bytes = new byte[10240];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) > 0) {
            writer.write(bytes, 0, len);
        }
        fileInputStream.close();
        writer.close();
    }

}
