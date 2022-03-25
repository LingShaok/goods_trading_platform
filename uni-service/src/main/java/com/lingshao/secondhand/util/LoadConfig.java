package com.lingshao.secondhand.util;


import java.io.InputStream;
import java.util.Properties;

/**
 * @author LingShao
 * @date 2021/4/18
 */
public class LoadConfig {

    public static String publicKey;
    public static String privateKey;

    //个人密钥
    public static String secretId;
    public static String secretKey;
    //发送的模板id
    public static String templateId;
    //appid
    public static String smsSdkAppId;
    //模板绑定的签名
    public static String sign;

    static {
        Properties props = new Properties();
        try {
            //通过类加载器，加载url.propeties文件。
            InputStream in = LoadConfig.class.getClassLoader().getResourceAsStream("init.properties");

            props.load(in);

            publicKey = props.getProperty("publicKey");
            privateKey = props.getProperty("privateKey");

            secretId = props.getProperty("secretId");
            secretKey = props.getProperty("secretKey");
            templateId = props.getProperty("templateId");
            smsSdkAppId = props.getProperty("smsSdkAppId");
            sign = props.getProperty("sign");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
