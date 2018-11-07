package com.one.wsy.wzxlib.multilayout.bean;

/**
 * @author wsy
 */
public class Entity {

    public static final int ITEM_ONE = 1;
    public static final int ITEM_TWO = 2;
    public static final int ITEM_THREE = 3;

    public Entity(int adaterType) {
        this.adaterType = adaterType;
    }

    private int adaterType;

    public int getAdaterType() {
        return adaterType;
    }

    public void setAdaterType(int adaterType) {
        this.adaterType = adaterType;
    }
}
