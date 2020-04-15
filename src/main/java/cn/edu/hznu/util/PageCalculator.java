package cn.edu.hznu.util;

/**
 * Created by wjj on 2020/4/13
 */
public class PageCalculator {

    public static int calculateRow(int pageIndex,int pageSize){
        return pageIndex>0?(pageIndex-1)*pageSize:0;
    }
}
