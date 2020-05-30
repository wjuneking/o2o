package cn.edu.hznu.dto;

import cn.edu.hznu.domain.Product;
import cn.edu.hznu.enums.ProductStateEnum;

import java.util.List;

/**
 * Created by wjj on 2020/5/30
 */
public class ProductExecution {

    //结果状态
    private ProductStateEnum state;

    //状态标识
    private String stateInfo;

    //商品数量
    private int count;

    //操作的product
    private Product product;

    //获取的product列表
    private List<Product>productList;

    public ProductExecution(ProductStateEnum state, Product product) {
        this.state = state;
        this.product = product;
    }

    public ProductExecution(ProductStateEnum state) {
        this.state = state;
    }

    public ProductStateEnum getState() {
        return state;
    }

    public void setState(ProductStateEnum state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
