package org.uysm.keypair;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESTest {

    String plainText="asjfjhadsgfhjagdshjfgads";
    String key="sggdfafasjjdsgkf";
    String chipherText = "9lkPy/HHHhYRF6MKJHxAXid2o1z9GXd5mBDhbddnl8U=";

    @Test
    public void encrypt() throws Exception {
        String result = AES.encrypt(plainText,key);
        assertEquals(chipherText,result);
    }

    @Test
    public void decrypt() throws Exception {
        String result = AES.decrypt(chipherText,key);
        assertEquals(plainText, result);
    }
}