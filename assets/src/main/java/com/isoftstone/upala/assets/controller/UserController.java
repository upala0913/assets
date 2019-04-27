package com.isoftstone.upala.assets.controller;

import com.isoftstone.upala.assets.pojo.User;
import com.isoftstone.upala.assets.service.IUserService;
import com.isoftstone.upala.assets.utils.EmailCard;
import com.isoftstone.upala.assets.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/11 21:43
 *  @package com.isoftstone.upala.assets.controller
 *  @project assets
 *  @describe
 *****************************/
@Controller
@RequestMapping("/user")
@Log4j2
public class UserController
{

    @Autowired
    private IUserService userService;
    private int num = 6;
    private String card = StringUtil.getCard(num);

    /**
     * 发送邮箱
     * @param email
     * @return
     */
    @RequestMapping(value="/sendEmail", method=RequestMethod.POST)
    @ResponseBody
    public String sendEmail (String email)
    {
        log.info(email);
        if (StringUtils.isEmpty(email))
        {
            return "null";
        }
        boolean flag = EmailCard.sendMail(email, card);
        return flag ? "ok" : "no";
    }

    /**
     * 检测验证码
     * @param card
     * @return
     */
    @RequestMapping(value="/checkCard", method=RequestMethod.POST)
    @ResponseBody
    public String checkCard (String card)
    {
        log.info(card);
        if (StringUtils.isEmpty(card))
        {
            return "null";
        }
        String cardLower = card.toLowerCase();
        String cardLower1 = this.card.toLowerCase();
        return cardLower1.equals(cardLower)?"ok":"no";
    }

    /**
     * 检测账号是否存在
     * @param userCard
     * @return
     */
    @RequestMapping(value="/userCard", method=RequestMethod.POST)
    @ResponseBody
    public String userCard (String userCard)
    {
        log.info(userCard);
        String name = null;
        if (StringUtils.isEmpty(userCard))
        {
            return "null";
        }
        name = userService.queryUsername(userCard);
        return name != null ? "ok":"no";
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value="/registry", method=RequestMethod.POST)
    @ResponseBody
    public String registryUser (User user)
    {
        log.info(user.toString());
        int result = userService.addUser(user);
        return result > 0 ? "ok" : "no";
    }

    /**
     * 修改用户状态
     * @param state
     * @return
     */
    @RequestMapping(value="/updateUserState/{state}/{userNo}", method=RequestMethod.POST)
    @ResponseBody
    public String updateUserState (@PathVariable("state") int state, @PathVariable("userNo")String userNo)
    {
        if (StringUtils.isEmpty(userNo))
        {
            return "null";
        }
        log.info(state+"---"+userNo);
        String result = userService.updateUserState(state, userNo);
        log.info(result);
        return result;
    }

    /**
     * 检测用户状态
     * @param userNo
     * @return
     */
    @RequestMapping(value="/checkUserState/{userNo}", method=RequestMethod.POST)
    @ResponseBody
    public String getUserState (@PathVariable("userNo")String userNo)
    {
        log.info(userNo);
        if (StringUtils.isEmpty(userNo))
        {
            return "null";
        }
        int result = userService.queryUserState(userNo);
        return result == 0 ? "no" : "ok";
    }

    /**
     * 检测账号密码是否正确
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/queryUser", method=RequestMethod.POST)
    @ResponseBody
    public String queryUser (String username, String password, HttpSession session)
    {
        log.info(username+"--"+password);
        String userNo = (String)session.getAttribute("userNo");
        if (StringUtil.checkString(username, password))
        {
            return "null";
        }
        User user = userService.queryUser(username, password, userNo);
        return user == null?"no":"ok";
    }

    /**
     * 查询用户编号
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value="/queryUserNo", method=RequestMethod.POST)
    @ResponseBody
    public String queryUserNo (String username, String password, HttpSession session)
    {
        log.info(username+"--"+password);
        if (StringUtil.checkString(username, password))
        {
            return "null";
        }
        String userNo = userService.queryUserNo(username, password);
        log.info(userNo);
        session.setAttribute("userNo", userNo);
        return userNo == null ? "no":userNo;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/loginUser", method=RequestMethod.POST)
    @ResponseBody
    public String loginUser (String username, String password, HttpSession session)
    {
        log.info(username+"--"+password);
        if (StringUtil.checkString(username, password))
        {
            return "null";
        }
        User user = userService.loginUser(username, password);
        session.setAttribute("currentUser", user);
        return user != null?"ok":"no";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout (HttpSession session)
    {
        session.invalidate();
        return "redirect:/assets/";
    }

}
