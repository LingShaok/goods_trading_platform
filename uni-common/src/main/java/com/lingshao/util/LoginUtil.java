package com.lingshao.util;

import com.lingshao.constant.ErrCode;
import com.lingshao.entity.PublicResultConstant;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginUtil {
    /**
     * @description 爬取教务系统的登录接口
     * @author LingShao
     * @date 2021/4/18
     */
    public static Map<Integer,String> login(String username, String password) throws Exception {
        Map<Integer,String> resultMap = new HashMap<>();
        Connection con = Jsoup.connect("http://jwnew.cqust.edu.cn/eams/login.action?cqustadminweb=1");
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0");
        con.header("Upgrade-Insecure-Requests","1");
        con.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        con.header("Content-Type","application/x-www-form-urlencoded");
        Connection.Response rs = con.execute();
        Document documentLogin = Jsoup.parse(rs.body());
        List<Element> elementsLogin = documentLogin.select("#login_text");
        Map<String, String> datas = new HashMap<String, String>();
        if (elementsLogin.size() != 0) {
            for (Element e : elementsLogin.get(0).getAllElements()) {
                if (e.attr("name").equals("username")) {
                    e.attr("value", username);
                }
                if (e.attr("name").equals("password")) {
                    e.attr("value", password);
                }
                if (e.attr("name").length() > 0) {
                    datas.put(e.attr("name"), e.attr("value"));
                }
            }
        } else {
            resultMap.put(ErrCode.UNKOWN_ERROR, PublicResultConstant.FAILED);
            return resultMap;
        }
        Connection con2 = Jsoup.connect("http://jwnew.cqust.edu.cn/eams/login.action?cqustadminweb=1");
        con2.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0");
        con2.header("Upgrade-Insecure-Requests","1");
        con2.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        con2.header("Content-Type","application/x-www-form-urlencoded");
        Connection.Response login = con2.ignoreContentType(true).method(Connection.Method.POST).data(datas).cookies(rs.cookies()).execute();
        Document documentStuInfo = Jsoup.parse(login.body());
        List<Element> getInfo = documentStuInfo.select("a");
        String errorInfo = getInfo.get(1).text();
        if (errorInfo.equals("返回首页") == false) {
            resultMap.put(ErrCode.PASSWORD_ERROR,PublicResultConstant.INVALID_USERNAME_PASSWORD);
            return resultMap;
        }
        List<Element> studentInfoRow = documentStuInfo.select("a");
        String studentInfo = studentInfoRow.get(11).text();
        resultMap.put(StringUtil.getNumber(studentInfo),StringUtil.getChinese(studentInfo));
        return resultMap;
    }
}
