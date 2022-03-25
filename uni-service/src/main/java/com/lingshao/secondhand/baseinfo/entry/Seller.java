package com.lingshao.secondhand.baseinfo.entry;

import com.lingshao.secondhand.evaluate.entity.Evaluate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author LingShao
 * @description 卖家信息
 * @date 2021/4/27
 */

@Data
public class Seller implements Serializable {
    @ApiModelProperty(value = "卖家id")
    private String sellerId;
    @ApiModelProperty(value = "卖家地址")
    private String sellerAddress;
    @ApiModelProperty(value = "卖家名字")
    private String sellerName;
    @ApiModelProperty(value = "卖家头像")
    private String sellerAvatar;
    @ApiModelProperty(value = "卖家评价")
    private List<Evaluate> sellerEvaluate;
    @ApiModelProperty(value = "售出次数")
    private String sellerCount;
    @ApiModelProperty(value = "退货次数")
    private String backCount;
}
