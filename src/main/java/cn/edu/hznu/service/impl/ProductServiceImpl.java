package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IProductDao;
import cn.edu.hznu.domain.Product;
import cn.edu.hznu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wjj on 2020/5/28
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> queryProductList(Product productCondition, int rowIndex, int pageSize) {
        return productDao.queryProductList(productCondition,rowIndex,pageSize);
    }

    @Override
    public int queryProductCount(Product productCondition) {
        return productDao.queryProductCount(productCondition);
    }

    @Override
    public Product queryProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }
}
