package com.isoftstone.upala.assets.service;

import com.isoftstone.upala.assets.pojo.Goods;

import java.util.List;

/*****************************
 *  @author 王鹏
 *  @version 2019/1/20 22:26
 *  @package com.isoftstone.upala.assets.service
 *  @project assets
 *  @describe
 *****************************/
public interface IGoodsService
{

    public List<Goods> queryAllGoods (int start, int size);

    public String queryGoods (String goodsNo);

}
