package com.isoftstone.upala.assets.service.impl;

import com.isoftstone.upala.assets.mapper.IUserMapper;
import com.isoftstone.upala.assets.pojo.User;
import com.isoftstone.upala.assets.service.IUserService;
import com.isoftstone.upala.assets.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/19 22:45
 *  @package com.isoftstone.upala.assets.service.impl
 *  @project assets
 *  @describe
 *****************************/
@Service
@Log4j2
public class UserService implements IUserService
{

    @Autowired
    private IUserMapper userMapper;

    @Override
    public String queryUsername(String username)
    {
        String name = null;
        try
        {
            name = userMapper.queryUsername(username);
        } catch (Exception e)
        {
            log.error("查询出错！", e);
        }
        return name;
    }

    @Override
    public int addUser(User user)
    {
        String passwordMd5 = StringUtil.getHex(user.getUsername(), user.getPassword());
        String userNo = StringUtil.getNo();
        user.setPassword(passwordMd5);
        user.setUserNo(userNo);
        int i = 0;
        try
        {
            i = userMapper.addUser(user);
        } catch (Exception e)
        {
            log.error("添加用户出错！", e);
        }
        return i;
    }

    @Override
    public int queryUserState(String userNo)
    {
        log.info(userNo);
        int result = 0;
        try
        {
            result = userMapper.queryUserState(userNo);
        } catch (Exception e)
        {
            log.error("查询用户状态出错！", e);
        }
        return result;
    }

    @Override
    public User queryUser(String username, String password, String userNo)
    {
        String passwordMd5 = StringUtil.getHex(username, password);
        log.info(passwordMd5);
        User user = null;
        if (null == queryUserNo (username, password))
        {
            return null;
        }
        String userOldNo = queryUserNo (username, password);
        if (userNo.equals(userOldNo))
        {
            try
            {
                user = userMapper.queryUser(username, passwordMd5);
            } catch (Exception e)
            {
                log.info("查询用户出错！", e);
            }
            return user;
        }
        return null;
    }

    @Override
    public String queryUserNo(String username, String password)
    {
        String passwordMd5 = StringUtil.getHex(username, password);
        log.info(passwordMd5);
        String userNo = null;
        try
        {
            if (null == userMapper.queryUserNo(username, passwordMd5))
            {
                return null;
            }
            userNo = userMapper.queryUserNo(username, passwordMd5);
        } catch (Exception e)
        {
            log.error("查询用户编号出错1", e);
        }
        return userNo;
    }

    @Override
    public String updateUserState(int state, String userNo)
    {
        try
        {
            userMapper.updateUserState(state, userNo);
            return "ok";
        } catch (Exception e)
        {
            log.info("修改状态出错！", e);
            return "no";
        }
    }

    @Override
    public User loginUser(String username, String password)
    {
        String passwordMd5 = StringUtil.getHex(username, password);
        log.info(passwordMd5);
        User user = null;
        try
        {
            if (null == userMapper.queryUser(username, passwordMd5))
            {
                return null;
            }
            user = userMapper.queryUser(username, passwordMd5);
        } catch (Exception e)
        {
            log.error("查询用户出错！", e);
        }
        return user;
    }

    @Override
    public void addPicture(String picture, String userNo)
    {
        try
        {
            userMapper.addPicture(picture, userNo);
        } catch (Exception e)
        {
            log.error("添加头像出错！", e);
        }
    }
}
