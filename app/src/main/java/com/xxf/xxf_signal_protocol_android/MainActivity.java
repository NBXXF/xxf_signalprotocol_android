package com.xxf.xxf_signal_protocol_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import org.whispersystems.libsignal.ecc.Curve;
import org.whispersystems.libsignal.ecc.ECKeyPair;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();

        startActivity(new Intent(this, TestActivity2.class));
    }

    private static void log(String s) {
        Log.d("========>", s);
    }

    private void test() {

//        IdentityKeyPair identityKeyPair = KeyHelper.generateIdentityKeyPair();
//        int registrationId = KeyHelper.generateRegistrationId(true);
//        List<PreKeyRecord> preKeys = KeyHelper.generatePreKeys(startId, 100);
//        SignedPreKeyRecord signedPreKey = KeyHelper.generateSignedPreKey(identityKeyPair, 5);
//
//        SessionStore sessionStore = new MySessionStore();
//        PreKeyStore preKeyStore = new MyPreKeyStore();
//        SignedPreKeyStore signedPreKeyStore = new MySignedPreKeyStore();
//        IdentityKeyStore identityStore = new MyIdentityKeyStore();
//
//        // Instantiate a SessionBuilder for a remote recipientId + deviceId tuple.
//        SessionBuilder sessionBuilder = new SessionBuilder(sessionStore, preKeyStore, signedPreKeyStore,
//                identityStore, recipientId, deviceId);
//
//        // Build a session with a PreKey retrieved from the server.
//        sessionBuilder.process(retrievedPreKey);
//
//        SessionCipher sessionCipher = new SessionCipher(sessionStore, recipientId, deviceId);
//        CiphertextMessage message = sessionCipher.encrypt("Hello world!".getBytes("UTF-8"));
//
//
//        deliver(message.serialize());

        try {
            User a = new User("张三");

            User b = new User("李四");


            byte[] bytes = Curve.calculateAgreement(b.getKeys().getPublicKey(), a.getKeys().getPrivateKey());
            String key1 = new String(bytes, "UTF-8");

            byte[] bytes2 = Curve.calculateAgreement(a.getKeys().getPublicKey(), b.getKeys().getPrivateKey());
            String key2 = new String(bytes2, "UTF-8");


            log("======>key1:" + key1 + "key2:" + key2 + " key1==key2 ? " + (TextUtils.equals(key1, key2)));

            ECKeyPair keys = Curve.generateKeyPair();
            byte[] message = new byte[1024 * 1024];

            byte[] signature = Curve.calculateSignature(keys.getPrivateKey(), message);

            boolean res = Curve.verifySignature(keys.getPublicKey(), message, signature);
            log("======>res:" + res);

            message[0] ^= 0x01;

            boolean res2 = Curve.verifySignature(keys.getPublicKey(), message, signature);
            log("======>res2:" + res2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class User {
        String name;
        ECKeyPair keys;

        public User(String name) {
            this.name = name;
            keys = Curve.generateKeyPair();
        }

        public ECKeyPair getKeys() {
            return keys;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
