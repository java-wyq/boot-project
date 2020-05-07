package com.lw.bases;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyanqiang
 * @title: BaseEntity
 * @date 2020/4/3014:14
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String creator;
    private String updater;
    private Integer delFlag;
    private Date createTime;
    private Date updateTime;
    private String remark;
    private String ext1;
    private String ext2;
    private String ext3;
}
