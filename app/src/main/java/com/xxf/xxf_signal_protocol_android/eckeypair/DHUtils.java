package com.xxf.xxf_signal_protocol_android.eckeypair;

import com.xxf.xxf_signal_protocol_android.Base64;

import org.whispersystems.libsignal.InvalidKeyException;
import org.whispersystems.libsignal.ecc.Curve;
import org.whispersystems.libsignal.ecc.ECKeyPair;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @Description DH密钥交换
 */
public class DHUtils {

    /**
     * 生成密钥对
     *
     * @return
     */
    public static SimpleECKeyPair generateECKeyPair() {
        ECKeyPair ecKeyPair = Curve.generateKeyPair();
        return new SimpleECKeyPair(ecKeyPair);
    }

    /**
     * 生成交换密钥
     *
     * @param publicKey
     * @param privateKey
     * @return
     * @throws InvalidKeyException
     */
    public static String generateECKey(String publicKey, String privateKey) throws InvalidKeyException {
        SimpleECKeyPair simpleECKeyPair = new SimpleECKeyPair(publicKey, privateKey);
        byte[] bytes = Curve.calculateAgreement(simpleECKeyPair.getPublicKey(), simpleECKeyPair.getPrivateKey());
        return Base64.encode(bytes);
    }
}
