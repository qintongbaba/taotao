package com.wuqinghua.taotao.common.constant;

/**
 * Created by wuqinghua on 17/10/23.
 */
public enum ItemStatus {
    Normal(Byte.parseByte("1"), "正常"), Remove(Byte.parseByte("2"), "删除"), Sold_Out(Byte
            .parseByte("3"), "下架");

    public Byte value;
    public String desc;

    private ItemStatus(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Byte getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static ItemStatus build(Byte val) {
        switch (val) {
            case 1:
                return ItemStatus.Normal;
            case 2:
                return ItemStatus.Remove;
            case 3:
                return ItemStatus.Sold_Out;
            default:
                throw new IllegalArgumentException("枚举参数异常：itemStatus:" + val);
        }
    }
}
