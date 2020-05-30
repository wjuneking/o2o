package cn.edu.hznu.service;

import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.dto.ImageHolder;
import cn.edu.hznu.dto.ShopExecution;

import java.io.File;
import java.io.InputStream;

/**
 * Created by wjj on 2020/4/11
 */
public interface IShopService {
    //添加商店，返回商店结果集，封装值结果对象
    ShopExecution addShop(Shop shop,ImageHolder img);

    //通过id获取店铺信息
    Shop queryById(long shopId);

    //更新店铺信息
    ShopExecution updateShop(Shop shop, ImageHolder img);

    public ShopExecution getShopList(Shop shopCondition,int pageindex,int pagesize);
}
