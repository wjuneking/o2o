package cn.edu.hznu.service;

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
    private IAreaService areaService;
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
    public void TestfindAll(){
        System.out.println("service");
        List<Area> areas=areaService.findAllArea();
        for (Area area:areas)
            System.out.println(area);
    }
}
