package com.xiu.log.monitor.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:40
 * @Description: 加密工具类
 */
public class EncryptionUtil {
    public EncryptionUtil() {
    }

    public static String encrypt(String s) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("utf-8");
        int length = bytes.length;

        for(int i = 0; i < length / 2; ++i) {
            byte b = bytes[length - 1 - i];
            bytes[length - 1 - i] = (byte)(bytes[i] ^ b);
            bytes[i] = b;
        }

        byte[] key = new byte[2];
        Random random = new Random();
        random.nextBytes(key);

        for(int i = 0; i < length - 1; i += 2) {
            byte b = bytes[i + 1];
            bytes[i + 1] = (byte)(bytes[i] ^ key[0]);
            bytes[i] = (byte)(b ^ key[1]);
        }

        byte[] ciper = new byte[length + 2];
        System.arraycopy(bytes, 0, ciper, 1, length);
        ciper[0] = key[0];
        ciper[ciper.length - 1] = key[1];
        return new String(ciper, "iso-8859-1");
    }

    public static String decrypt(String s) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("iso-8859-1");
        byte[] ciper = new byte[bytes.length - 2];
        System.arraycopy(bytes, 1, ciper, 0, ciper.length);
        int length = ciper.length;

        int i;
        byte b;
        for(i = 0; i < length - 1; i += 2) {
            b = ciper[i + 1];
            ciper[i + 1] = (byte)(ciper[i] ^ bytes[length + 1]);
            ciper[i] = (byte)(b ^ bytes[0]);
        }

        for(i = 0; i < length / 2; ++i) {
            b = ciper[i];
            ciper[i] ^= ciper[length - 1 - i];
            ciper[length - 1 - i] = b;
        }

        return new String(ciper, "utf-8");
    }
}
