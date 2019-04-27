package com.isoftstone.upala.assets.pojo;

import lombok.Data;

import java.util.Date;

/*****************************
 *  @author 王鹏
 *  @version 2019/1/20 21:48
 *  @package com.isoftstone.upala.assets.pojo
 *  @project assets
 *  @describe 商品实体类
 *****************************/

@Data
public class Goods
{
    private int id;
    private String goodsNo;
    private String goodsName;
    private int goodsSellPrice;
    private int goodsStockPrice;
    private Date goodsCreateTime;
    private Date goodsUploadTime;
    private String goodsType;
    private int goodsNumber;
    private int goodsReview;
    private String goodsImage;
    private String goodsDescribe;
}
