package com.lw.web;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wangyanqiang
 * @title: YQDataCOntroller
 * @date 2020/5/2714:04
 */
@RestController
@RequestMapping("yq")
public class YQDataController {
    private static final String china="https://gwpre.sina.cn/interface/fymap2020_data.json?_=timestamp";
    private static final String shannxi="https://gwpre.sina.cn/interface/news/ncp/data.d.json?mod=province&province=shanxis&_=timestamp";
    private static final String xian="https://gwpre.sina.cn/interface/news/ncp/data.d.json?mod=city&citycode=CN61010000000000&_=timestamp";


    @RequestMapping("/china")
    public JSONObject china(){
        Date date = new Date();
        System.out.println(date.getTime());

        String s = HttpUtil.get(china.replace("timestamp",String.valueOf(date.getTime())));
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject;
    }
    @RequestMapping("/shannxi")
    public JSONObject shannxi(){
        Date date = new Date();
        String s = HttpUtil.get(shannxi.replace("timestamp",String.valueOf(date.getTime())));
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject;
    }
    @RequestMapping("/xian")
    public JSONObject xian(){
        Date date = new Date();
        String s = HttpUtil.get(xian.replace("timestamp",String.valueOf(date.getTime())));
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject;
    }
}
