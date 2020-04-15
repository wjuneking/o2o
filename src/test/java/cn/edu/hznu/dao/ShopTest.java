package cn.edu.hznu.dao;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.Area;
import cn.edu.hznu.domain.PersonInfo;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.domain.ShopCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
public class ShopTest  extends BaseTest {
    @Autowired
    private IShopDao shopDao;

    @Before
    public void init(){
        System.out.println("Dao层执行-------------------------------------------------------------------");
    }

    @After
    public  void destory(){
        System.out.println("Dao层结束-------------------------------------------------------------------");
    }
    //测试查询所有area，按照权重排序
    @Test
    public void TestinsertShop(){
        Shop shop=new Shop();
        PersonInfo person=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();

        person.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(person);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("一点点");
        shop.setShopDesc("奶茶");
        shop.setShopAddr("etse");
        shop.setPhone("123");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int res=shopDao.insertShop(shop);
        System.out.println("res:"+res);
    }

    @Test
    public void TestupdateShop(){
        Shop shop=new Shop();
        PersonInfo person=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();

        person.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setShopId(1L);
        shop.setOwner(person);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("一点点");
        shop.setShopDesc("奶茶");
        shop.setShopAddr("测试");
        shop.setPhone("123");
        shop.setShopImg("测试");
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int res=shopDao.updateShop(shop);
        System.out.println("res:"+res);
    }

    @Test
    public void TestqueryShopbyid(){
        Shop shop=shopDao.queryById(1L);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
        System.out.println(shop.getShopCategory().getShopCategoryId());
        System.out.println(shop.getShopCategory().getShopCategoryName());

    }

    @Test
    public void TestqueryShopCount(){
        Shop shop=shopDao.queryById(1L);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
        System.out.println(shop.getShopCategory().getShopCategoryId());
        System.out.println(shop.getShopCategory().getShopCategoryName());

    }

    @Test
    public void TestqueryShopList(){
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        owner.setUserId(1L);
        shop.setOwner(owner);

        ShopCategory shopCategory=new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shop.setShopCategory(shopCategory);
        shop.setEnableStatus(1);
        shop.setShopName("商店");

        List<Shop>shops=
                shopDao.queryShopList(shop,0,3);
        System.out.println(shops.size());
        int count=shopDao.queryShopCount(shop);
        System.out.println(count);
    }
}