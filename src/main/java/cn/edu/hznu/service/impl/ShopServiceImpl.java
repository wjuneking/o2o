package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IShopDao;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.dto.ShopExecution;
import cn.edu.hznu.enums.ShopStateEnum;
import cn.edu.hznu.exceptions.ShopOperationException;
import cn.edu.hznu.service.IShopService;
import cn.edu.hznu.util.ImgUtil;
import cn.edu.hznu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * Created by wjj on 2020/4/11
 */
@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File img) {
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
                if(img!=null) {
                    //存储图片
                    try {
                        addShopImg(shop, img);
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

    private void addShopImg(Shop shop, File img) {
        //获取img目录相对值路径
        String dest= PathUtil.getShopImagePath(shop.getShopId());

        String shopimgaddr=ImgUtil.generateThumbnail(img,dest);
        shop.setShopImg(shopimgaddr);
    }
}
