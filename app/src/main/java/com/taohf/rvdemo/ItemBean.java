package com.taohf.rvdemo;

/**
 * ItemBean
 *
 * @author hongfei.tao
 * @date 2018/10/31 15:16
 */
public class ItemBean {

    private EItemType type;
    private ESortType mSortType;
    private String sortKey;
    private int index;
    private String desc;

    public EItemType getType() {
        return type;
    }

    public void setType(EItemType type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public ESortType getSortType() {
        return mSortType;
    }

    public void setSortType(ESortType sortType) {
        mSortType = sortType;
    }
}
