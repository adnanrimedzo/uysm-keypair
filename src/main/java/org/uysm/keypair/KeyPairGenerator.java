package org.uysm.keypair;

import javafx.util.Pair;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class KeyPairGenerator {

    public final static Pair<String, String> generate(String inputKey) {
        String randomKey = UUID.randomUUID().toString();
        String chipherText = AES.encrypt(inputKey, randomKey.substring(0, 16));

        String key1 = chipherText.substring(0, chipherText.length() / 2)
                + randomKey.substring(0, 8);

        String key2 = chipherText.substring(chipherText.length() / 2)
                + randomKey.substring(8,16);

        return new Pair<>(Hex.encodeHexString(key1.getBytes()), Hex.encodeHexString(key2.getBytes()));
    }

    public final static String decode(Pair<String, String> keys) throws DecoderException, UnsupportedEncodingException {

        String key1=new String(Hex.decodeHex(keys.getKey().toCharArray()), "UTF8");
        String key2=new String(Hex.decodeHex(keys.getValue().toCharArray()), "UTF8");

        String chipherText = key1.substring(0, key1.length() - 8) +
                key2.substring(0, key2.length() - 8);

        String randomKey = key1.substring(key1.length() -8)
                + key2.substring(key2.length() -8);

        return AES.decrypt(chipherText,randomKey);
    }

}
