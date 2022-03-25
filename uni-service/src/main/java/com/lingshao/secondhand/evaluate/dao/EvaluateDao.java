package com.lingshao.secondhand.evaluate.dao;

import com.lingshao.secondhand.evaluate.entity.Evaluate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateDao {

    /**
     * @param sellerId 卖家id
     * @description 通过卖家id获取评论
     * @author LingShao
     * @date 2021/4/27
     */
    List<Evaluate> getEvaluateBySellerId(@Param("sellerId") String sellerId);

    /**
     * @description 通过买家id获取评论
     * @author LingShao
     * @date 2021/5/10
     * @param buyerId 买家id
     */
    List<Evaluate> getEvaluateByBuyerId(@Param("buyerId") String buyerId);

    /**
     * @description 发布评论
     * @author LingShao
     * @date 2021/5/10
     * @param @param null:
     * @return
     */
    Integer addEvaluate(@Param("Evaluate")Evaluate evaluate);

    /**
     * @description 买家删除评论
     * @author LingShao
     * @date 2021/5/10
     * @param evaluateId 评论id
     * @param buyerId 买家id
     */
    Integer delEvaluate(@Param("evaluateId") String evaluateId,@Param("buyerId") String buyerId);

    /**
     * @description 是否评论
     * @author LingShao
     * @date 2021/5/16
     * @param @param null:
     * @return
     */
    Integer isEvaluate(@Param("orderNumber") String orderNumber,@Param("userNumber") String userNumber);
}
