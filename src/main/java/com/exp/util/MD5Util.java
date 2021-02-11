package com.exp.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Contains utility methods that use MD5 hashing. The class uses STANDARD JVM MD5 algorithm.
 *
 * @author Paul Cook
 */
public class MD5Util {

    /**
     * Calculates MD5 hash for string. assume string is UTF-8 encoded
     * @param input string which is going to be encoded into MD5 format
     * @return  MD5 representation of the input string
     * @throws NoSuchAlgorithmException if the MD5 algorithm is not available.
     */
    public static String calculateMd5(String input) throws NoSuchAlgorithmException {
        try {
            return calculateMd5(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null; // -- impossible --
        }
    }

    /**
     * Calculates MD5 hash for string.
     * @param input string which is going to be encoded into MD5 format
     * @param encoding character encoding of the string which is going to be encoded into MD5 format
     * @return  MD5 representation of the input string
     * @throws NoSuchAlgorithmException if the MD5 algorithm is not available.
     * @throws UnsupportedEncodingException if the specified encoding is unavailable.
     */
    public static String calculateMd5(String input, String encoding) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes(encoding));
        byte digest[] = md.digest();

        final StringBuilder hexString = new StringBuilder();
        for (byte element : digest) {
            int z = 0xFF & element;
            if (z < 16)
                hexString.append("0");
            hexString.append(Integer.toHexString(z));
        }

        return hexString.toString();
    }

}
