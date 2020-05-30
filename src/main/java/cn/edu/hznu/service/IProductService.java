package cn.edu.hznu.service;

import cn.edu.hznu.domain.Product;
import cn.edu.hznu.dto.ImageHolder;
import cn.edu.hznu.dto.ProductExecution;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;

/**
 * Created by wjj on 2020/5/28
 */
public interface IProductService {
    List<Product> queryProductList(Product productCondition,int rowIndex, int pageSize);

    int queryProductCount(Product productCondition);

    Product queryProductById(long productId);

    ProductExecution insertProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imglist);

    ProductExecution updateProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imglist);

}


