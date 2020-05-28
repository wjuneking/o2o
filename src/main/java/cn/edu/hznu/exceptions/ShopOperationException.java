package cn.edu.hznu.exceptions;

/**
 * Created by wjj on 2020/4/11
 */
public class ShopOperationException extends RuntimeException{
    public ShopOperationException(String msg){
        super(msg);
    }
}
