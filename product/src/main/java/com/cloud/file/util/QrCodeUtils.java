package com.cloud.file.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @author haizhuangbu
 * @date 2023/3/11 11:56
 * @mark QrCodeUtils 二维码生成器
 */
@Slf4j
public class QrCodeUtils {

    /**
     * @param content      二维码内容
     * @param outputStream 输出流
     */
    public static void createCodeToFile(String content, OutputStream outputStream) throws WriterException, IOException {
        if (StringUtils.isBlank(content)) {
            return;
        }


        content = content.trim();

        BufferedImage bufferImage = getBufferImage(content);

        ImageIO.write(bufferImage,"png",outputStream);
    }


    /**
     * @param content 内容
     * @return 二维码生成器
     */
    private static BufferedImage getBufferImage(String content) throws WriterException {
        HashMap<EncodeHintType, Object> hits = new HashMap<>();
        hits.put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置字符串编码类型
        hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        hits.put(EncodeHintType.MARGIN, 1);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hits);
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_BGR);
        for (int i = 0; i < 200; i++) {

            for (int j = 0; j < 200; j++) {
                bufferedImage.setRGB(i,j,bitMatrix.get(i,j)? 0x000000 : 0xFFFFFF);
            }
            
        }
        return bufferedImage;
    }


}
