package cn.edu.hznu.dao;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.domain.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wjj on 2020/4/12
 */
public class ShopCategoryTest extends BaseTest {
    @Autowired
    private IShopCategoryDao categoryDao;

    @Test
    public void testfindAll(){
        List<ShopCategory>shopCategories=
        categoryDao.findAllShopCategory(new ShopCategory());
        System.out.println("size:"+shopCategories.size());
    }

    @Test
    public void testfindByParent(){
        ShopCategory son=new ShopCategory();
        ShopCategory parent=new ShopCategory();
        parent.setShopCategoryId(1L);
        son.setParent(parent);
        List<ShopCategory>shopCategories=
                categoryDao.findAllShopCategory(son);
        System.out.println("size:"+shopCategories.get(0).getShopCategoryName());
    }
}
