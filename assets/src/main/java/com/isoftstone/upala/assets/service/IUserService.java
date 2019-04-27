package com.isoftstone.upala.assets.service;

import com.isoftstone.upala.assets.pojo.User;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/19 22:45
 *  @package com.isoftstone.upala.assets.service
 *  @project assets
 *  @describe
 *****************************/
public interface IUserService
{

    public String queryUsername(String username);

    public int addUser (User user);

    public int queryUserState (String userNo);

    public User queryUser (String username, String password, String userNo);

    public String queryUserNo (String username, String password);

    public String updateUserState (int state, String userNo);

    public User loginUser (String username, String password);

    public void addPicture (String picture, String userNo);

}
