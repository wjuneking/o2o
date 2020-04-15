package cn.edu.hznu.dao;

import cn.edu.hznu.domain.ProductImg;

import java.util.List;

/**
 * Created by wjj on 2020/4/15
 */
public interface IProductImgDao {
    /*
    * 返回所有照片
    * */
    List<ProductImg> queryProductImgList(Long productId);

    /*
    * 批量插入照片
    * */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /*
    * 删除照片
    * */
    int deleteProductImg(Long productId);
}
