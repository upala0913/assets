package com.isoftstone.upala.assets.commons;

import com.isoftstone.upala.assets.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/11 20:52
 *  @package com.isoftstone.upala.assets.utils
 *  @project assets
 *  @describe 拦截器配置
 *****************************/

@Log4j2
public class LoginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception
    {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        log.info("进入拦截器地址....");
        if (null != user)
        {
            return true;
        } else
        {
            response.sendRedirect("/login.html");
            return false;
        }
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }
}
