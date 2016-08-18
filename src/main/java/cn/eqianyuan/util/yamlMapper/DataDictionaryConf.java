package cn.eqianyuan.util.yamlMapper;

/**
 * 数据字典分组KEY配置
 * Created by jason on 2016-08-18.
 */
public enum DataDictionaryConf {
    /**
     * 激活状态
     */
    ACTIVATION_STATUS("activation_status"),
    /**
     * 公司性质
     */
    ENTERPRISE_NATURE("enterprise_nature"),
    /**
     * 公司规模
     */
    ENTERPRISE_SCALE("enterprise_scale");

    private Object value;

    DataDictionaryConf(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
