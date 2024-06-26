package cn.edu.xmu.oomall.activity.model.bo;


/**
 * @author Jiawei Zheng
 * @date 2021-11-26
 */
public enum AdvanceSaleState {
    /**
     * DRAFT活动在草稿状态
     * OFFLINE活动在下线状态
     * ONLINE活动在上线状态
     */
    DRAFT((byte)0,"草稿"),
    ONLINE((byte)1,"上线"),
    OFFLINE((byte)2, "下线");

    private Byte code;
    private String value;
    AdvanceSaleState(Byte code, String value) {
        this.code=code;
        this.value=value;
    }

    public Byte getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
