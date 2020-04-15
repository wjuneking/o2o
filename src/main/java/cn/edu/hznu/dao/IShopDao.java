package cn.edu.hznu.dao;

import cn.edu.hznu.domain.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
public interface IShopDao {
    //返回值-1 失败 1成功
    int insertShop(Shop shop);

    //更新店铺信息
    int updateShop(Shop shop);

    //查询店铺信息
    Shop queryById(long shopId);

    //分页查询店铺
    List<Shop>queryShopList(@Param("shopCondition") Shop shop,@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);

    //获取店铺总数
    int queryShopCount(@Param("shopCondition") Shop shop);
}
