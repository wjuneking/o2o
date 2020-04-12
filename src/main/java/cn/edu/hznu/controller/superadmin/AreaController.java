package cn.edu.hznu.controller.superadmin;

import cn.edu.hznu.domain.Area;
import cn.edu.hznu.service.IAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wjj on 2020/4/11
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger= LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private IAreaService areaService;
    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listArea(){
        logger.info("===start===");
        long starttime=System.currentTimeMillis();
        Map<String,Object>modelMap=new HashMap<String, Object>();
        List<Area>list=new ArrayList<Area>();
        try {
            list=areaService.findAllArea();
            modelMap.put("rows",list);
            modelMap.put("total",list.size());
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        logger.error("Testerror");
        long endtime=System.currentTimeMillis();
        logger.debug("costtime:[{}ms]",endtime-starttime);
        logger.info("===end===");
        return modelMap;
    }


}
