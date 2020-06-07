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
    public String index() {
        return "html/shop/index";
    }

    @RequestMapping("/shoplist")
    public String shoplist() {
        return "html/shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    public String shopmanagement() {
        return "html/shop/shopmanagement";
    }

    @RequestMapping("/frame")
    public String frame() {
        return "html/shop/frame";
    }

    @RequestMapping("/mine")
    public String mine() {
        return "html/shop/mine";
    }

    @RequestMapping("/preview")
    public String preview() {
        return "html/shop/preview";
    }

    @RequestMapping("/register")
    public String register() {
        return "html/shop/register";
    }

    @RequestMapping("/home")
    public String home() {
        return "html/shop/home";
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
