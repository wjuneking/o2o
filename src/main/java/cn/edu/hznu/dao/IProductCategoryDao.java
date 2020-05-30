package cn.edu.hznu.dao;

import cn.edu.hznu.domain.Product;
import cn.edu.hznu.domain.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作商品类别
 * Created by wjj on 2020/4/14
 */
public interface IProductCategoryDao {
    /*
    * 根据店铺id查询获得对应的店铺商品类别
    * */
    List<ProductCategory> findAllProductCategory(Long shopId);

    /*
    * 批量添加产品
    * */
    int batchAddProductCategory(List<ProductCategory>lists);

    /*
    * 删除制定商品类别
    * */
    int deleteProductCategory(@Param("productCategoryId") long productcategoryid,@Param("ShopId") long shopId);
}
