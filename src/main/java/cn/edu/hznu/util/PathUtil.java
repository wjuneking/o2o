package cn.edu.hznu.util;

/**
 * Created by wjj on 2020/4/11
 */
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "C:/Users/wjj/Desktop";
        } else {
            basePath = "/www/wwwroot/wjj";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImagePath(long shopId) {
        String imagePath = "/image/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }
}
