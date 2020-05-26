package com.lw.domain;

import com.lw.annotation.BeanOrder;
import com.lw.bases.BaseEntity;
import lombok.Data;

/**
 * @author wangyanqiang
 * @title: LwEntity
 * @date 2020/4/3014:13
 */
@Data
public class LwEntity extends BaseEntity {
    @BeanOrder(order = 0)
    private Long id;
    @BeanOrder(order = 1)
    private String name;
    @BeanOrder(order = 2)
    private Integer age;
}
