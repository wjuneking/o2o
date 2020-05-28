package cn.edu.hznu.controller.shopadmin;

import cn.edu.hznu.dao.IAreaDao;
import cn.edu.hznu.dao.IShopCategoryDao;
import cn.edu.hznu.domain.Area;
import cn.edu.hznu.domain.PersonInfo;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.domain.ShopCategory;
import cn.edu.hznu.dto.ShopExecution;
import cn.edu.hznu.enums.ShopStateEnum;
import cn.edu.hznu.exceptions.ShopOperationException;
import cn.edu.hznu.service.IAreaService;
import cn.edu.hznu.service.IShopCategoryService;
import cn.edu.hznu.service.IShopService;
import cn.edu.hznu.util.HttpServletRequestUtil;
import cn.edu.hznu.util.ImgUtil;
import cn.edu.hznu.util.codeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.ECMAException;
import jdk.nashorn.internal.runtime.UserAccessorProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wjj on 2020/4/12
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private IShopService shopService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IShopCategoryService categoryService;

    //使用该方法实现注册店铺
    @RequestMapping(value = "/registershop")
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //判断验证码是否正确
        if(codeUtil.checkVerifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("errMsg", "错误的验证码");
            return modelMap;
        }

        // 1.接收并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        // 2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            //PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    List<Shop>list=(List<Shop>)request.getSession().getAttribute("shopList");
                    if(list==null||list.size()==0){
                        list=new ArrayList<>();
                    }
                    list.add(se.getShop());
                    request.getSession().setAttribute("shopList",list);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "状态" + se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "状态" + e.toString());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

    //js中调用该方法，获取注册店铺时的初始化信息（区域list，商铺二级类别）
    @RequestMapping("/getshopinitinfo")
    @ResponseBody
    private Map<String,Object>getShopInitInfo(){
        Map<String,Object>modelMap=new HashMap<>();
        List<ShopCategory>shopCategories=null;
        List<Area>areas=null;
        try {
            shopCategories=categoryService.findAllShopCategory(new ShopCategory());
            areas=areaService.findAllArea();
            modelMap.put("shopCategories",shopCategories);
            modelMap.put("areas",areas);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    //使用该方法获取店铺信息
    @RequestMapping("/getshopbyid")
    @ResponseBody
    private Map<String,Object>getshopbyid(HttpServletRequest request){
        Map<String,Object>modelMap=new HashMap<>();
        Long shopId=HttpServletRequestUtil.getLong(request,"shopId");
        Shop res=null;
        if(shopId>0){
            res=shopService.queryById(shopId);
            modelMap.put("shop",res);
            modelMap.put("success",true);
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","没有该店铺信息");
        }
        return modelMap;
    }

    //使用该方法实现修改店铺
    @RequestMapping(value = "/modifyshop")
    @ResponseBody
    private Map<String, Object> modifyshop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //判断验证码是否正确
        if(codeUtil.checkVerifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("errMsg", "错误的验证码");
            return modelMap;
        }

        // 1.接收并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        // 2.修改店铺
        if (shop != null&&shop.getShopId()!=null) {
            //PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                if(shopImg==null)
                    se = shopService.updateShop(shop, null, null);
                else
                    se = shopService.updateShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "状态" + se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "状态" + e.toString());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺id");
            return modelMap;
        }
    }

    //获取店铺列表
    @RequestMapping("/getshoplist")
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        PersonInfo owner=new PersonInfo();
        owner.setUserId(1L);
        owner.setName("wjj");
        request.getSession().setAttribute("user",owner);
        owner=(PersonInfo)request.getSession().getAttribute("user");

        try {
            Shop shop=new Shop();
            shop.setOwner(owner);
            ShopExecution se=shopService.getShopList(shop,0,100);
            modelMap.put("shopList",se.getShops());
            modelMap.put("user",owner);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }

        return  modelMap;
    }

    //进入店铺管理界面
    @RequestMapping("/getshopmanagementinfo")
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        long shopId=HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId<=0){
            Object obj=request.getSession().getAttribute("currentShop");
            if(obj==null){
                modelMap.put("redirect",true);
                modelMap.put("url","shoplist");
            } else {
                Shop currentShop=(Shop)obj;
                modelMap.put("redirect",false);
                modelMap.put("ShopId",currentShop.getShopId());
            }
        }
        else{
            Shop currentShop=new Shop();
            currentShop.setShopId(shopId);
            modelMap.put("redirect",false);
            request.getSession().setAttribute("currentShop",currentShop);
        }
        return modelMap;
    }

}
