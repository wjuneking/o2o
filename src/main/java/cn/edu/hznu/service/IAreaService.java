package cn.edu.hznu.service;

import cn.edu.hznu.domain.Area;

import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
public interface IAreaService {
    //查询所有的地域
    List<Area>findAllArea();
}
