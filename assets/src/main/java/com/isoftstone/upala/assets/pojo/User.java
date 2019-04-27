package com.isoftstone.upala.assets.pojo;

import lombok.Data;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/9 16:46
 *  @package com.isoftstone.upala.assets.pojo
 *  @project assets
 *  @describe 用户实体类
 *****************************/

@Data
public class User
{
    private int id;
    private String userNo;
    private String username;
    private String realName;
    private String password;
    private int userStatus;
    private int vipStatus;
    private String email;
    private String picture;

}
