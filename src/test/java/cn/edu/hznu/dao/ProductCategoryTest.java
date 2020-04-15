package cn.edu.hznu.dao;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.ProductCategory;
import cn.edu.hznu.domain.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wjj on 2020/4/14
 */
public class ProductCategoryTest extends BaseTest {
    @Autowired
    private IProductCategoryDao productCategoryDao;
    @Test
    public void TestindAllProductCategory(){
        List<ProductCategory>lists=productCategoryDao.findAllProductCategory(1L);
        for(ProductCategory list:lists)
            System.out.println(list.getProductCategoryName());
    }


    @Test
    public void TestbatchAddProductCategory(){
        List<ProductCategory>lists=new ArrayList<>();
        for (int i = 1; i <3 ; i++) {
            ProductCategory productCategory=new ProductCategory();
            productCategory.setCreateTime(new Date());
            productCategory.setProductCategoryName("测试批量添加"+0);
            productCategory.setPriority(0);
            productCategory.setShopId(1L);
            lists.add(productCategory);
        }

        int res=productCategoryDao.batchAddProductCategory(lists);
        System.out.println(res);
    }

    @Test
    public void TestDelete(){
      productCategoryDao.deleteProductCategory(1,1);
    }
}
