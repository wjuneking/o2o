package cn.edu.hznu.dao;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.Product;
import cn.edu.hznu.domain.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by wjj on 2020/5/27
 */
public class ProductTest extends BaseTest {
    @Autowired
    private IProductDao dao;
    //获取所有的商品列表 必须要含有店铺信息 id
    @Test
    public void TestqueryProductList() {
        Product p = new Product();
        Shop shop = new Shop();
        shop.setShopId(17L);
        p.setShop(shop);
        List<Product>products=dao.queryProductList(p, 0, 999);
        for (Product product : products) {
            System.out.println(product);
        }
    }
    //获取所有的商品列表 必须要含有店铺信息 id
    @Test
    public void queryProductCount() {
        Product p = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        p.setShop(shop);
       int x=dao.queryProductCount(p);
        System.out.println(x);
    }

    @Test
    public void queryProductById(){
        Product product = dao.queryProductById(2);
        System.out.println(product);
    }

    //插入商品，必须要含有  商品名+优先级+积分+店铺信息（店铺id号）+状态号+商品创建更新时间
    @Test
    public void insertProduct(){
        Product product=new Product();
        Shop shop=new Shop();
        shop.setShopId(1L);
        product.setShop(shop);
        product.setEnableStatus(1);
        product.setProductName("杨枝甘露");
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setPriority(10);
        product.setPoint(10);

        int i = dao.insertProduct(product);
    }
    //更新商品 必须要含有店铺信息+指定的商品id
    @Test
    public void updateProduct(){
        Product product=new Product();
        Shop shop=new Shop();
        shop.setShopId(1L);
        product.setProductId(7L);
        product.setShop(shop);
        product.setEnableStatus(1);
        product.setProductName("甘露");
        product.setLastEditTime(new Date());
        product.setPriority(10);
        product.setPoint(20);

        int i = dao.updateProduct(product);
    }
}
