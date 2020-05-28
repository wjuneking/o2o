package cn.edu.hznu.service;

import cn.edu.hznu.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wjj on 2020/5/28
 */
public interface IProductService {
    List<Product> queryProductList(Product productCondition,int rowIndex, int pageSize);

    int queryProductCount(Product productCondition);

    Product queryProductById(long productId);

    int insertProduct(Product product);

    int updateProduct(Product product);

}


