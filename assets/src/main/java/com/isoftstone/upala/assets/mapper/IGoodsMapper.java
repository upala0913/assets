package com.isoftstone.upala.assets.mapper;

import com.isoftstone.upala.assets.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*****************************
 *  @author 王鹏
 *  @version 2019/1/20 21:54
 *  @package com.isoftstone.upala.assets.mapper
 *  @project assets
 *  @describe
 *****************************/
@Mapper
public interface IGoodsMapper
{

    public List<Goods> queryAllGoods(@Param("start")int start, @Param("size")int size) throws Exception;

    public Goods queryGoods (@Param("goodsNo") String goodsNo) throws Exception;

}
