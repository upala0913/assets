package com.isoftstone.upala.assets.utils;

import java.util.ResourceBundle;

/*****************************
 *  @author 王鹏
 *  @version 2019/1/30 23:41
 *  @package com.isoftstone.upala.assets.utils
 *  @project assets
 *  @describe
 *****************************/
public class PathUtils
{

    private static final String SERVER_PATH;

    static
    {
        ResourceBundle rb = ResourceBundle.getBundle("pathConfig");
        SERVER_PATH = rb.getString("serverPath");
    }

    public static String getPath ()
    {
        return SERVER_PATH;
    }

}
