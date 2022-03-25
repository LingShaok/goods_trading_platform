package com.lingshao.secondhand.controller;

import com.lingshao.annotation.CurrentUser;
import com.lingshao.secondhand.baseinfo.service.PictureService;
import com.lingshao.secondhand.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.lingshao.annotation.Pass;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.util.UUID;

/**
 * @author LingShao
 * @description 图片上传
 * @date 2021/3/25
 */

@Slf4j
@Api(tags = "文件上传")
@RestController
@RequestMapping("/userBaseInfo")
public class FileController {
    @Autowired
    private PictureService pictureService;

    @Value("${pictureDir}")
    private String pictureDir;

    @Value("${goodsDir}")
    private String goodsDir;

    @Value("${avatarDir}")
    private String avatarDir;

    /**
     * @param Picture 头像
     * @description: 上传图片
     * @author: LingShao
     * @date: 2021/3/19
     * @return:
     */
    @ApiOperation(value = "上传头像")
    @PostMapping("/v1/uploadAvatarPic")
    public ResponseModel<String> Upload(@ApiIgnore() @CurrentUser User user,
                                        @RequestParam("picture") MultipartFile Picture) throws Exception {
        String picPath = pictureDir +avatarDir+ user.getUserNumber();
        File dirFile = new File(picPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        Picture.transferTo(new File(picPath + "\\avatar.jpg"));
        return ResponseHelper.buildResponseModel(pictureService.picIsLeg(user.getUserNumber(),picPath + "\\avatar.jpg"));
    }

    /**
     * @param Picture 商品图片
     * @description: 上传图片
     * @author: LingShao
     * @date: 2021/3/19
     * @return:
     */
    @ApiOperation(value = "上传商品图片")
    @PostMapping("/v1/uploadPicture")
    public ResponseModel<String> uploadPicture(@ApiIgnore() @CurrentUser User user,
                                               @RequestParam("picture")MultipartFile Picture) throws Exception {
        String picPath = pictureDir +goodsDir+ user.getUserNumber();
        File dirFile = new File(picPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        String fileName = "\\"+UUID.randomUUID().toString().replace("-","")+".jpg";
        Picture.transferTo(new File(picPath + fileName));
        String result = "\\"+goodsDir+user.getUserNumber()+fileName;
        return ResponseHelper.buildResponseModel(result);
    }
}
