package com.awhyse.java8;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * nio下的Path,和Files对io输入输出优化
 * Created by whyse on 2017/1/22.
 */
public class NewFileUtils {
    static Logger logger = LoggerFactory.getLogger(NewFileUtils.class);

    //========================================================
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/whyse/Desktop/work/任务文件夹/短信后台/";
        Path path = Paths.get(filePath,"testR.txt");

        List<String> listStr = Files.readAllLines(path);//按行读取文件

        String TAB = "\t";
        listStr.forEach(item->{
            String[] strs = item.split(TAB);
            System.err.print(12);
        });

        byte[] bytesFile = Files.readAllBytes(path);

        Files.write(path,listStr);//写入几行

        Files.write(path,bytesFile);//写入文件
//        Files.write(path,array, StandardOpenOption.APPEND);//追加

        Files.write(path,listStr, StandardOpenOption.APPEND);//追加
        Files.write(path,"\r\n".getBytes(), StandardOpenOption.APPEND);//换行

    }
}
