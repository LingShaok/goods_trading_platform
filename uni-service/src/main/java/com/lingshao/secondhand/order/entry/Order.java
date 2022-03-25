package com.lingshao.secondhand.order.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 订单实体
 * @author LingShao
 * @date 2021/4/28
 */

@Data
public class Order implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Integer Id;
    @ApiModelProperty(value = "订单号")
    private String orderNumber;
    @ApiModelProperty(value = "货物id")
    private String goodsId;
    private String goodsName;
    @ApiModelProperty(value = "卖家id")
    private String sellerId;
    @ApiModelProperty(value = "买家id")
    private String buyerId;
    @ApiModelProperty(value = "交易方式")
    private String Transaction;
    @ApiModelProperty(value = "数量")
    private String Number;
    @ApiModelProperty(value = "买家是否确认结束")
    private String isPayBuyer;
    @ApiModelProperty(value = "卖家是否确认结束")
    private String isPaySeller;
    @ApiModelProperty(value = "买家取消")
    private String isCancelBuyer;
    @ApiModelProperty(value = "卖家取消")
    private String isCancelSeller;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "修改时间")
    private String modifiedTime;

}
