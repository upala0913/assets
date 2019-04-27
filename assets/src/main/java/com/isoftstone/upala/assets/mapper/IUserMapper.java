package com.isoftstone.upala.assets.mapper;

import com.isoftstone.upala.assets.pojo.User;
import org.apache.ibatis.annotations.Param;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/19 22:41
 *  @package com.isoftstone.upala.assets.mapper
 *  @project assets
 *  @describe
 *****************************/
public interface IUserMapper
{

    public String queryUsername(@Param("username") String username) throws Exception;

    public int addUser (User user) throws Exception;

    public Integer queryUserState (@Param("userNo")String userNo) throws Exception;

    public User queryUser (@Param("username")String username, @Param("password")String password) throws Exception;

    public void updateUserState (@Param("state")int state, @Param("userNo")String userNo) throws Exception;

    public String queryUserNo (@Param("username")String username, @Param("password")String password) throws Exception;

    public void addPicture (@Param("picture")String picture, @Param("userNo")String userNo) throws Exception;

}
