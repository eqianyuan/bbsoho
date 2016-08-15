package cn.eqianyuan.bean.po;

public class DataDictionaryPO {
    private Integer id;

    private String groupKey;

    private String groupValKey;

    private String groupValVal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey == null ? null : groupKey.trim();
    }

    public String getGroupValKey() {
        return groupValKey;
    }

    public void setGroupValKey(String groupValKey) {
        this.groupValKey = groupValKey == null ? null : groupValKey.trim();
    }

    public String getGroupValVal() {
        return groupValVal;
    }

    public void setGroupValVal(String groupValVal) {
        this.groupValVal = groupValVal == null ? null : groupValVal.trim();
    }
}