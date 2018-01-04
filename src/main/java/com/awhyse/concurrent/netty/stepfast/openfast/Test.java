package com.awhyse.concurrent.netty.stepfast.openfast;

import org.openfast.ByteUtil;
import org.openfast.Context;
import org.openfast.Message;
import org.openfast.ScalarValue;
import org.openfast.codec.FastDecoder;
import org.openfast.codec.FastEncoder;
import org.openfast.template.Field;
import org.openfast.template.MessageTemplate;
import org.openfast.template.Scalar;
import org.openfast.template.loader.XMLMessageTemplateLoader;
import org.openfast.template.operator.Operator;
import org.openfast.template.type.Type;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;

/**
 * Created by whyse
 * on 2017/12/13 17:49
 */
public class Test {
    public static void main(String[] args) {

        XMLMessageTemplateLoader loader = new XMLMessageTemplateLoader();
        loader.setLoadTemplateIdFromAuxId(true);
        loader.load(resource("templates/templates.xml"));//templates,templateCS

        Context context = new Context();
        context.setTemplateRegistry(loader.getTemplateRegistry());


        byte[] encoded = ByteUtil.convertHexStringToByteArray("C0 81 01 F9 C0 F8 FE 87 1C 50 54 FC BF 06 6B C1 "
                + "06 6B C2 82 32 7B BA 81 B2 B2 81 FD 06 62 A0 D0 B8");
        FastDecoder decoder = new FastDecoder(context, new ByteArrayInputStream(encoded));

        Message versionInformationMessage = decoder.readMessage();
        assertEquals(249, versionInformationMessage.getInt("versNo"));

        Message resetMessage = decoder.readMessage();
        assertEquals(context.getTemplateRegistry().get("ResetMessage"), resetMessage.getTemplate());
        decoder.reset();
        Message orderBookDeltaInformationMessage = decoder.readMessage();
        assertEquals(1, orderBookDeltaInformationMessage.getSequence("entries").getLength());

//        test1();
//        test2();
    }

    private static void test2() {
        MessageTemplate template = new MessageTemplate("",
                new Field[] {
                        new Scalar("1", Type.U32, Operator.COPY, ScalarValue.UNDEFINED, false)
                });
        Context context = new Context();
        context.registerTemplate(113, template);

        Message message = new Message(template);
        //message.setInteger(1, 1);
        message.setInteger(1, 512);

        FastEncoder encoder = new FastEncoder(context);
        //assertEquals("11100000 11110001 10000001", encoder.encode(message));
        byte[] encoding = encoder.encode(message);
        System.err.println(new String(encoding));
        assertEquals("11100000 11110001 00000100 10000000", encoder.encode(message));
    }

    private static void test1() {
        MessageTemplate messageTemplate = new MessageTemplate("", new Field[] {});
        InputStream in = ByteUtil.createByteStream("11000000 11110001");
        Context context = new Context();
        context.registerTemplate(113, messageTemplate);

        Message message = new FastDecoder(context, in).readMessage();
        System.err.println( message.getInt(0));
        assertEquals(113, message.getInt(0));
    }

    protected static InputStream resource(String url) {
        return Test.class.getClassLoader().getResourceAsStream(url);
    }
}
