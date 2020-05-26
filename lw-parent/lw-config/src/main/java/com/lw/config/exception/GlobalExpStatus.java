package com.lw.config.exception;

/**
 * @author wangyanqiang
 * @title: GlobalExpStatus
 * @date 2020/5/2617:25
 */
public enum GlobalExpStatus {

    /**
     * AccessLimitForNull
     */
    AccessLimitForNull(5001),
    /**
     * AccessLimitForToken
     */
    AccessLimitForToken(5002),
    /**
     * AccessLimitForFrequently
     */
    AccessLimit(5003),
    /**
     * AccessLimitForUnknown
     */
    AccessLimitUnknown(5004);

    private final Integer value;

    GlobalExpStatus(Integer value)
    {
        this.value = value;
    }

    public int value()
    {
        return this.value;
    }

}
