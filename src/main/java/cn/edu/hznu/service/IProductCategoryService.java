package cn.edu.hznu.service;

import cn.edu.hznu.domain.ProductCategory;
import cn.edu.hznu.dto.ProductCategoryExecution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wjj on 2020/4/14
 */
public interface IProductCategoryService {
    /*
    * 根据id获取商品类别
    * */
    List<ProductCategory> findAllProductCategory(Long shopId);

    /*
    * 批量插入商品类别
    * */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory>lists);

    /*
     * 删除指定商品类别
     * */
    ProductCategoryExecution deleteProductCategory( long productcategoryid,  long shopId);
}
