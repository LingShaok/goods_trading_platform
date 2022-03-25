package com.lingshao.secondhand.commodity.entry;

import com.lingshao.secondhand.baseinfo.entry.Seller;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @description the commodity entry
 * @author LingShao
 * @date 2021/4/27
 */

@Data
public class Commodity implements Serializable {
    @ApiModelProperty(value = "商品编号")
    private String commodityId;
    @ApiModelProperty(value = "商品名称")
    private String commodityName;
    @ApiModelProperty(value = "商品价格")
    private String Price;
    @ApiModelProperty(value = "商品描述")
    private String Describe;
    @ApiModelProperty(value = "库存")
    private String Number;
    @ApiModelProperty(value = "商品照片地址")
    private String commodityPic;
    @ApiModelProperty(value = "交易方式")
    private String Transaction;
    @ApiModelProperty(value = "卖家信息")
    private Seller sellerInfo;
}
