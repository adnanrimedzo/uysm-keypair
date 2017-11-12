package org.uysm.keypair;

import javafx.util.Pair;
import org.apache.commons.codec.DecoderException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class KeyPairGeneratorTest {

    String key = "adnan212142352dsjabvlsdjkfbda/fdgsdfgfsdhgfd";
    @Test
    public void generate() throws DecoderException, UnsupportedEncodingException {
        Pair<String, String> keys = KeyPairGenerator.generate(key);
        String inputKey = KeyPairGenerator.decode(keys);
        assertEquals(key,inputKey);
    }
}