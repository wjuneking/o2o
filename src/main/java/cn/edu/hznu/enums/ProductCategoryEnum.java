package cn.edu.hznu.enums;

/**
 * Created by wjj on 2020/4/14
 */
public enum  ProductCategoryEnum {
    SUCCESS(1,"操作成功"),
    NULL_SHOP(-2,"list信息为空"),
    INNER_ERROR(-1,"操作失败");
    private int state;
    private String stateInfo;

    private ProductCategoryEnum(int state,String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
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
