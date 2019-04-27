package com.isoftstone.upala.assets.utils;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/1 21:23
 *  @package com.isoftstone.upala.assets.utils
 *  @project assets
 *  @describe
 *****************************/
public class StringUtil
{

    public static String getCard (int num)
    {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i=0; i<num; i++)
        {
            int index = random.nextInt(len);
            char ch = str.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String getHex (String arg1, String arg2)
    {
        String md5 = DigestUtils.md5Hex(arg1 + arg2);
        return md5;
    }

    public static String getNo ()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean checkString (String username, String password)
    {
        return StringUtils.isEmpty(username) && StringUtils.isEmpty(password);
    }

}
















