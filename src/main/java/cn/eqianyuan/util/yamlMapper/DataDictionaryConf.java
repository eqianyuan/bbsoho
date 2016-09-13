package cn.eqianyuan.util.yamlMapper;

/**
 * 数据字典分组KEY配置
 * Created by jason on 2016-08-18.
 */
public enum DataDictionaryConf {
    /**
     * 性别
     */
    SEX("sex"),
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
    ENTERPRISE_SCALE("enterprise_scale"),
    /**
     * 尊称
     */
    RESPECTFUL_NAME("respectful_name"),
    /**
     * 学历
     */
    SCHOOLING("schooling"),
    /**
     * 工作年限
     */
    WORKING_YEARS("working_years"),
    /**
     * 行业
     */
    INDUSTRY("industry"),
    /**
     * 工种分类
     */
    WORK_TYPE("work_type"),
    /**
     * 工种
     */
    WORK("work"),
    /**
     * 期望工作时间
     */
    EXPECT_WORK_TIME("expect_work_time"),
    /**
     * 工作餐
     */
    WORKING_LUNCH("working_lunch"),
    /**
     * 期望工作薪资
     */
    EXPECT_PAY("expect_pay"),
    /**
     * 外语语种
     */
    FOREIGN_LANGUAGE("foreign_language"),
    /**
     * 外语语种熟练度
     */
    FOREIGN_LANGUAGE_ABILITY("foreign_language_ability");

    private Object value;

    DataDictionaryConf(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
