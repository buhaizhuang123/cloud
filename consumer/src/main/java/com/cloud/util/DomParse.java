package com.cloud.util;

import com.sun.istack.internal.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/9/10 16:42
 * @mark DomParse
 */
public class DomParse<T> {

    private T clazz;

    /**
     * @author haizhuang.bu
     * @date 16:50 2022/9/10
     * @function 获取dom元素
     */
    public static Document parse(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document parse = documentBuilder.parse(path);
            return parse;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getData(String path, @NotNull String domPath, T clazz) {
        Document parse = DomParse.parse(path);

        String[] doms = domPath.split("_");

        NodeList elementsByTagName = null;
        for (String dom : doms) {
            Element documentElement = parse.getDocumentElement();
            elementsByTagName = documentElement.getElementsByTagName(dom);
        }

        for (int i = 0; i < elementsByTagName.getLength(); i++) {

            Node item = elementsByTagName.item(i);
            String nodeValue = item.getNodeValue();
        }
        return clazz;

    }


}
