package com.lw.domain;

import com.lw.bases.BaseEntity;
import lombok.Data;

/**
 * @author wangyanqiang
 * @title: LwEntity
 * @date 2020/4/3014:13
 */
@Data
public class LwEntity extends BaseEntity {
    private Long id;
    private String name;
    private Integer age;
}
