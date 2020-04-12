package cn.edu.hznu.service;

import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.dto.ShopExecution;

import java.io.File;

/**
 * Created by wjj on 2020/4/11
 */
public interface IShopService {
    //添加商店，返回商店结果集，封装值结果对象
    ShopExecution addShop(Shop shop, File img);

}
