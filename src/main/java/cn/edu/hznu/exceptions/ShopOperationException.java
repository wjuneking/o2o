package cn.edu.hznu.exceptions;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;

/**
 * Created by wjj on 2020/4/11
 */
public class ShopOperationException extends RuntimeException{
    public ShopOperationException(String msg){
        super(msg);
    }
}
