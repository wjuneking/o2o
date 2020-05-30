package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IProductDao;
import cn.edu.hznu.dao.IProductImgDao;
import cn.edu.hznu.domain.Product;
import cn.edu.hznu.domain.ProductImg;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.dto.ImageHolder;
import cn.edu.hznu.dto.ProductExecution;
import cn.edu.hznu.enums.ProductStateEnum;
import cn.edu.hznu.service.IProductService;
import cn.edu.hznu.util.ImgUtil;
import cn.edu.hznu.util.PathUtil;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wjj on 2020/5/28
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IProductImgDao imgDao;

    @Override
    public List<Product> queryProductList(Product productCondition, int rowIndex, int pageSize) {
        return productDao.queryProductList(productCondition, rowIndex, pageSize);
    }

    @Override
    public int queryProductCount(Product productCondition) {
        return productDao.queryProductCount(productCondition);
    }

    @Override
    public Product queryProductById(long productId) {
        return productDao.queryProductById(productId);
    }

//    添加商品
    @Override
    @Transactional
    public ProductExecution insertProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imglist) throws RuntimeException{
//        1、处理缩略图，获取图片相对路径
//        2、向数据库中插入信息，返回id
//        3、结合id批量处理详情图
//        4、将详情图批量插入
        if(product!=null&&product.getShop()!=null){
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if(thumbnail!=null){
                addThumbnail(product,thumbnail);
            }
            int i = productDao.insertProduct(product);
            if(i<=0)
                throw new RuntimeException("插入失败");
            if(imglist!=null&&imglist.size()>0){
                addImgList(product,imglist);
            } else {
                throw new RuntimeException("缩略图为空");
            }
        } else {
            throw new RuntimeException("商品为空");
        }
        return new ProductExecution(ProductStateEnum.SUCCESS,product);
    }

    private void addImgList(Product product, List<ImageHolder> imglist) {
        //获取img目录相对值路径
        String dest= PathUtil.getShopImagePath(product.getShop().getShopId());
//        String productImgAddr= ImgUtil.generateThumbnail(thumbnail,dest);
//        product.setImgAddr(productImgAddr);

        List<ProductImg>imgs=new ArrayList<>();
        for (ImageHolder imageHolder : imglist) {
            String addr=ImgUtil.generateThumbnail(imageHolder,dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(addr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            imgs.add(productImg);
        }
        if(imgs.size()>0){
            try {
                int i = imgDao.batchInsertProductImg(imgs);
                if(i<=0)
                    throw new RuntimeException("创建图片失败");
            } catch (RuntimeException e) {
                throw new RuntimeException("创建图片失败"+e.toString());
            }
        }
    }

    private void addThumbnail(Product product, ImageHolder thumbnail) {
            //获取img目录相对值路径
            String dest= PathUtil.getShopImagePath(product.getShop().getShopId());
            String productImgAddr= ImgUtil.generateThumbnail(thumbnail,dest);
            product.setImgAddr(productImgAddr);
        }
//    更新商品
    @Override
    public ProductExecution updateProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imglist) {
        // 空值判断
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // 给商品设置上默认属性
            product.setLastEditTime(new Date());
            // 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null) {
                // 先获取一遍原有信息，因为原来的信息里有原图片地址
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImgUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            // 如果有新存入的商品详情图，则将原先的删除，并添加新的图片
            if (imglist != null && imglist.size() > 0) {
                deleteProductImgList(product.getProductId());
                addImgList(product, imglist);
            }
            try {
                // 更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new RuntimeException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new RuntimeException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.NULL_Product);
        }
    }

    /**
     * 删除某个商品下的所有详情图
     *
     * @param productId
     */
    private void deleteProductImgList(long productId) {
        // 根据productId获取原来的图片
        List<ProductImg> productImgList = imgDao.queryProductImgList(productId);
        // 干掉原来的图片
        for (ProductImg productImg : productImgList) {
            ImgUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        // 删除数据库里原有图片的信息
        imgDao.deleteProductImg(productId);
    }
}
