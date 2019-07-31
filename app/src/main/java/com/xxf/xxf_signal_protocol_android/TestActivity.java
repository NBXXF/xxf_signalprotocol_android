package com.xxf.xxf_signal_protocol_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyPair;

public class TestActivity extends AppCompatActivity {

    TextView publicKeyTv;
    TextView privateKeyTv;
    EditText otherPublicKeyEt;
    TextView keyTv;
    String publicKey;
    String privateKey;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        publicKeyTv = findViewById(R.id.publicKeyTv);
        privateKeyTv = findViewById(R.id.privateKeyTv);
        otherPublicKeyEt = findViewById(R.id.otherPublicKeyEt);
        keyTv = findViewById(R.id.keyTv);
        findViewById(R.id.bt_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(otherPublicKeyEt.getText())) {
                    Toast.makeText(v.getContext(), "请输入他人public key:", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    byte[] secretKey = DHUtils.getSecretKey(Base64.decode(otherPublicKeyEt.getText().toString()), Base64.decode(privateKey));
                    // byte[] secretKey = DHUtils.getSecretKey(Base64.decode(publicKey), Base64.decode(privateKey));
                    keyTv.setText(Base64.encode(secretKey));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("=====>", "error:", e);
                    keyTv.setText(e.getMessage());
                    Toast.makeText(v.getContext(), "异常:" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        initkey();
    }

    private void initkey() {
        try {
            KeyPair keyPair = DHUtils.initRandomKey();
            publicKey = Base64.encode(keyPair.getPublic().getEncoded());
            privateKey = Base64.encode(keyPair.getPrivate().getEncoded());

            publicKeyTv.setText(publicKey);
            privateKeyTv.setText(privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
