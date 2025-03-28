package backend.service;

import java.util.List;

import backend.entity.UserBehavior;
import backend.entity.DateStatVo;
import backend.entity.HourlyBehaviorVo;

import backend.product.entity.*;

public interface taobaoService {
    List<UserBehavior> queryUserBehavior();
    DateStatVo queryDateStat();
    HourlyBehaviorVo queryHourlyBehavior();

    List<ProductStat> queryProductStat();
    List<ProductStat01> queryProductStat01();
    ProductStat02 queryProductStat02();
}
