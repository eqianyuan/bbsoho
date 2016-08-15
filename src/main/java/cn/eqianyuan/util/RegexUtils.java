package cn.eqianyuan.util;

import java.util.regex.Pattern;

/**
 * 常用正则表达式工具类
 * Created by jason on 2016-07-11.
 */
public class RegexUtils {

    /**
     * 是否为手机号码
     *
     * @param mobileNumber 手机号码
     * @return
     */
    public static boolean isMobile(String mobileNumber) {
        Pattern pattern = Pattern.compile("(13|14|15|18){1}[\\d]{9}");
        return pattern.matcher(mobileNumber).matches();
    }

    /**
     * 是否为纯正整数
     *
     * @param val 待校验内容
     * @return
     */
    public static boolean isDigital(String val) {
        Pattern pattern = Pattern.compile("[\\d]+");
        return pattern.matcher(val).matches();
    }

    /**
     * 是否为纯字母
     *
     * @param val 带校验内容
     * @return
     */
    public static boolean isAlphabet(String val) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        return pattern.matcher(val).matches();
    }

    /**
     * 是否为特殊字符
     *
     * @param val
     * @return
     */
    public static boolean isSpecialCharacters(String val) {
        Pattern pattern = Pattern.compile("\\W+");
        return pattern.matcher(val).matches();
    }

    /**
     * 是否含有为空白字符（空格/制表符/换页/换行/回车）
     * 纯空白、空白+字符、空白+字符+空白、字符+空白、字符+空白+字符
     *
     * @param val
     * @return
     */
    public static boolean hasBlankCharacters(String val) {
        Pattern pattern = Pattern.compile(".*\\s+.*");
        return pattern.matcher(val).matches();
    }

    /**
     * 是否包含数字
     *
     * @param val
     * @return
     */
    public static boolean hasDigital(String val) {
        Pattern pattern = Pattern.compile(".*\\d+.*");
        return pattern.matcher(val).matches();
    }

    /**
     * 是否包含字母及下划线
     *
     * @param val
     * @return
     */
    public static boolean hasAlphabet(String val) {
        Pattern pattern = Pattern.compile(".*[a-zA-z_]+.*");
        return pattern.matcher(val).matches();
    }

    /**
     * 是否包含特殊字符
     *
     * @param val
     * @return
     */
    public static boolean hasSpecialCharacters(String val) {
        Pattern pattern = Pattern.compile(".*\\W+.*");
        return pattern.matcher(val).matches();
    }

    /**
     * 是否为邮箱号码
     *
     * @param val
     * @return
     */
    public static boolean isEmail(String val) {
        Pattern pattern = Pattern.compile("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)");
        return pattern.matcher(val).matches();
    }

}
