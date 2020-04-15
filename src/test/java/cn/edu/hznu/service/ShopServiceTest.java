package cn.edu.hznu.service;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.Area;
import cn.edu.hznu.domain.PersonInfo;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.domain.ShopCategory;
import cn.edu.hznu.dto.ShopExecution;
import cn.edu.hznu.enums.ShopStateEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    private IShopService shopService;
    @Before
    public void init(){
        System.out.println("service层执行-------------------------------------------------------------------");
    }

    @After
    public  void destory(){
        System.out.println("service层结束-------------------------------------------------------------------");
    }
    //测试查询所有area，按照权重排序
    @Test
    public void Testsave() throws FileNotFoundException {
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
        shop.setShopName("三点点");
        shop.setShopDesc("奶茶");
        shop.setShopAddr("test3");
        shop.setPhone("123");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File img=new File("C:\\Users\\wjj\\Desktop\\Image\\eva\\Asuka\\asuka.jpg");
        InputStream in=new FileInputStream(img);
        ShopExecution shopExecution =shopService.addShop(shop,in,img.getName());
        System.out.println("res:"+shopExecution.getState());
    }
    @Test
    public void Testupdate() throws Exception{
        Shop shop=shopService.queryById(1L);
        shop.setShopName("修改后商店");
        File img=new File("C:\\Users\\wjj\\Desktop\\Image\\eva\\Asuka\\as.jpg");
        InputStream in=new FileInputStream(img);
        ShopExecution shopExecution =shopService.updateShop(shop,in,img.getName());
        System.out.println("res:"+shopExecution);
    }

    @Test
    public void getShopList() throws Exception{
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        owner.setUserId(1L);
        shop.setOwner(owner);

        ShopCategory shopCategory=new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shop.setShopCategory(shopCategory);

        ShopExecution se=shopService.getShopList(shop,3,3);
        System.out.println(se.getState());
        System.out.println(se.getCount());
        List<Shop>lists=se.getShops();
        for (Shop shop1:lists)
            System.out.println(shop1);
    }
}
