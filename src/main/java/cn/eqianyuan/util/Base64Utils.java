package cn.eqianyuan.util;

import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * Created by jason on 2016-08-29.
 */
public class Base64Utils {

    private static Logger logger = Logger.getLogger(Base64Utils.class);

    public static byte[] decode(String s) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return decoder.decodeBuffer(s);
        } catch (IOException e) {
            logger.error("base64.decodeBuffer(String) error", e);
            return new byte[0];
        }
    }
}
