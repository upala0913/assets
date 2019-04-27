package com.isoftstone.upala.assets.commons;

import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;

import java.util.ResourceBundle;

/*****************************
 *  @author 王鹏
 *  @version 2019/3/10 16:06
 *  @package com.isoftstone.upala.assets.utils
 *  @project assets
 *  @describe
 *****************************/
@Log4j2
public class RedisConf
{

    private static final String REDIS_HOST;
    private static final int REDIS_PORT;
    private static final String REDIS_PASS;
    private static Jedis jedis = null;

    static
    {
        ResourceBundle rb = ResourceBundle.getBundle("redisConf");
        REDIS_HOST = rb.getString("host");
        REDIS_PORT = Integer.parseInt(rb.getString("port"));
        REDIS_PASS = rb.getString("pass");
    }

    // 获取Redis连接对象
    public static Jedis getConnection ()
    {
        jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        if (null != jedis)
        {
            log.info("连接Redis成功！");
            jedis.auth(REDIS_PASS);
        }
        return jedis;
    }

}
