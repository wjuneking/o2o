package cn.edu.hznu.service;

import cn.edu.hznu.domain.ShopCategory;

import java.util.List;

/**
 * Created by wjj on 2020/4/12
 */
public interface IShopCategoryService {
    //查询所有的商店分类
    List<ShopCategory> findAllShopCategory(ShopCategory shopCategory);
}
