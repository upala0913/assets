package com.isoftstone.upala.assets.controller;

import com.isoftstone.upala.assets.pojo.Goods;
import com.isoftstone.upala.assets.service.IGoodsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/*****************************
 *  @author 王鹏
 *  @version 2019/1/20 1:50
 *  @package com.isoftstone.upala.assets.controller
 *  @project assets
 *  @describe
 *****************************/

@Controller
@RequestMapping("/assets")
@Log4j2
public class PageController
{

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value={"", " ", "/"}, method=RequestMethod.GET)
    public String index (Model model)
    {
        log.info("index");
        List<Goods> goods = queryAllGoods(0, 10);
        model.addAttribute("goods", goods);
        return "index";
    }

    public List<Goods> queryAllGoods (int start, int size)
    {
        List<Goods> listGoods = goodsService.queryAllGoods(start, size);
        log.info("商品信息：", listGoods);
        return listGoods;
    }

}
