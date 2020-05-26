package com.lw.utils;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Maps;
import com.lw.annotation.BeanOrder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author wangyanqiang
 * @title: BeanToMapService
 * @date 2020/5/2116:12
 */
public class BeanToMapUtil {

    /**
     * 根据下标获取对应的Map
     * @param index
     * @param obj
     * @return
     */
    public static Map<String,Object> castBeanToMapWithIndex(int [] index, Object obj){
        Map<String,Object> map = Maps.newHashMap();
        try {
            Field fields[] = obj.getClass().getDeclaredFields();
            Method[] methods =  obj.getClass().getDeclaredMethods();
            List<Field> fl=getOrderedField(fields);
            for (int i = 0; i < index.length; i++) {
                Field field = fl.get(index[i]);
                field.setAccessible(true);
                map.put(field.getName(),field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据需要的属性名称获取对应的Map
     * @param filed
     * @param obj
     * @return
     */
    public static Map<String,Object> castBeanToMapWithRequiredFiled(String [] filed,Object obj){
        List<String> list = Arrays.asList(filed);
        Map<String, Object> map = BeanUtil.beanToMap(obj);
        Map<String, Object> rtnMap = Maps.newHashMap();
        map.forEach((k,v) ->{
            if(list.contains(k)){
                rtnMap.put(k,v);
            }
        });
        return rtnMap;
    }

    /**
     * 根据不需要的属性名称获取对应的Map
     * @param filed
     * @param obj
     * @return
     */
    public static Map<String,Object> castBeanToMapWithNotRequiredFiled(String [] filed,Object obj){
        List<String> list = Arrays.asList(filed);
        Map<String, Object> map = BeanUtil.beanToMap(obj);
        map.keySet().removeIf(key  ->list.contains(key));
        return map;
    }

    private static List<Field> getOrderedField(Field[] fields) {
        List<Field> fieldList = new ArrayList<>();
        for (Field f : fields) {
            if (f.getAnnotation(BeanOrder.class) != null) {
                fieldList.add(f);
            }
        }
         fieldList.sort(Comparator.comparingInt(
                m -> m.getAnnotation(BeanOrder.class).order()));
        return fieldList;
    }


}
