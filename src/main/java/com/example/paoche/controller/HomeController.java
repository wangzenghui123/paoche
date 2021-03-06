package com.example.paoche.controller;


import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.service.HomeService;
import com.example.paoche.util.DataResult;
import com.example.paoche.vo.resp.HomeRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@Slf4j
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping("/home/menu")
    @ResponseBody
    public DataResult home(){
        System.out.println("请求home菜单数据");
        DataResult<HomeRespVO> dataResult = DataResult.getResult(BaseResponseCode.SUCCESS);
        dataResult.setData(homeService.getHomeMenus());
        System.out.println(homeService.getHomeMenus().toString());
        return dataResult;
    }


    @RequestMapping("/home")
    public String home1(){
        log.info("进入home方法，到主页");
        return "main";
    }
}
