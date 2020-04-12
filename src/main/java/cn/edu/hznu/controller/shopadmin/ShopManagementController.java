package cn.edu.hznu.controller.shopadmin;

import cn.edu.hznu.domain.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * Created by wjj on 2020/4/12
 */
@Controller
@RequestMapping("/Shopadmin")
public class ShopManagementController {
    @RequestMapping(value = "/registerShop",method = RequestMethod.POST)

}
