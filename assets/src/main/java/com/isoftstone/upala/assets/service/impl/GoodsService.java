package com.isoftstone.upala.assets.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isoftstone.upala.assets.mapper.IGoodsMapper;
import com.isoftstone.upala.assets.pojo.Goods;
import com.isoftstone.upala.assets.service.IGoodsService;
import com.isoftstone.upala.assets.utils.RedisUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************
 *  @author 王鹏
 *  @version 2019/1/20 22:27
 *  @package com.isoftstone.upala.assets.service
 *  @project assets
 *  @describe
 *****************************/

@Service
@Log4j2
public class GoodsService implements IGoodsService
{

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IGoodsMapper goodsMapper;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Goods> queryAllGoods(int start, int size)
    {
        try
        {
            return goodsMapper.queryAllGoods(start, size);
        } catch (Exception e)
        {
            log.error("数据库查询出错！", e);
            return null;
        }
    }

    @Override
    public String queryGoods(String goodsNo)
    {
        String goodsJSON = null;
        try
        {
            Goods goods = goodsMapper.queryGoods(goodsNo);
            goodsJSON = mapper.writeValueAsString(goods);
            Long result = redisUtils.setData("allGoods", goodsNo, goodsJSON);
            log.info(result);
        } catch (Exception e)
        {
            log.error("查询数据出错！", e);
        }
        return goodsJSON;
    }
}
