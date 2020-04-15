package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IProductCategoryDao;
import cn.edu.hznu.domain.ProductCategory;
import cn.edu.hznu.dto.ProductCategoryExecution;
import cn.edu.hznu.enums.ProductCategoryEnum;
import cn.edu.hznu.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.util.List;

/**
 * Created by wjj on 2020/4/14
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Autowired
    private IProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findAllProductCategory(Long shopId) {
        return productCategoryDao.findAllProductCategory(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> lists) {
        if(lists==null||lists.size()==0){
            return new ProductCategoryExecution(ProductCategoryEnum.INNER_ERROR);
        } else {
            try {
                int res=0;
                res=productCategoryDao.batchAddProductCategory(lists);
                if(res<=0){
                    throw new RuntimeException("店铺创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

    }

    @Override
    public ProductCategoryExecution deleteProductCategory(long productcategoryid, long shopId) {
        int effectnum=productCategoryDao.deleteProductCategory(productcategoryid,shopId);
        try {
            if(effectnum<=0){
                throw new RuntimeException("删除失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("删除失败"+e.getMessage());
        }

    }
}
