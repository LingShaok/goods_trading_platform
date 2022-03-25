package com.lingshao.secondhand.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    @ApiModelProperty(value = "唯一主键")
    private Integer Id;
    @ApiModelProperty(value = "学号")
    private String userNumber;
    @ApiModelProperty(value = "密码")
    private String userPwd;
    @ApiModelProperty(value = "姓名")
    private String studentName;
    @ApiModelProperty(value = "昵称")
    private String userName;
    @ApiModelProperty(value = "头像路径")
    private String Avatar;
    @ApiModelProperty(value = "手机号")
    private String Phone;
    @ApiModelProperty(value = "邮箱地址")
    private String Email;
    @ApiModelProperty(value = "居住地址")
    private String Address;
    private String isValid;

}
