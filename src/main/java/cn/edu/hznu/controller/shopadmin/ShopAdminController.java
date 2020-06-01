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
    public String shopOperation() {
        return "html/shop/shopoperation";
    }

    @RequestMapping("/index")
    public String shoplist() {
        return "html/shop/index";
    }


    @RequestMapping("/shopmanagement")
    public String shopmanagement() {
        return "html/shop/shopmanagement";
    }


    @RequestMapping("/productcategorymanagement")
    public String productcategorymanagement() {
        return "html/shop/productcategorymanagement";
    }


    @RequestMapping("/productmanagement")
    public String productmanagement() {
        return "html/shop/productmanagement";
    }

    @RequestMapping("/productoperation")
    public String productoperation() {
        return "html/shop/productoperation";
    }


}
