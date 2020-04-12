package cn.edu.hznu.dao;

import cn.edu.hznu.domain.Shop;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by wjj on 2020/4/11
 */
public interface IShopDao {
    //返回值-1 失败 1成功
    int insertShop(Shop shop);

    //更新店铺信息
    int updateShop(Shop shop);
}
