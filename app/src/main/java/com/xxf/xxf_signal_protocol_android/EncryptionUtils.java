package com.xxf.xxf_signal_protocol_android;

public final class EncryptionUtils {

    /**
     * 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encodeString(String content, String key) {
        String result = XXTEA.encryptToBase64String(content, key);
        //TODO 写日志
        return result;
    }


    /**
     * 解密
     *
     * @param content
     * @param key
     * @return
     */
    public static String decodeString(String content, String key) {
        String result = XXTEA.decryptBase64StringToString(content, key);
        //TODO 写日志
        return result;
    }
}