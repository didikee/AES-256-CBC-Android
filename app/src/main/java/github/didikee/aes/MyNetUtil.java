package github.didikee.aes;


import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.UUID;

import github.didikee.aes256cbc.AES256Util;
import github.didikee.aes256cbc.utils.ArrayUtil;
import github.didikee.aes256cbc.utils.PhpUtil;


/**
 * Created by didikee on 2017/10/26.
 */

public class MyNetUtil {
    private static final String TAG = "ssss";
    private static final String STRING_FORMAT = "utf-8";
    private static final int IV_LENGTH = 16;
    private static final int HMAC_HASH_LENGTH = 32;
    private static final String ENCRYPTION_KEY = "415F0DCDDDA03E574AEC0DA9C9448702";


    /**
     * 创建一个随机的16位字符串
     * @return
     */
    private static String makeRawIv() {
        return UUID.randomUUID().toString().substring(0, IV_LENGTH);
    }

    public static String decrypt(String codeText) {
        try {

            byte[] decode64 = Base64.decode(codeText, Base64.NO_WRAP);
            Log.d(TAG, "decode64: " + decode64.length);
            byte[] iv_byte = Arrays.copyOfRange(decode64, 0, IV_LENGTH);
            Log.d(TAG, "iv_byte: " + iv_byte.length);
            byte[] hmac_byte = Arrays.copyOfRange(decode64, IV_LENGTH, IV_LENGTH + HMAC_HASH_LENGTH);
            Log.d(TAG, "hmac_byte: " + hmac_byte.length);
            byte[] ase_byte = Arrays.copyOfRange(decode64, IV_LENGTH + HMAC_HASH_LENGTH, decode64.length);
            Log.d(TAG, "ase_byte: " + ase_byte.length);

            byte[] decrypt = AES256Util.decrypt(ENCRYPTION_KEY.getBytes(STRING_FORMAT), iv_byte, ase_byte);
            if (decrypt != null) {
                return new String(decrypt);
            }
        } catch (IndexOutOfBoundsException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encrypt(String text) {
        String iv = makeRawIv();
        try {
//            byte[] encryptAesText = encryptAES(text.getBytes("utf-8"), iv.getBytes("utf-8"));
            byte[] encryptAesText = AES256Util.encrypt(ENCRYPTION_KEY, iv, text.getBytes(STRING_FORMAT));
            byte[] hmac = PhpUtil.hash_hmac_sha256(ENCRYPTION_KEY, text);
            return new String(Base64.encode(ArrayUtil.append(ArrayUtil.append(iv.getBytes(), hmac), encryptAesText), Base64.NO_WRAP));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";

    }


}
