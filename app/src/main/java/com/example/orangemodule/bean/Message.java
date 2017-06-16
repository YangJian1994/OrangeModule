package com.example.orangemodule.bean;

import com.example.orangemodule.utils.MessageUtils;

/**
 * Created by 杨健 on 2017/6/15.
 * function: 发送数据的实体类
 */

public class Message {

    public static int top = 0XA534785A;
    public static int air = 0X00000403;
    public static int battery = 0X00000103;
    public static int seq = 0XFFFFFFFE;
    public static int sum = 0X0000FFFE;

    public static String data1 = "once";
    public static String data2 = "all";

    //发送获取空气信息的字节数组
    public static byte[] sendAirBytes() {
        byte[] byte_1 = MessageUtils.byteMerger(MessageUtils.intToBytes(top), MessageUtils.intToBytes(air));
        byte[] byte_2 = MessageUtils.byteMerger(MessageUtils.intToBytes(seq), MessageUtils.intToBytes(4));
        byte[] byte_3 = MessageUtils.byteMerger(data1.getBytes(), MessageUtils.intToBytes(sum));

        return MessageUtils.byteMerger(MessageUtils.byteMerger(byte_1, byte_2), byte_3);
    }

    //发送获取空气详细数据的字节数组
    public static byte[] sendAllAirBytes() {
        byte[] byte_1 = MessageUtils.byteMerger(MessageUtils.intToBytes(top), MessageUtils.intToBytes(air));
        byte[] byte_2 = MessageUtils.byteMerger(MessageUtils.intToBytes(seq), MessageUtils.intToBytes(3));
        byte[] byte_3 = MessageUtils.byteMerger(data2.getBytes(), MessageUtils.intToBytes(sum));

        return MessageUtils.byteMerger(MessageUtils.byteMerger(byte_1, byte_2), byte_3);
    }

    //发送获取电池信息的字节数组
    public static byte[] sendBatteryBytes() {
        byte[] byte_0 = new byte[]{};
        byte[] byte_1 = MessageUtils.byteMerger(MessageUtils.intToBytes(top), MessageUtils.intToBytes(battery));
        byte[] byte_2 = MessageUtils.byteMerger(MessageUtils.intToBytes(seq), MessageUtils.intToBytes(0));
        byte[] byte_3 = MessageUtils.byteMerger(byte_0, MessageUtils.intToBytes(sum));

        return MessageUtils.byteMerger(MessageUtils.byteMerger(byte_1, byte_2), byte_3);
    }

}
