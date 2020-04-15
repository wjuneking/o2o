package cn.edu.hznu.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wjj on 2020/4/12
 */
public class codeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected=(String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual=HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if (verifyCodeActual.equals(verifyCodeExpected)||verifyCodeActual==null){
            return false;
        }
        return true;
    }
}
