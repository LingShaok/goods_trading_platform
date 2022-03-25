package com.lingshao.util;

import com.lingshao.entity.PublicResultConstant;
import net.coobird.thumbnailator.makers.ScaledThumbnailMaker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.channels.FileChannel;

public class PictureUtil {
    /**
     * @param path:图片路径
     * @param ruleH:规定高度
     * @return true:符合规定，false：不符合规定
     * @description: 判断图片高度是否符合规定高度
     * @author: LingShao
     * @date: 2021/3/19
     */
    public static Boolean picHisLegal(String path, BigDecimal ruleH) throws Exception {
        BigDecimal picH = picGetHight(path);
        if (picH.compareTo(ruleH) > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param path 路径
     * @description 获取图片高度
     * @author LingShao
     * @date 2021/3/23
     */
    public static BigDecimal picGetHight(String path) throws Exception {
        File file = new File(path);
        FileChannel fc = null;
        BufferedImage bi = null;
        FileInputStream fs = null;
        if (file.exists() && file.isFile()) {
            try {
                fs = new FileInputStream(file);
                fc = fs.getChannel();
                bi = ImageIO.read(file);
            } catch (IOException e) {
                throw new Exception(PublicResultConstant.PIC_IMG_ERROR);
            } finally {
                if (file.exists()) {
                    fc.close();
                    fs.close();
                }
            }
        }
        BigDecimal picH = new BigDecimal(BigInteger.valueOf(bi.getHeight()));
        return picH;
    }

    /**
     * @param Path  路径
     * @param Scale 比例
     * @return true/false
     * @description 按比例压缩图片
     * @author LingShao
     * @date 2021/3/23
     */
    public static Boolean narrowPic(String Path, BigDecimal Scale) throws IOException {
        //读取文件
        BufferedImage img = ImageIO.read(new File(Path));
        //按固定比例0.15压缩图片
        BufferedImage bi = new ScaledThumbnailMaker().scale(Scale.doubleValue()).imageType(BufferedImage.TYPE_3BYTE_BGR).make(img);
        //写压缩后图片            
        return ImageIO.write(bi, "jpg", new File(Path));
    }

    /**
     * @param b1 被除数
     * @param b2 除数
     * @description 获取比例 b1/b2，b1存规定尺寸,b2放图片的尺寸
     * @author LingShao
     * @date 2021/3/23
     */
    public static BigDecimal scale(BigDecimal b1, BigDecimal b2) {
        BigDecimal result = b1.divide(b2, 2);
        return result;
    }

    /**
     * @param path:指定路径
     * @return true:删除成功，false:删除失败
     * @description 删除指定文件
     * @author LingShao
     * @date 2021/3/19
     */
    public static Boolean deletePic(String path) {
        File file = new File(path);
        if (file != null) {
            file.delete();
            return true;
        } else {
            return false;
        }
    }
}