package cn.edu.hznu.dao;

import cn.edu.hznu.domain.Area;
import cn.edu.hznu.domain.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLOutput;
import java.util.List;

/**
 * Created by wjj on 2020/4/12
 */
public interface IShopCategoryDao {

    //查询所有的商店分类
    List<ShopCategory> findAllShopCategory(@Param("shopCategory") ShopCategory shopCategory);
}
