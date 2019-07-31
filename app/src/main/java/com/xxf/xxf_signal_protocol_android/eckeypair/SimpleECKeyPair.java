package com.xxf.xxf_signal_protocol_android.eckeypair;


import com.xxf.xxf_signal_protocol_android.Base64;

import org.whispersystems.libsignal.InvalidKeyException;
import org.whispersystems.libsignal.ecc.Curve;
import org.whispersystems.libsignal.ecc.ECKeyPair;
import org.whispersystems.libsignal.ecc.ECPrivateKey;
import org.whispersystems.libsignal.ecc.ECPublicKey;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @Description
 */
public class SimpleECKeyPair extends ECKeyPair {
    private SimpleECKeyPair(ECPublicKey publicKey, ECPrivateKey privateKey) {
        super(publicKey, privateKey);
    }

    SimpleECKeyPair(ECKeyPair ecKeyPair) {
        super(ecKeyPair.getPublicKey(), ecKeyPair.getPrivateKey());
    }

    public SimpleECKeyPair(String publicKey, String privateKey) throws InvalidKeyException {
        super(Curve.decodePoint(Base64.decode(publicKey), 0), Curve.decodePrivatePoint(Base64.decode(privateKey)));
    }

    public String getPublicKeyString() {
        return Base64.encode(getPublicKey().serialize());
    }

    public String getPrivateKeyString() {
        return Base64.encode(getPrivateKey().serialize());
    }
}
