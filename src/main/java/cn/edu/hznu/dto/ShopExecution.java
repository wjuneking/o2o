package cn.edu.hznu.dto;

import cn.edu.hznu.domain.Shop;
import cn.edu.hznu.enums.ShopStateEnum;

import java.util.List;

/**
 * Created by wjj on 2020/4/11
 */
public class ShopExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //店铺数量
    private int count;

    //操作的shop()
    private Shop shop;

    //商店列表
    private List<Shop>shops;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public ShopExecution(int state, String stateInfo, int count, Shop shop, List<Shop> shops) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.count = count;
        this.shop = shop;
        this.shops = shops;
    }
    //操作失败的构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }
    //操作成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.shop=shop;
    }
    //操作成功的构造器（返回一个列表）
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shops) {
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.shops=shops;
        this.count=shops.size();
    }
}
