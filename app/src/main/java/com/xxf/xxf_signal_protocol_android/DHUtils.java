package com.xxf.xxf_signal_protocol_android;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DHUtils {
    /**
     * 非对称加密密钥算法
     */
    private static final String KEY_ALGORITHM = "DH";
    /**
     * 本地密钥算法，即对称加密密钥算法
     * 可选DES、DESede或者AES
     */
    private static final String SELECT_ALGORITHM = "DES";

    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * 密钥长度
     */

    private static final int KEY_SIZE = 512;
    //公钥
    public static String PUBLIC_KEY = "DHPublicKey";
    //私钥
    public static String PRIVATE_KEY = "DHPrivateKey";

    /**
     * 初始化甲方密钥
     *
     * @return Map 甲方密钥Map
     * @throws Exception
     */
    public static Map<String, String> initKey() throws Exception {
        //实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥对生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
        //甲方私钥
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        //将密钥对存储在Map中
        Map<String, String> keyMap = new HashMap<String, String>(2);
        keyMap.put(PUBLIC_KEY, Base64.encode(publicKey.getEncoded()));
        keyMap.put(PRIVATE_KEY, Base64.encode(privateKey.getEncoded()));
        return keyMap;
    }

    /**
     * 初始化甲方密钥
     *
     * @return Map 甲方密钥Map
     * @throws Exception
     */
    public static KeyPair initRandomKey() throws Exception {
        //实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥对生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * 初始化乙方密钥
     *
     * @param key 甲方公钥
     * @return Map 乙方密钥Map
     * @throws Exception
     */
    public static Map<String, String> initKey(byte[] key) throws Exception {
        //解析甲方公钥
        //转换公钥材料
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //由甲方公钥构建乙方密钥
        DHParameterSpec dhParameterSpec = ((DHPublicKey) pubKey).getParams();
        //实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥对生成器
        keyPairGenerator.initialize(dhParameterSpec);
        //产生密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //乙方公钥
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
        //乙方私约
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        //将密钥对存储在Map中
        Map<String, String> keyMap = new HashMap<String, String>(2);
        keyMap.put(PUBLIC_KEY, publicKey.getFormat());
        keyMap.put(PRIVATE_KEY, privateKey.toString());
        return keyMap;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final byte[] key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(SELECT_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), SELECT_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DHUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {

        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = data;

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密
            return result;
//            return Base64Utils.encode(result);//通过Base64转码返回

        } catch (Exception ex) {
            System.out.print("encrypt " + ex.getMessage());
        }

        return null;


    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

            //执行操作
            byte[] result = cipher.doFinal(data);

            return result;

        } catch (Exception ex) {
            System.out.print("decrypt " + ex.getMessage());
        }

        return null;
    }

    /**
     * 构建密钥
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return byte[] 本地密钥
     * @throws Exception
     */
    public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //初始化私钥
        //密钥材料转换
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        //产生私钥
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //实例化
        KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        //初始化
        keyAgree.init(priKey);
        keyAgree.doPhase(pubKey, true);
        //生成本地密钥
        SecretKey secretKey = keyAgree.generateSecret(SELECT_ALGORITHM);
        return secretKey.getEncoded();
    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥Map
     * @return byte[] 私钥
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥Map
     * @return byte[] 公钥
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }
}