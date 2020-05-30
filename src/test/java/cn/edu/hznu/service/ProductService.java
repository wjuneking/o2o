package cn.edu.hznu.service;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.dao.IProductDao;
import cn.edu.hznu.domain.Product;
import cn.edu.hznu.domain.ProductCategory;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.dto.ImageHolder;
import cn.edu.hznu.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjj on 2020/5/30
 */
public class ProductService extends BaseTest {

    @Autowired
    private IProductService productService;

    @Test
    public void TestInsertProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(20L);
        product.setShop(shop);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(12L);
        product.setProductCategory(productCategory);
        product.setProductName("测试插入商品");
        product.setProductDesc("测试插入商品");
        product.setPriority(20);
        product.setPoint(20);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        File thum=new File("C:\\Users\\wjj\\Desktop\\Image\\奶茶1.jpg");
        InputStream is=new FileInputStream(thum);
        ImageHolder thumail = new ImageHolder(thum.getName(),is);

        File img1=new File("C:\\Users\\wjj\\Desktop\\Image\\奶茶1.jpg");
        ImageHolder i1 = new ImageHolder(thum.getName(),new FileInputStream(thum));
        File img2=new File("C:\\Users\\wjj\\Desktop\\Image\\奶茶1.jpg");
        ImageHolder i2 = new ImageHolder(thum.getName(),new FileInputStream(thum));
        List<ImageHolder>imgs = new ArrayList<>();
        imgs.add(i1);
        imgs.add(i2);
        productService.insertProduct(product,thumail,imgs);
    }
}
