package com.bu.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/10/8 15:08
 * @mark QRCodeUtil
 */
public class QRCodeUtil {

    /**
     *
     * @param response
     * @param codeUrl 需要转二维码的URL
     */
    public static void createQrCode(HttpServletResponse response, String codeUrl) {
        try {
            //生成二维码配置
            Map<EncodeHintType,Object> hints = new HashMap<>();
            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //设置编码类型
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            //构造图片对象
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
            //输出流
            OutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
