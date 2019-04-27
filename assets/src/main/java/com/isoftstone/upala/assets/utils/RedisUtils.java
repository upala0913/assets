package com.isoftstone.upala.assets.utils;

import com.isoftstone.upala.assets.commons.RedisConf;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/*****************************
 *  @author 王鹏
 *  @version 2019/3/10 16:25
 *  @package com.isoftstone.upala.assets.utils
 *  @project assets
 *  @describe
 *****************************/

@Component
@Log4j2
public class RedisUtils
{

    private Jedis jedis = RedisConf.getConnection();

    // 获取数据
    public String getData (String key, String field)
    {
        String goods = jedis.hget(key, field);
        log.info(goods);
        return goods;
    }

    // 添加数据
    public Long setData (String key, String field, String objectJSON)
    {
        Long result = jedis.hset(key, field, objectJSON);
        log.info(result);
        return result;
    }

}
