package cn.edu.hznu.dto;

import cn.edu.hznu.domain.ProductCategory;
import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.enums.ProductCategoryEnum;

import java.util.List;

/**
 * Created by wjj on 2020/4/14
 */
public class ProductCategoryExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //类别列表
    private List<ProductCategory> categories;

    //操作失败返回类型
    public ProductCategoryExecution(ProductCategoryEnum productCategoryEnum) {
        this.state = productCategoryEnum.getState();
        this.stateInfo = productCategoryEnum.getStateInfo();
    }

    //操作成功的返回类型，加入返回列表
    public ProductCategoryExecution(ProductCategoryEnum productCategoryEnum, List<ProductCategory> categories) {
        this.state = productCategoryEnum.getState();
        this.stateInfo = productCategoryEnum.getStateInfo();
        this.categories = categories;
    }

    public ProductCategoryExecution() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }
}
