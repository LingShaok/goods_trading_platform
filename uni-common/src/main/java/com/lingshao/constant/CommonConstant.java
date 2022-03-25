/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: Liu long fei
 * Date: 2018/12/13
 * Description:CommonConstant.java
 */
package com.lingshao.constant;

/**
 * 常量
 *
 * @author bin
 */
public interface CommonConstant {

    interface MESSAGE_CODE {
        /**
         * 成功
         */
        int SUCCESS = 200;

        /**
         * 失败
         */
        int FAILED = 301;

    }

    interface USE_STATUS {
        /**
         * 已使用
         */
        int USE = 1;
        /**
         * 未使用
         */
        int UNUSE = 0;
    }

    interface MAJOR {
        interface STATUS {
            /**
             * 启用
             */
            Integer USE = 0;

            /**
             * 禁用
             */
            Integer UNUSE = 1;
        }
    }

    interface WEEK {
        String[] WEEKARRAY = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    }

    interface WEEKDAY_NUMBER {
        int ZERO = 0;
        int ONE = 1;
        int TWO = 2;
        int SEVEN = 7;
    }

    interface FOUNCTION_NAME {
        String TEACHER_IMPORT = "teacherImport";
    }

    interface FILE_NAME {
        String FAIL_IMPORT_FILE_NAME = "FailImportTeacher.xls";
    }

    /**
     * @description 交易方式 0：自约地点，1：送货上门，2：上门取货，3：买家决定
     * @author LingShao
     * @date 2021/4/16
     */
    interface TRANSACTION {
        String CUSTOM = "自约地点";
        String SEND_BY_SELLER = "卖家送货";
        String TAKE_GOODS_BY_BUYER = "买家提货";
        String ACCORD_BUYER = "买家决定";
    }

}
