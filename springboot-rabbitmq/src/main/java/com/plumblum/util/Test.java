package com.plumblum.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenpeibin
 * @version 1.0
 * @date 2020/3/27 17:15
 */
@Data
public class Test extends AbstractObject {
    private String demo;

    private Map<String, Test> map;

    public static void main(String[] args) {
        HashMap<String, Test> hashMap = new HashMap<>();
        Test test = new Test();
        test.setDemo("12314");

        Test demo = new Test();
        demo.setDemo("demo");
        hashMap.put("demo", demo);
        test.setMap(hashMap);

        Test clone = test.clone(Test.class, 1);
        System.out.println(clone);

    }
}