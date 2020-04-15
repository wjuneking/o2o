package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IShopCategoryDao;
import cn.edu.hznu.domain.ShopCategory;
import cn.edu.hznu.service.IShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wjj on 2020/4/12
 */
@Service
public class ShopCategoryServiceImpl implements IShopCategoryService {
    @Autowired
    private IShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> findAllShopCategory(ShopCategory shopCategory) {
        return  shopCategoryDao.findAllShopCategory(shopCategory);
    }
}
