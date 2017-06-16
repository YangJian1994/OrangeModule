package com.example.orangemodule.utils;

/**
 * Created by 杨健 on 2017/6/15.
 * function: 数据的工具类
 */

public class MessageUtils {
    //小端模式
    public static byte[] intToBytes(int value)
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }

    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset+1] & 0xFF)<<8)
                | ((src[offset+2] & 0xFF)<<16)
                | ((src[offset+3] & 0xFF)<<24));
        return value;
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    //int类型转HexString
    public static String int2HexStr(int i) {
        String str = null;
        try {
            str = Integer.toHexString(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String printData(byte[] bytes) {
        String data = "";
        String top = int2HexStr(bytesToInt(bytes, 0));
        String order = int2HexStr(bytesToInt(bytes, 4));
        String seq = int2HexStr(bytesToInt(bytes, 8));
        String len = int2HexStr(bytesToInt(bytes, 12));
        int dataLen = bytesToInt(bytes, 12);
        for (int i = 0; i < dataLen; i++) {
            data += (char)bytes[16 + i];
        }
        String sum = int2HexStr(bytesToInt(bytes, 16+dataLen));

        return data;
    }
}
