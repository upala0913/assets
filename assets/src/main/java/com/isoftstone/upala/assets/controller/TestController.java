package com.isoftstone.upala.assets.controller;

import com.isoftstone.upala.assets.pojo.Goods;
import com.isoftstone.upala.assets.service.IGoodsService;
import com.isoftstone.upala.assets.utils.RedisUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*****************************
 *  @author 王鹏
 *  @version 2019/3/12 13:25
 *  @package com.isoftstone.upala.assets.controller
 *  @project assets
 *  @describe
 *****************************/

@Controller
@Log4j2
@RequestMapping("/test")
public class TestController
{

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/addGoods")
    @ResponseBody
    public String addGoods ()
    {
        String goodsJSON = goodsService.queryGoods("2uz6470fru0heh3hrepwqn94l85fbto4");
        log.info(goodsJSON);
        return goodsJSON;
    }

}
