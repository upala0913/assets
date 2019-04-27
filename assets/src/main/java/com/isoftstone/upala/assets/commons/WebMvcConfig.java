package com.isoftstone.upala.assets.commons;

import com.isoftstone.upala.assets.utils.PathUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 静态资源
 * @package_name} com.upala.util
 * @file_name} WebMvcConfig.java
 * @author  Upala
 * @oper_time    2018年11月12日
 * @version	1.0.0
 */
@Configuration
@Log4j2
public class WebMvcConfig extends WebMvcConfigurerAdapter
{

    /**
     * 加载静态资源
     * @param registry
     */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
	    // 获取映射路径
	    String path = PathUtils.getPath();
	    int index = path.lastIndexOf(":");
	    String filePath = "file"+path.substring(index);
	    log.info("filePath:"+filePath);

		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/img/**").addResourceLocations(filePath);
		super.addResourceHandlers(registry);
	}

    /**
     * 页面跳转
     * @param registry
     */
	@Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/login.html").setViewName("page/user/login");
        registry.addViewController("/registry.html").setViewName("page/user/registry");
        registry.addViewController("/info.html").setViewName("page/user/info");
        registry.addViewController("/assets/address").setViewName("page/goods/address");
        super.addViewControllers(registry);
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/assets/**",
                "/assets/address",
                "/img/**",
                "/static/**",
                "/login.html",
                "/registry.html",
                "/user/**",
                "/userInfo/getGoods",
                "/test/addGoods"
        );
    }

}
