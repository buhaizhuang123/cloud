package com.design.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/4/17 13:43
 * @mark Meta
 */
public class Meta {

    private static Map<String, AbsEnjoyMeta> absEnjoyMetaMap;

    static {
        absEnjoyMetaMap = new HashMap<>();
    }

    public static AbsEnjoyMeta getInstance(String key) {
        if (absEnjoyMetaMap.containsKey(key)) {
            return absEnjoyMetaMap.get(key);
        }
        absEnjoyMetaMap.put(key, new EnjoyMeta());
        return absEnjoyMetaMap.get(key);
    }

}

/**
 * 抽象享元
 */
interface AbsEnjoyMeta {

}

class EnjoyMeta implements AbsEnjoyMeta {

}

class MainMeta {
    public static void main(String[] args) {

        AbsEnjoyMeta aa = Meta.getInstance("aa");
        AbsEnjoyMeta bb = Meta.getInstance("aa");
        System.out.println(aa);
        System.out.println(bb);
    }
}


