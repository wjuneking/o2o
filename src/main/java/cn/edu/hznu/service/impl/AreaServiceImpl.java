package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IAreaDao;
import cn.edu.hznu.domain.Area;
import cn.edu.hznu.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
@Service
public class AreaServiceImpl implements IAreaService {
    @Autowired
    private IAreaDao areaDao;

    public List<Area> findAllArea() {
        return areaDao.findAllArea();
    }
}
