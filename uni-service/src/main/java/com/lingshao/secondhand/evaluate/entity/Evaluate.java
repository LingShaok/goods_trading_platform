package com.lingshao.secondhand.evaluate.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 评论
 * @author LingShao
 * @date 2021/4/27
 */

@Data
public class Evaluate implements Serializable {
    @ApiModelProperty(value = "评论id")
    private String evaluateId;
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;
    private String sellerName;
    @ApiModelProperty(value = "卖家id")
    private String sellerId;
    private String buyerName;
    @ApiModelProperty(value = "买家id")
    private String buyerId;
    @ApiModelProperty(value = "交易速度")
    private String speed;
    @ApiModelProperty(value = "交易态度")
    private String attitude;
    @ApiModelProperty(value = "描述符合程度")
    private String accordance;
    @ApiModelProperty(value = "评论")
    private String describe;
    @ApiModelProperty(value = "创建时间")
    private String crateTime;
    @ApiModelProperty(value = "修改时间")
    private String modifiedTime;

}
