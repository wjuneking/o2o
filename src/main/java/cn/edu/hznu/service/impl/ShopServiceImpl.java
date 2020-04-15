package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IShopDao;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.dto.ShopExecution;
import cn.edu.hznu.enums.ShopStateEnum;
import cn.edu.hznu.exceptions.ShopOperationException;
import cn.edu.hznu.service.IShopService;
import cn.edu.hznu.util.ImgUtil;
import cn.edu.hznu.util.PageCalculator;
import cn.edu.hznu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream imgInputStream, String fileName) {
        //空值判断
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //店铺审核中
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //调用dao添加
            int res=shopDao.insertShop(shop);
            if(res<=0){
                throw new RuntimeException("addshop error");
            }else{
                if(imgInputStream !=null) {
                    //存储图片
                    try {
                        addShopImg(shop, imgInputStream,fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ShopOperationException("addshop error"+e.getMessage());
                    }
                    //更新图片地址
                    res=shopDao.updateShop(shop);
                    if(res<=0)
                        throw new ShopOperationException("addshop error");
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addshop error"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, InputStream imgInputStream, String fileName) {
        //获取img目录相对值路径
        String dest= PathUtil.getShopImagePath(shop.getShopId());

        String shopimgaddr=ImgUtil.generateThumbnail(imgInputStream,fileName,dest);
        shop.setShopImg(shopimgaddr);
    }

    @Override
    public Shop queryById(long shopId) {
        return shopDao.queryById(shopId);
    }

    //更新店铺信息
    @Override
    public ShopExecution updateShop(Shop shop, InputStream imginputStream, String fileName) {

        try {
            if(shop==null||shop.getShopId()==null){
                return new ShopExecution(ShopStateEnum.NULL_SHOP);
            }
            //判断是否需要修改图片
            if(imginputStream!=null){
                Shop shoptemp=shopDao.queryById(shop.getShopId());
                if(shoptemp.getShopImg()!=null){//
                    ImgUtil.deleteFileOrPath(shoptemp.getShopImg());
                }
                addShopImg(shop,imginputStream,fileName);
            }
            //更新店铺信息
            shop.setLastEditTime(new Date());
            int res=shopDao.updateShop(shop);
            if(res<=0){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }
            return new ShopExecution(ShopStateEnum.SUCCESS,shopDao.queryById(shop.getShopId()));
        } catch (Exception e) {
            throw new ShopOperationException("修改店铺信息出错"+e.getMessage());
        }
    }

    //获取条件下的列表
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageindex, int pagesize) {
        int rowIndex=0;
        int count;
        ShopExecution res=null;
        try {
            rowIndex= PageCalculator.calculateRow(pageindex,pagesize);
            List<Shop>shopList=shopDao.queryShopList(shopCondition,rowIndex,pagesize);
            count=shopDao.queryShopCount(shopCondition);
            res=new ShopExecution();
            res.setCount(count);
            res.setShops(shopList);
            res.setState(ShopStateEnum.SUCCESS.getState());
        } catch (Exception e) {
            res.setState(ShopStateEnum.INNER_ERROR.getState());

        }

        return res;
    }
}
