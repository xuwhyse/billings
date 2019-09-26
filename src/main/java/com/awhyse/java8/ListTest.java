package com.awhyse.java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xumin
 * @description
 * @date 2018/10/19 上午10:16
 */
public class ListTest {
    public static void main(String[] args) {
        String[] arrays = new String[]{"a", "b", "c"};
        List<String> listStrings = Stream.of(arrays).collect(Collectors.toList());


        String[] ss = listStrings.stream().toArray(String[]::new);
    }
}
