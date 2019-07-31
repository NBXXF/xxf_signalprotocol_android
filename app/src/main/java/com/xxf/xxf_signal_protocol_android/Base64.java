package com.xxf.xxf_signal_protocol_android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Base64 {

    private static String[] BASE_CHARS = {"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"};

    public static String encode(byte[] data) {
        return encode(data, 0, data.length, null).toString();
    }

    public static StringBuffer encode(byte[] data, int start, int len, StringBuffer buf) {
        int key = 0;
        char[] charT = BASE_CHARS[key].toCharArray();

        if (buf == null) {
            buf = new StringBuffer(data.length * 3 / 2);
        }
        int i = start;
        int end = len - 3;
        while (i <= end) {
            int d = (data[i] & 0xFF) << 16 | (data[(i + 1)] & 0xFF) << 8 | data[(i + 2)] & 0xFF;

            buf.append(charT[(d >> 18 & 0x3F)]);
            buf.append(charT[(d >> 12 & 0x3F)]);
            buf.append(charT[(d >> 6 & 0x3F)]);
            buf.append(charT[(d & 0x3F)]);
            i += 3;
        }

        if (i == start + len - 2) {
            int d = (data[i] & 0xFF) << 16 | (data[(i + 1)] & 0xFF) << 8;

            buf.append(charT[(d >> 18 & 0x3F)]);
            buf.append(charT[(d >> 12 & 0x3F)]);
            buf.append(charT[(d >> 6 & 0x3F)]);
        } else if (i == start + len - 1) {
            int d = (data[i] & 0xFF) << 16;
            buf.append(charT[(d >> 18 & 0x3F)]);
            buf.append(charT[(d >> 12 & 0x3F)]);
        }
        return buf;
    }

    public static int decode(char c, int key) {
        char[] charT = BASE_CHARS[key].toCharArray();
        if (c == '=') {
            return 0;
        }
        for (int i = 0; i < 64; i++) {
            if (charT[i] == c) {
                return i;
            }
        }
        return 0;
    }

    public static byte[] decode(String s) {
        byte[] temp = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (s == null)
            return null;
        try {
            decode(s, bos);
            temp = bos.toByteArray();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    public static void decode(String s, ByteArrayOutputStream bos) {
        int i = 0;
        int len = s.length();
        int nKey = 0;
        int nAddNum = s.length() % 4;
        if (nAddNum > 0) {
            nAddNum = 4 - nAddNum;
        }
        while (nAddNum > 0) {
            s = s + '=';
            nAddNum--;
        }
        while (true)
            if ((i < len) && (s.charAt(i) <= ' ')) {
                i++;
            } else {
                if (i == len) {
                    break;
                }
                int tri = (decode(s.charAt(i), nKey) << 18) + (decode(s.charAt(i + 1), nKey) << 12)
                        + (decode(s.charAt(i + 2), nKey) << 6) + decode(s.charAt(i + 3), nKey);

                bos.write(tri >> 16 & 0xFF);
                if (s.charAt(i + 2) == '=') {
                    break;
                }
                bos.write(tri >> 8 & 0xFF);
                if (s.charAt(i + 3) == '=') {
                    break;
                }
                bos.write(tri & 0xFF);
                i += 4;
            }
    }

    public static boolean needBase64(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '-') {
                if (s.charAt(i) <= 'z')
                    if (((s.charAt(i) < 'a' ? 1 : 0) & (s.charAt(i) > 'Z' ? 1 : 0)) == 0)
                        if ((((s.charAt(i) < 'A' ? 1 : 0) & (s.charAt(i) > '9' ? 1 : 0)) == 0)
                                && (s.charAt(i) >= '0'))
                            continue;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String source = "abcabc";
        String encode = encode(source.getBytes());
        String decode = new String(decode(encode));
        System.out.println("源串:" + source);
        System.out.println("编码:" + encode);
        System.out.println("解码:" + decode);
    }
}