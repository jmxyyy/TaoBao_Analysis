package backend.controller;

import backend.entity.UserBehavior;
import backend.entity.DateStatVo;
import backend.entity.HourlyBehaviorVo;

import backend.product.entity.*;

import backend.service.taobaoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    taobaoService bigDataService;

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }

    @GetMapping("/user_behavior")
    public List<UserBehavior> queryUserBehavior(){
        return bigDataService.queryUserBehavior();
    }

    @GetMapping("/date_stat")
    public DateStatVo queryDateStat(){
        return bigDataService.queryDateStat();
    }

    @GetMapping("/hourly_behavior")
    public HourlyBehaviorVo queryHourlyBehavior() {
        return bigDataService.queryHourlyBehavior();
    }

    @GetMapping("/product_stat")
    public List<ProductStat> queryProductStat() {
        return bigDataService.queryProductStat();
    }

    @GetMapping("/product_stat01")
    public List<ProductStat01> queryProductStat01() {
        return bigDataService.queryProductStat01();
    }

    @GetMapping("/product_stat02")
    public ProductStat02 queryProductStat02() {
        return bigDataService.queryProductStat02();
    }
}
