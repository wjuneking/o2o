package cn.edu.hznu.dao;

import cn.edu.hznu.domain.Product;
import cn.edu.hznu.domain.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wjj on 2020/4/15
 */
public interface IProductDao {
    //添加商品
    int insertProduct(Product product);

    //更新商品信息
    int updateProduct(Product Product);

    //查询产品信息
    Product queryProduct(long ProductId);


}
