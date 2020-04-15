package cn.edu.hznu.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wjj on 2020/4/12
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {
    @RequestMapping("/shopoperation")
    public String shopOperation(){
        return "html/shop/shopoperation";
    }

    @RequestMapping("/shoplist")
    public String shoplist(){
        return "html/shop/shoplist";
    }


    @RequestMapping("/shopmanagement")
    public String shopmanagement(){
        return "html/shop/shopmanagement";
    }


    @RequestMapping("/productcategorymanagement")
    public String productcategorymanagement(){
        return "html/shop/productcategorymanagement";
    }

}
