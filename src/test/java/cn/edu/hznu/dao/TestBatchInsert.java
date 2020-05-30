package cn.edu.hznu.dao;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wjj on 2020/5/30
 */
public class TestBatchInsert  extends BaseTest {
    @Autowired
    private IProductImgDao productImgDao;

    @Test
    public void testInsertImg(){
        ProductImg productImg=new ProductImg();
        productImg.setImgAddr("test1");
        productImg.setImgDesc("test1");
        productImg.setCreateTime(new Date());
        productImg.setProductId(7L);
        ProductImg productImg2=new ProductImg();
        productImg2.setImgAddr("test2");
        productImg2.setImgDesc("test2");
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(7L);
        List<ProductImg> lists = new ArrayList<>();
        lists.add(productImg);
        lists.add(productImg2);

        productImgDao.batchInsertProductImg(lists);
    }
}
