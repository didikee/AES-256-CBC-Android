package github.didikee.aes256cbc.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by didikee on 2017/10/30.
 */

public class PhpUtil {
    /**
     * 实现php的
     * $s = hash_hmac('sha256', 'text', 'key', true);
     *
     * @return
     */
    public static byte[] hash_hmac_sha256(String key, String text) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            return mac.doFinal(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
