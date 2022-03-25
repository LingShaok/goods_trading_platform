package com.lingshao.secondhand.commodity.dao;

import com.lingshao.secondhand.commodity.entry.Commodity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LingShao
 * @description 商品持久层
 * @date 2021/4/27
 */
@Repository
public interface CommodityDao {
    /**
     * @param conditionStr 搜索条件
     * @return 商品集合
     * @description 获取商品信息(可加搜索条件)
     * @author LingShao
     * @date 2021/4/27
     */

    List<Commodity> getCommodity(@Param("condition") String conditionStr);

    /**
     * @param commodityId 商品id
     * @return
     * @description 获取商品详细信息
     * @author LingShao
     * @date 2021/4/27
     */
    Commodity getDetailedCommodity(@Param("commodityId") String commodityId);

    /**
     * @param stuNumber 学号
     * @return
     * @description 获取个人所发布的所有商品
     * @author LingShao
     * @date 2021/4/29
     */
    List<Commodity> getMyCommodity(@Param("stuNumber") String stuNumber);

    /**
     * @param userNumber 用户学号
     * @param commodity  货物信息
     * @description 上传货物信息
     * @author LingShao
     * @date 2021/4/28
     */
    Integer addCommodity(@Param("userNumber") String userNumber, @Param("commodity") Commodity commodity);

    /**
     * @param @param null:
     * @return
     * @description 修改商品信息
     * @author LingShao
     * @date 2021/5/2
     */
    Integer updateCommodity(@Param("userNumber") String userNumber, @Param("commodity") Commodity commodity);

    /**
     * @param @param null:
     * @return
     * @description 删除商品
     * @author LingShao
     * @date 2021/5/2
     */
    Integer deleteCommodity(@Param("userNumber") String userNumber, @Param("commodityId") String commodityId);

    /**
     * @param @param null:
     * @return
     * @description 批量删除
     * @author LingShao
     * @date 2021/5/2
     */
    Integer deleteLotCommodity(@Param("userNumber") String userNumber, @Param("commodityIdList") List<String> commodityId);

    /**
     * @return
     * @description 管理员删除商品
     * @author LingShao
     * @date 2021/4/29
     */
    Integer adminDeleteCommodity(@Param("commodityId") String commodityId);

    /**
     * @description 修改商品数量
     * @author LingShao
     * @date 2021/5/3
     */
    Integer updateCommodityNumber(@Param("commodityId") String commodityId, @Param("Number") Integer Number);
}
