package com.jayjav.jaycoin.util;

import java.security.MessageDigest;

import static com.jayjav.jaycoin.util.ApplicationConstants.CHARACTER_SET_NAME;
import static com.jayjav.jaycoin.util.ApplicationConstants.ENCRYPTION_ALGORITHM;

/**
 * @author OMONIYI ILESANMI
 */
public class EncyptionUtil {

    private EncyptionUtil(){

    }

    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            byte[] hash = digest.digest(input.getBytes(CHARACTER_SET_NAME));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
