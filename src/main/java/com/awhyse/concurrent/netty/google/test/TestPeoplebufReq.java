package com.awhyse.concurrent.netty.google.test;

import com.awhyse.concurrent.netty.google.protobuf.PeopleBuf;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by whyse
 * on 2017/1/24 16:21
 */
public class TestPeoplebufReq {

    static Logger logger = LoggerFactory.getLogger(TestPeoplebufReq.class);
    //======================================
    public static void main(String[] args) {
        //创建一个传输msg
        PeopleBuf.PeopleMsg.Builder peopleMsgBuilder = PeopleBuf.PeopleMsg.newBuilder();
        peopleMsgBuilder.setName("xumin");
        peopleMsgBuilder.setAge(27);
        PeopleBuf.PeopleMsg people = peopleMsgBuilder.build();

        PeopleBuf.PeopleMsg people2 = null;

        byte[] encodeBytes = people.toByteArray();//对peopleMSG对象进行编码
        try {
            people2 =  PeopleBuf.PeopleMsg.parseFrom(encodeBytes);//用包装对象解码
            logger.debug(people2.toString());
            System.out.println(people.equals(people2));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
