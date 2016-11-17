package com.honliv.honlivmall.util;

import android.util.Log;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class SignUtils {

    private static final String ALGORITHM = "RSA";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String TAG = "SignUtils";

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decode(privateKey));
            Log.i(TAG, "KeyFactory");
            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM, "BC");
            Log.i(TAG, "PrivateKey");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Log.i(TAG, "signature");
            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            Log.i(TAG, content);
            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));
            Log.i(TAG, "signed");
            byte[] signed = signature.sign();
            Log.i(TAG, "signed--" + signed[1]);

            return Base64.encode(signed);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return null;
    }

}
