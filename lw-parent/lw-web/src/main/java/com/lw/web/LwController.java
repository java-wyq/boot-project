package com.lw.web;

import com.lw.config.annotation.AccessLimit;
import com.lw.domain.LwEntity;
import com.lw.domain.Person;
import com.lw.dto.AjaxResult;
import com.lw.service.impl.LwServiceImpl;
import com.lw.utils.BeanToMapUtil;
import com.lw.utils.IdGeneratorUtils;
import com.lw.utils.JwtTokenUtils;
import com.lw.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyanqiang
 * @title: LwController
 * @date 2020/4/3015:24
 */
@RestController
@RequestMapping("/c")
public class LwController {
    private static final Logger logger = LoggerFactory.getLogger(LwController.class);

    @Autowired
    private LwServiceImpl lwService;

    @RequestMapping("/get/{id}")
    public AjaxResult getId(@PathVariable("id")Long id){
        LwEntity lwEntity = lwService.selectByPrimaryKey(id);
        return AjaxResult.success(lwEntity);
    }
    @RequestMapping("/put/{key}")
    public AjaxResult put(@PathVariable("key")String key){
        Object o = RedisUtils.get(key);
        if(null == o){
            RedisUtils.set(key,key,30);
        }
        return AjaxResult.success();
    }

    @RequestMapping("/putAysnc/{key}")
    public AjaxResult putAysnc(@PathVariable("key")String key){
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        lwService.execueAsync(map);
        return AjaxResult.success();
    }

    @RequestMapping("/token")
    public AjaxResult getToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("userId",123465);
        map.put("name","lw");
        map.put("age",18);
        String s = JwtTokenUtils.create(map);
        logger.info("test get token : " + s);
        return AjaxResult.success("get token successfully",s);
    }
    @RequestMapping("/token1")
    public AjaxResult getToken1(){
        Map<String, Object> map = new HashMap<>();
        map.put("userId",1234657);
        map.put("name","lw");
        map.put("age",18);
        String s = JwtTokenUtils.create(map);
        logger.info("test get token : " + s);
        return AjaxResult.success("get token successfully",s);
    }

    @RequestMapping("/verify/{token}")
    public AjaxResult verify(@PathVariable("token")String token){
        Claims verify = JwtTokenUtils.verify(token);
        logger.info("test verify token : " + verify.toString());
        return AjaxResult.success(verify);
    }

    @RequestMapping("/getId")
    public AjaxResult getId(){
        Long id = IdGeneratorUtils.getInstance().nextId();
        return AjaxResult.success(id);
    }
    @RequestMapping("/getDataCenter")
    public AjaxResult getDataCenter(){
        Long dateCenterId = IdGeneratorUtils.getDataCenterId();
        return AjaxResult.success(dateCenterId);
    }

    @GetMapping("/test")
    public AjaxResult test(){
        LwEntity lwEntity = new LwEntity();
        lwEntity.setAge(18);
        lwEntity.setId(IdGeneratorUtils.getInstance().nextId());
        lwEntity.setName("lw");

        Person person= new Person();
        person.setAge(18);
        person.setName(null);
        person.setId(8564741674L);
        Map<String, Object> map = BeanToMapUtil.castBeanToMapWithIndex(new int[]{1},lwEntity);
        Map<String, Object> map1 = BeanToMapUtil.castBeanToMapWithRequiredFiled(new String[]{"name","id"},lwEntity);
        Map<String, Object> map2 = BeanToMapUtil.castBeanToMapWithNotRequiredFiled(new String[]{"age","id"},person);
        return AjaxResult.success(map2);
    }

    @GetMapping("/app/testAccess")
    @AccessLimit(limit = 5,timeScope = 60)
    public AjaxResult testAccess(){
        return AjaxResult.success();
    }

}
