package cn.edu.hznu.dao;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wjj on 2020/5/30
 */
public class ProductImgTest extends BaseTest {

    @Autowired
    private IProductImgDao imgdao;

    @Test
    public void TestqueryImg(){
        List<ProductImg> productImgs = imgdao.queryProductImgList((long) 12);
        for (ProductImg productImg : productImgs) {
            System.out.println(productImg.getImgAddr());

        }
    }

    @Test
    public void deleteimg(){
        int i = imgdao.deleteProductImg((long)13);
    }
}
