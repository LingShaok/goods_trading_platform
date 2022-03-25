package com.lingshao.secondhand.commodity.service;

import com.lingshao.secondhand.commodity.entry.Commodity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommodityService {

    /**
     * @param conditionStr 搜索条件
     * @description 获取商品详细信息
     * @author LingShao
     * @date 2021/4/27
     */
    List<Commodity> getCommodity(String conditionStr);

    /**
     * @param commodityId 商品id
     * @description 获取单个商品信息
     * @author LingShao
     * @date 2021/4/27
     */
    Commodity getOneCommodity(String commodityId);

    /**
     * @description 获取个人所发布的商品
     * @author LingShao
     * @date 2021/4/29
     * @param @param null:
     * @return
     */
    List<Commodity> getMyCommodity(String stuNumber);

    /**
     * @param Commodity 商品信息
     * @param stuNumber 用户学号
     * @description 上架或修改商品
     * @author LingShao
     * @date 2021/4/28
     */
    Integer addOrUpdateCommodity(String stuNumber, Commodity Commodity) throws IOException;

    /**
     * @description 删除个人商品
     * @author LingShao
     * @date 2021/5/2
     * @param @param null:
     * @return
     */
    Integer deleteMyCommodity(String stuNumber,String commodityId);

    /**
     * @description 批量删除个人商品
     * @author LingShao
     * @date 2021/5/2
     * @param @param null:
     * @return
     */
    Integer deleteLotMyCommodity(String stuNumber,String commodityId);

    /**
     * @description 管理员删除商品
     * @author LingShao
     * @date 2021/5/11
     * @param commodityId
     */
    Integer adminDeleteCommodity(String commodityId);
}
