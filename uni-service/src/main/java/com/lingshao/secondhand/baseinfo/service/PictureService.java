package com.lingshao.secondhand.baseinfo.service;

public interface PictureService {
    /**
     * @description 判断头像上传是否合法
     * @param inputPath:绝对路径
     * @author LingShao
     * @date 2021/3/19
     */
    String picIsLeg(String stuNumber,String inputPath) throws Exception;
}
