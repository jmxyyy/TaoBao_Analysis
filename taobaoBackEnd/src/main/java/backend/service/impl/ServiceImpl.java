package backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.entity.DateStat;
import backend.entity.DateStatVo;
import backend.entity.UserBehavior;
import backend.entity.HourlyBehavior;
import backend.entity.HourlyBehaviorVo;

import backend.product.entity.*;
import backend.product.mapper.*;

import backend.mapper.DateStatMapper;
import backend.mapper.UserBehaviorMapper;
import backend.mapper.HourlyBehaviorMapper;

import backend.service.taobaoService;

@Service
public class ServiceImpl implements taobaoService{
    @Autowired
    UserBehaviorMapper userBehaviorMapper;

    @Override
    public List<UserBehavior> queryUserBehavior() {
        return userBehaviorMapper.queryUserBehavior();
    }

    @Autowired
    DateStatMapper dateStatMapper;

    @Override
    public DateStatVo queryDateStat() {
        List<DateStat> entityList = dateStatMapper.queryDateStat();
        List<String> typeList = Arrays.asList("pv", "fav", "cart", "buy");
        // 组装日期列表，第一个列表用于返回前端作为X轴，第二个列表用于后续循环处理数据
        List<String> dateStrList = new ArrayList<>();
        Set<Date> uniqueDates = new HashSet<>();

        // 填充两个列表
        for (DateStat item: entityList) {
            if (!uniqueDates.contains(item.getDate())){
                uniqueDates.add(item.getDate());
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String dateStr = sdf.format(item.getDate());
                dateStrList.add(dateStr);
            }
        }

        // 返回给前端的数据对象
        DateStatVo dateStatVo = new DateStatVo();
        dateStatVo.setDateList(dateStrList);
        dateStatVo.setEventCnt(new ArrayList<>());

        // 按顺序组装每个行为对应的每日行为信息
        for (String type: typeList) {
            List<Long> cntList = new ArrayList<>();
            for (Date itemDate: uniqueDates) {
                long cnt = entityList.stream().filter(v -> v.getDate().equals(itemDate) && v.getEventType().equals(type))
                .map(DateStat::getCnt).findFirst().orElse(Long.valueOf("0"));
                cntList.add(cnt);
            }
            dateStatVo.getEventCnt().add(cntList);
        }
        return dateStatVo;
    }

    @Autowired
    HourlyBehaviorMapper hourlyBehaviorMapper;

    @Override
    public HourlyBehaviorVo queryHourlyBehavior() {
        List<HourlyBehavior> entityList = hourlyBehaviorMapper.queryHourlyBehavior();
        List<String> typeList = Arrays.asList("pv", "fav", "cart", "buy");
        // 组装hour列表，第一个列表用于返回前端作为X轴，第二个列表用于后续循环处理数据
        List<String> hourStrList = new ArrayList<>();
        Set<String> uniqueHours = new HashSet<>();

        // 填充两个列表
        for (HourlyBehavior item: entityList) {
            if (!uniqueHours.contains(item.getHour())){
                uniqueHours.add(item.getHour());
                // SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                // String dateStr = sdf.format(item.getDate());
                hourStrList.add(item.getHour());
            }
        }

        // 返回给前端的数据对象
        HourlyBehaviorVo hourlyBehaviorVo = new HourlyBehaviorVo();
        hourlyBehaviorVo.setHourList(hourStrList);
        hourlyBehaviorVo.setEventCnt(new ArrayList<>());

        // 按顺序组装每个行为对应的hourly行为信息
        for (String type: typeList) {
            List<Long> cntList = new ArrayList<>();
            for (String itemHour: uniqueHours) {
                long cnt = entityList.stream().filter(v -> v.getHour().equals(itemHour) && 
                            v.getEventType().equals(type)).map(HourlyBehavior::getCnt).findFirst().orElse(Long.valueOf("0"));
                cntList.add(cnt);
            }
            hourlyBehaviorVo.getEventCnt().add(cntList);
        }
        return hourlyBehaviorVo;
    }


    @Autowired
    ProductStatMapper productStatMapper;

    @Override
    public List<ProductStat> queryProductStat() {
        return productStatMapper.queryProductStat();
    }

    @Autowired
    ProductStatMapper01 productStatMapper01;

    @Override
    public List<ProductStat01> queryProductStat01() {
        return productStatMapper01.queryProductStat01();
    }

    @Autowired
    ProductStatMapper02 productStatMapper02;

    @Override
    public ProductStat02 queryProductStat02() {
        List<ProductStat> entityList = productStatMapper02.queryProductStat02();
        // List<String> typeList = Arrays.asList("fav", "buy");
        // 组装日期列表，第一个列表用于返回前端作为X轴，第二个列表用于后续循环处理数据
        List<String> categoryStrList = new ArrayList<>();
        // Set<String> uniqueCategory = new HashSet<>();

        List<Long> cntList1 = new ArrayList<>();
        List<Long> cntList2 = new ArrayList<>();
        // 填充两个列表
        for (ProductStat item: entityList) {
            // if (!uniqueCategory.contains(item.getProductCategory())){
            //     uniqueCategory.add(item.getProductCategory());
            //     categoryStrList.add(item.getProductCategory());
            // }
            categoryStrList.add(item.getProductCategory());
            cntList1.add(item.getTotalSales());
            cntList2.add(item.getTotalFav());
        }

        // 返回给前端的数据对象
        ProductStat02 productStat02 = new ProductStat02();
        productStat02.setproductCategoryList(categoryStrList);
        productStat02.setEventCnt(new ArrayList<>());

        // 按顺序组装每个行为对应的每日行为信息
        // for (String type: typeList) {
        //     List<Long> cntList = new ArrayList<>();
        //     for (String itemCategory: uniqueCategory) {
        //         long cnt = entityList.stream().filter(v -> v.getProductCategory().equals(itemCategory) && 
        //                 v.getEventType().equals(type)).map(ProductStat::getCnt).findFirst().orElse(Long.valueOf("0"));
        //         cntList.add(cnt);
        //     }
        //     productStat02.getEventCnt().add(cntList);
        // }
        productStat02.getEventCnt().add(cntList1);
        productStat02.getEventCnt().add(cntList2);

        return productStat02;
    }
}
