package cn.edu.hznu.controller.shopadmin;

import cn.edu.hznu.domain.Product;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.service.IProductService;
import cn.edu.hznu.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wjj on 2020/5/28
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
    @Autowired
    private IProductService productService;

    //使用该方法获取商品列表
    @RequestMapping("/getproductlistbyshop")
    @ResponseBody
    private Map<String, Object> getproductlist(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //var listUrl = '/o2o/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999';
        int pageindex= HttpServletRequestUtil.getInt(request,"pageIndex");
        int pagesize= HttpServletRequestUtil.getInt(request,"pageSize");
        Shop shop=(Shop) request.getSession().getAttribute("currentShop");
        Product productcon=new Product();
        productcon.setShop(shop);
        try {
            List<Product> products = productService.queryProductList(productcon, (pageindex - 1) * pagesize, pagesize);
            modelMap.put("success",true);
            modelMap.put("productList",products);
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }


    //使用该方法获取商品列表
    @RequestMapping("/modifyproduct")
    @ResponseBody
    private Map<String, Object> modifyproduct(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int id=HttpServletRequestUtil.getInt(request,"productId");
        if(id<0){
            modelMap.put("success",false);
            modelMap.put("errMsg","没有该属性的值");
        } else {
            Product product = productService.queryProductById(id);
            product.setEnableStatus(product.getEnableStatus()==1?0:1);
            productService.updateProduct(product);
            modelMap.put("success",true);
        }
        return modelMap;
    }
}
