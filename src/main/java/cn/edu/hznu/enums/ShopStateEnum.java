package cn.edu.hznu.enums;

/**
 * Created by wjj on 2020/4/11
 */
public enum  ShopStateEnum {
    CHECK(0,"审核中"),
    OFFLINE(-1,"下线"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    NULL_SHOP(-2,"shop信息为空"),
    INNER_ERROR(-1001,"系统错误");
    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
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

    public static ShopStateEnum stateof(int state){
        for (ShopStateEnum shopStateEnum:values()){
            if(shopStateEnum.getState()==state)
                return shopStateEnum;
        }
        return null;
   }
}
