package cn.edu.hznu.enums;

/**
 * Created by wjj on 2020/5/30
 */
public enum  ProductStateEnum {
    SUCCESS(1,"操作成功"),
    NULL_Product(-2,"商品信息为空"),
    INNER_ERROR(-1,"操作失败");
    private int state;
    private String stateInfo;

    ProductStateEnum(int state, String stateInfo) {
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
}
