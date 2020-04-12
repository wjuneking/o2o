package cn.edu.hznu.util;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 处理图像的工具类
 * Created by wjj on 2020/4/11
 */
public class ImgUtil {
    private static String basePath="C:/Users/wjj/IdeaProjects/o2o/src/main/resources";
    private static Logger logger= LoggerFactory.getLogger(ImgUtil.class);

    public static void main(String[] args) throws Exception {
        Thumbnails.of(new File("C:/Users/wjj/Desktop/Image/eva/Asuka/asuka.jpg")).scale(1f)
                .watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File("C:\\Users\\wjj\\IdeaProjects\\o2o\\src\\main\\resources\\watermark.jpg")),1f)
        .outputQuality(1f).toFile("C:/Users/wjj/Desktop/Image/eva/Asuka/asukanew.jpg");
    }

    public File transfer(CommonsMultipartFile cf) {
        File file= null;
        try {
            file = new File(cf.getOriginalFilename());
            cf.transferTo(file);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }

        return file;
    }

    public static String generateThumbnail(File thumbnail, String targetAddr) {
        // 获取不重复的随机名
        String realFileName = getRandomFileName();
        // 获取文件的扩展名如png,jpg等
        String extension = getFileExtension(thumbnail);
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        String path=PathUtil.getImgBasePath() + relativeAddr;
        File dest = new File(path);

        // 调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnail).size(200,200)
                    .watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File("C:\\Users\\wjj\\IdeaProjects\\o2o\\src\\main\\resources\\watermark.jpg")),1f)
                    .outputQuality(1f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        // 返回图片相对路径地址
        return relativeAddr;
    }

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
        File dirp=new File(realFileParentPath);
        if(!dirp.exists()){
            dirp.mkdir();
        }
    }

    //获取扩展名
    private static String getFileExtension(File thumbnail) {
        String s=thumbnail.getName();
        return s.substring(s.lastIndexOf("."));
    }

    private static String getRandomFileName() {
        return UUID.randomUUID().toString();
    }


}
