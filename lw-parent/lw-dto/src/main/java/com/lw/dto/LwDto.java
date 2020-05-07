package com.lw.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangyanqiang
 * @title: LwDto
 * @date 2020/4/3015:55
 */
@Data
public class LwDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
}
