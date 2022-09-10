package com.cloud.util;

import com.cloud.sys.dto.Person;
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
import java.lang.reflect.Field;

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
            elementsByTagName = documentElement.getChildNodes();
        }


        Class<?> aClass = clazz.getClass();
        Field[] fields = aClass.getDeclaredFields();
        // 获取对象实例
        Object o = null;
        try {
            o = aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        for (int i = 1; i < elementsByTagName.getLength(); i++) {

            Node item = elementsByTagName.item(i);
            for (Field field : fields) {
                String name = field.getName();
                if (item.getNodeName().equals(name)) {
                    try {
                        field.setAccessible(true);
                        // 属性赋值
                        field.set(o, item.getTextContent());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
//
//            for (int j = 0; j < childNodes.getLength(); j++) {
//                Node node = childNodes.item(j);
//                String nodeValue = node.getNodeValue();
//                System.out.println("node.getOwnerDocument().getDocumentElement().getTagName() = " + node.getOwnerDocument().getDocumentElement().getTagName());
//
//            }

        }
        return (T) o;
    }

    public static void main(String[] args) {

        DomParse<Person> personDomParse = new DomParse<>();

        String path = DomParse.class.getResource("/").getPath();
        Person person = personDomParse.getData(path + "Person.xml", "person", new Person());

        System.out.println(person);

    }

}
