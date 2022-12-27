package com.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/11/28 15:57
 * @mark Pdfutils
 */
public class Pdfutils {



    /**
     * 转换全部的pdf
     * @param fileAddress 文件地址
     * @param filename PDF文件名
     * @param type 图片类型
     */
    public static void pdf2png(String fileAddress,String filename,String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(fileAddress+filename+".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI

                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, type, new File(fileAddress+"\\"+filename+"_"+(i+1)+"."+type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *自由确定起始页和终止页
     * @param fileAddress 文件地址
     * @param filename pdf文件名
     * @param indexOfStart 开始页  开始转换的页码，从0开始
     * @param type 图片类型
     */
    public static void pdf2png(String fileAddress,String filename,int indexOfStart,int indexOfEnd,String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(fileAddress+"\\"+filename+".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = indexOfStart; i < indexOfEnd; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, type, new File(fileAddress+"\\"+filename+"_"+(i+1)+"."+type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Pdfutils.pdf2png(args[0],args[1],"png");
    }

}
