package com.deepbar.framework.security.passwordencoder;

import org.springframework.security.crypto.codec.Utf8;

/**
 * Created by josh on 15/7/28.
 */
class PasswordEncoderUtils {
    static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {
            return false;
        } else {
            int result = 0;

            for (int i = 0; i < expectedLength; ++i) {
                result |= expectedBytes[i] ^ actualBytes[i];
            }

            return result == 0;
        }
    }

    private static byte[] bytesUtf8(String s) {
        return s == null ? null : Utf8.encode(s);
    }

    private PasswordEncoderUtils() {
    }
}
