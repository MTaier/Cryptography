package com.springboot.cryptography.service;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoService {

    private static final StrongTextEncryptor encryptor;

    static {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(System.getenv("CRYPTO_KEY"));
    }

    public static String encrypt(String data) {
        return encryptor.encrypt(data);
    }

    public static String decrypt(String encryptedData) {
        return encryptor.decrypt(encryptedData);
    }

}
