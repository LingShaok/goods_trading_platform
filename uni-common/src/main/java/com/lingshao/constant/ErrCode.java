package com.lingshao.constant;

/**
 * 常用error 返回值
 */
public final class ErrCode {
    public final static int SUCCESS = 200;// 请求成功
    public final static int FAIL = 201;// 请求失败
    public final static int LOGIN_ERROR = 202; // 登陆失败
    public final static int LOGIN_EXPIRE = 203;// 登陆过期
    public final static int ARGS_ERROR = 501; // 参数错误
    public final static int UNKOWN_ERROR = 502;// 未知错误
    public final static int USER_PHONE_EXIST = 503;// 手机号已存在
    public final static int USER_EMAIL_EXIST = 504;// 邮箱已存在
    public final static int USER_NAME_EXIST = 505;//用户名已存在
    public final static int ACCESS_LIMITED = 301;// 权限受限
    public final static int SELLER_NOT_SURE = 506;//卖家未确认
    public final static int BUYER_NOT_SURE = 507;//买家未确认
    public final static int DEAL_NOT_COMPLETE = 508;//交易未完成
    public final static int USERNAME_ERROR = 510;// 用户名不存在
    public final static int LONG_TIME_NO_WORK = 511;//长时间未操作，数据发生变化
    public final static int USER_IS_VALID = 513;//用户已被禁用
    public final static int USER_PHONE_NOT_COMPLETE = 514;// 个人信息手机号为空
    public final static int USER_ADDRESS_NOT_COMPLETE = 515;// 个人信息地址为空
    public final static int SURVEY_NO_END = 516;// 上一轮调查尚未结束
    public final static int PASSWORD_IS_INCONSISTENT = 517;// 密码不一致
    public final static int PASSWORD_ERROR = 518;// 密码错误
    public final static int DOWNLOAD_ERROR = 519;// 文件下载失败
    public final static int SQLEXECUTE_ERROR = 520;// 语句执行失败
    public final static int DATA_EXCEPTION = 521;//数据异常UserCondition
    public final static int FINSH_SCORE = 204;//您已完成本次评价

}
