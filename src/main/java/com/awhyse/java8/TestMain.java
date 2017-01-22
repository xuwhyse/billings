package com.awhyse.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by whyse on 2017/1/22.
 */
public class TestMain {

    static Logger logger = LoggerFactory.getLogger(TestMain.class);

    //========================================================
    public static void main(String[] args) {
        String filePath = "G:/";
        Path path = Paths.get(filePath,"testFile.txt");
        Thread.currentThread().setName("主线程");
        try {
            List<String> listStr = Files.readAllLines(path);//按行读取文件
            logger.info("hello logback");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
