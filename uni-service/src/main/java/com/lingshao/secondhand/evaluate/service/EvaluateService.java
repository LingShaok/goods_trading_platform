package com.lingshao.secondhand.evaluate.service;


import com.lingshao.secondhand.evaluate.entity.Evaluate;

import java.util.List;

public interface EvaluateService {

    /**
     * @param sellerId 卖家id
     * @description 通过卖家id查看评论
     * @author LingShao
     * @date 2021/5/10
     */
    List<Evaluate> getEvaluateBySellerId(String sellerId);

    /**
     * @param buyerId 买家id
     * @return
     * @description 通过买家id获取评论
     * @author LingShao
     * @date 2021/5/10
     */
    List<Evaluate> getEvaluateByBuyerId(String buyerId);

    /**
     * @param evaluate 评论实体
     * @description 添加评论
     * @author LingShao
     * @date 2021/5/10
     */
    Integer addEvaluate(String userNumber, Evaluate evaluate);

    /**
     * @param buyerId
     * @description 买家删除评论
     * @author LingShao
     * @date 2021/5/10
     */
    Integer delEvaluate(String evaluateId, String buyerId);

    /**
     * @description 是否评论
     * @author LingShao
     * @date 2021/5/16
     * @param @param null:
     * @return
     */
    Integer isEvaluate(String userNumber,String order);

}
