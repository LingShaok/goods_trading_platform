package com.lingshao.secondhand.baseinfo.service.impl;

import com.lingshao.secondhand.baseinfo.service.PictureService;
import com.lingshao.secondhand.user.dao.UserDao;
import com.lingshao.util.PictureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.lingshao.entity.PublicResultConstant.PIC_IMG_ERROR;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${pictureDir}")
    private String pictureDir;

    @Resource
    private UserDao userDao;
    /**
     * @param inputPath：图片路径
     * @return true:合法，false:不合法
     * @description 判断图片是否合法（高度是否超过指定高度）
     * @author LingShao
     * @date 2021/3/19
     */
    @Override
    public String picIsLeg(String suNumber,String inputPath) throws Exception {
        Boolean flag = PictureUtil.picHisLegal(inputPath, BigDecimal.valueOf(300));
        if (flag == true) {
            userDao.updateAvatar(suNumber,inputPath.replace(pictureDir,""));
            return inputPath.replace(pictureDir,"");
        } else {
            BigDecimal Scale = PictureUtil.scale(BigDecimal.valueOf(300),PictureUtil.picGetHight(inputPath) );
            if (PictureUtil.narrowPic(inputPath, Scale)) {
                userDao.updateAvatar(suNumber,inputPath.replace(pictureDir,""));
                return inputPath.replace(pictureDir,"");
            } else {
                PictureUtil.deletePic(inputPath);
                return PIC_IMG_ERROR;
            }
        }
    }
}
