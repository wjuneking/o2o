package cn.edu.hznu.controller.shopadmin;

import cn.edu.hznu.domain.ProductCategory;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.dto.ProductCategoryExecution;
import cn.edu.hznu.enums.ProductCategoryEnum;
import cn.edu.hznu.service.impl.ProductCategoryServiceImpl;
import cn.edu.hznu.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wjj on 2020/4/14
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @RequestMapping("/getproductcategorylist")
    @ResponseBody
    private Map<String, Object> getproductcategorylist(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long shopId= HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId<=0){
            Object obj=request.getSession().getAttribute("currentShop");
            if(obj==null){
                modelMap.put("redirect",true);
                modelMap.put("url","shoplist");
                return modelMap;
            } else {
                Shop currentShop=(Shop)obj;
                modelMap.put("redirect",false);
                shopId=currentShop.getShopId();
                modelMap.put("ShopId",shopId);
            }
        } else {
            Shop currentShop=new Shop();
            currentShop.setShopId(shopId);
            modelMap.put("redirect",false);
            request.getSession().setAttribute("currentShop",currentShop);
        }
        List<ProductCategory>lists=productCategoryService.findAllProductCategory(shopId);
        modelMap.put("success",true);
        modelMap.put("productlist",lists);
        return modelMap;
    }

    @RequestMapping("/batchAddProductCategory")
    @ResponseBody
    private Map<String, Object> batchAddProductCategory(HttpServletRequest request,@RequestBody List<ProductCategory> lists){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Shop current=(Shop)request.getSession().getAttribute("currentShop");
        for(ProductCategory productCategory:lists){
            productCategory.setShopId(current.getShopId());
            productCategory.setCreateTime(new Date());
        }
        if(lists!=null&&lists.size()>0){
            try {
                ProductCategoryExecution pr=productCategoryService.batchAddProductCategory(lists);
                if(pr.getState()== ProductCategoryEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                } else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pr.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","商品类别为空");
        }
        return modelMap;
    }

    @RequestMapping("/deleteProductCategory")
    @ResponseBody
    private Map<String, Object> deleteProductCategory(HttpServletRequest request,Long productCategoryId){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Shop current=(Shop)request.getSession().getAttribute("currentShop");
        if(productCategoryId!=null&&productCategoryId>0){
            try {
                ProductCategoryExecution pr=productCategoryService.deleteProductCategory(productCategoryId,current.getShopId());
                if(pr.getState()== ProductCategoryEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                } else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pr.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请选择一个商品类别");
        }
        return modelMap;

    }
}
