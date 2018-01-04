package com.awhyse.concurrent.netty.stepfast.openfast;

import com.awhyse.concurrent.netty.stepfast.common.Template4001;
import org.openfast.Context;
import org.openfast.Message;
import org.openfast.codec.FastDecoder;
import org.openfast.template.loader.XMLMessageTemplateLoader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by whyse
 * on 2017/12/13 17:49
 */
public class FastCSDecoder {
    static File fiel = new File("e:/mydata");
    public static Path path = Paths.get("e:/mydata");//e:/data.step;
    /**
     * 每个线程一个context,因为要reset
     */
    static ThreadLocal<Context> tdLContext = new ThreadLocal<>();
    protected static InputStream resource(String url) {
        return FastCSDecoder.class.getClassLoader().getResourceAsStream(url);
    }
    //============================================================================

    public static void main(String[] args) {
        try {
            fiel.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param value   需要反序列化的数据
     * @param mapTar  {9=1381, 49=EzEI.1.1, 56=EzSR.2010600, 34=472185, 52=20171214-14:13:57.392,
     *                347=GBK, 167=01, 339=3, 1180=7840, 1181=359488, 75=20171214,
                        779=14135703, 265=001, 5468=10}
     */
    public static void fastDecode(byte[] value, Map<Integer, Object> mapTar) {
        try {
            Context context = tdLContext.get();
            if(context==null){
                context = getContext();
                tdLContext.set(context);
            }
            context.reset();
            InputStream in = new ByteArrayInputStream(value);
            FastDecoder decoder = new FastDecoder(context, in);
            int length = Integer.parseInt(mapTar.get(5468).toString());
            List<Template4001> listTar = new ArrayList<>(length);
            while(in.available()!=0){
                //可以读多个
                Message message = decoder.readMessage();
                Template4001 template4001 = Template4001.getByMessage(message);
                listTar.add(template4001);
//                System.err.println(message);
            }
            mapTar.put(96,listTar);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static Context getContext() {
        XMLMessageTemplateLoader loader = new XMLMessageTemplateLoader();
        Context context = new Context();
        loader.setLoadTemplateIdFromAuxId(true);
        loader.load(resource("templates/template4001.xml"));//SH
//        loader.load(resource("templates/template4003.xml"));//SH
//        loader.load(resource("templates/template4010.xml"));//SH
        context.setTemplateRegistry(loader.getTemplateRegistry());
        return context;
    }

    public static void writeLocal(byte[] array) {
        if(fiel.canWrite()){
            try {
                Files.write(path,array, StandardOpenOption.APPEND);//写入文件
//                Files.write(path,"\r\n".getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
