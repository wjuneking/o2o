package cn.edu.hznu.dto;

import java.io.InputStream;

/**
 * Created by wjj on 2020/5/30
 */
public class ImageHolder {
    private String imgname;
    private InputStream img;

    public ImageHolder(String imgname, InputStream img) {
        this.imgname = imgname;
        this.img = img;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public InputStream getImg() {
        return img;
    }

    public void setImg(InputStream img) {
        this.img = img;
    }
}
