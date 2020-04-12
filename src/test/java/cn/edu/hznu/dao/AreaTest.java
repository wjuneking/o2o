package cn.edu.hznu.dao;


import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.Area;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
public class AreaTest extends BaseTest {
    @Autowired
    private IAreaDao areaDao;

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
    public void TestfindAll(){
        List<Area>areas=areaDao.findAllArea();
        for (Area area:areas)
            System.out.println(area);
    }
}
