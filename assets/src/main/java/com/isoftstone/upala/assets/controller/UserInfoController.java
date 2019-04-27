package com.isoftstone.upala.assets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isoftstone.upala.assets.pojo.Goods;
import com.isoftstone.upala.assets.service.IUserService;
import com.isoftstone.upala.assets.utils.PathUtils;
import com.isoftstone.upala.assets.utils.RedisUtils;
import com.isoftstone.upala.assets.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/*****************************
 *  @author 王鹏
 *  @version 2019/3/8 20:29
 *  @package com.isoftstone.upala.assets.controller
 *  @project assets
 *  @describe
 *****************************/
@Controller
@RequestMapping("/userInfo")
@Log4j2
public class UserInfoController
{

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IUserService userService;

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String updateImage (@RequestParam("myFile") MultipartFile file, HttpSession session)
    {
        String userNo = (String)session.getAttribute("userNo");
        // 判断上传文件是否为空
        if (null == file || "".equals(file.toString()))
        {
            return "null";
        }
        if (file.getSize() < 0)
        {
            return "no";
        }
        // 获取文件名称
        String fileName = file.getOriginalFilename();
        String[] strFile = fileName.split("\\.");
        String uuidFile = StringUtil.getNo() +"."+ strFile[1];
        String filePath = PathUtils.getPath()+ File.separator+uuidFile;
        log.info(filePath);
        try
        {
            file.transferTo(new File(filePath));
            userService.addPicture (uuidFile, userNo);
        } catch (IOException e)
        {
            log.error("上传文件出错！", e);
        }
        return uuidFile;
    }

    @RequestMapping("/getGoods")
    @ResponseBody
    public Goods getGoods ()
    {
        String goodJson = redisUtils.getData("goodsReviewSuccess", "716d67b952a74d029823fa0c2d0eb8b3");
        ObjectMapper mapper = new ObjectMapper();
        Goods goods = null;
        try
        {
            goods = mapper.readValue(goodJson, Goods.class);
        } catch (IOException e)
        {
            log.error("json转换出错！", e);
        }
        return goods;
    }

}
