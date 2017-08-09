package com.ruaho.studyapp.bean;

/**
 * Created by jinyunyang on 15/3/5.
 */
public class Constant {

    /**
     * 测试字符串
     */
    public static final String TEST_STRING = "{\n" +
            "    \"Category\": [\n" +
            "        {\n" +
            "            \"categoryId\": 1,\n" +
            "            \"categoryName\": \"饮品\",\n" +
            "            \"categoryImage\": \"/upload/yinpin.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"categoryId\": 2,\n" +
            "            \"categoryName\": \"食品\",\n" +
            "            \"categoryImage\": \"/upload/shiping.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"categoryId\": 3,\n" +
            "            \"categoryName\": \"酒类\",\n" +
            "            \"categoryImage\": \"/upload/jiullei.jpg\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"recommend\": {\n" +
            "        \"id\": 11,\n" +
            "        \"productName\": \"统一老坛泡椒牛肉袋面香辣味110g*24袋\",\n" +
            "        \"filenameSmall\": \"/upload/ty_ltpj_small.jpg\",\n" +
            "        \"productPrice\": 48,\n" +
            "        \"productCost\": 47.5\n" +
            "    }\n" +
            "}";

    /**
     * 回车符
     */
    public static final String STR_ENTER = "\r\n";

    /**
     * 主键项
     */
    public static final String KEY_ID = "_PK_";

    /**
     * 返回信息标签
     */
    public static final String RTN_MSG = "_MSG_";
    /**
     * 成功信息
     */
    public static final String RTN_MSG_OK = "OK,";
    /**
     * 警告信息
     */
    public static final String RTN_MSG_WARN = "WARN,";
    /**
     * 失败信息
     */
    public static final String RTN_MSG_ERROR = "ERROR,";
    /**
     * 登录信息
     */
    public static final String RTN_MSG_LOGIN = "LOGIN,";
    /**
     * 执行时间
     */
    public static final String RTN_TIME = "_TIME_";

    /**
     * list包装标签
     */
    public static final String RTN_DATA = "_DATA_";


    public static final String PARAM_SELECT = "_SELECT_";
    /**
     * 参数：查询表，支持多个
     */
    public static final String PARAM_TABLE = "_TABLE_";
    /**
     * 参数：过滤条件
     */
    public static final String PARAM_WHERE = "_WHERE_";
    /**
     * 参数：排序设置
     */
    public static final String PARAM_ORDER = "_ORDER_";
    /**
     * 参数：分组设置
     */
    public static final String PARAM_GROUP = "_GROUP_";
    /**
     * 参数：获取记录行数
     */
    public static final String PARAM_ROWNUM = "_ROWNUM_";
    /**
     * 参数：设置prepare sql变量信息
     */
    public static final String PARAM_PRE_VALUES = "_PREVALUES_";

    /**
     * 每页显示数据量
     */
    public static final String PAGE_SHOWNUM = "SHOWNUM";
    /**
     * 当前页
     */
    public static final String PAGE_NOWPAGE = "NOWPAGE";
    /**
     * 数据总量
     */
    public static final String PAGE_ALLNUM = "ALLNUM";
    /**
     * 总页数
     */
    public static final String PAGE_PAGES = "PAGES";
    /**
     * 排序
     */
    public static final String PAGE_ORDER = "ORDER";

    public static final String YES = "1";
    public static final String NO = "0";

    public static final int YES_INT = 1;
    public static final int NO_INT = 2;
    public static final int THREE = 3;
}
