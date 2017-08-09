package com.ruaho.studyapp.bean;

/**
 * Created by jinyunyang on 15/3/5.
 */
import java.util.Date;
/**
 * 这些帮助函数让 Java 的某些常用功能变得更简单
 *
 * @author Jerry Li
 */
public class Lang {
    private static final String TAG = "LANG";
    private static String SEPARATOR = ",";
    /** log */
//    private static Log log = LogFactory.getLog(Lang.class);

    /**
     * 私有 构建体方法
     */
    private Lang() {
    }


    /**
     * 转型为整型
     * @param obj 原始对象
     * @param def 缺省值
     *
     * @return 整型
     */
    public static int to(Object obj, int def) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return (Integer) obj;
            } else if (obj instanceof Number) {
                return ((Number) obj).intValue();
            } else if (obj instanceof Boolean) {
                return (Boolean) obj ? 1 : 0;
            } else if (obj instanceof Date) {
                return (int) ((Date) obj).getTime();
            } else {
                try {
                    return Integer.parseInt(obj.toString());
                } catch (Exception e) {
                    try {
                        return (new Double(Double.parseDouble(obj.toString()))).intValue();
                    } catch (Exception e2) {
                        return def;
                    }
                }
            }
        } else {
            return def;
        }
    }

    /**
     * 转型为长整型
     * @param obj 原始对象
     * @param def 缺省值
     *
     * @return 长整型
     */
    public static long to(Object obj, long def) {
        if (obj != null) {
            if (obj instanceof Long) {
                return (Long) obj;
            } else if (obj instanceof Number) {
                return ((Number) obj).longValue();
            } else if (obj instanceof Boolean) {
                return (Boolean) obj ? 1 : 0;
            } else if (obj instanceof Date) {
                return ((Date) obj).getTime();
            } else {
                try {
                    return Long.parseLong(obj.toString());
                } catch (Exception e) {
                    try {
                        return (new Double(Double.parseDouble(obj.toString()))).longValue();
                    } catch (Exception e2) {
                        return def;
                    }
                }
            }
        } else {
            return def;
        }
    }

    /**
     * 转型为双精度浮点型
     * @param obj 原始对象
     * @param def 缺省值
     *
     * @return 双精度浮点型
     */
    public static double to(Object obj, double def) {
        if (obj != null) {
            if (obj instanceof Double) {
                return (Double) obj;
            } else if (obj instanceof Float) {
                return ((Float) obj).doubleValue();
            } else if (obj instanceof Number) {
                return ((Number) obj).doubleValue();
            } else if (obj instanceof Boolean) {
                return (Boolean) obj ? 1 : 0;
            } else if (obj instanceof Date) {
                return ((Date) obj).getTime();
            } else {
                try {
                    return Double.parseDouble(obj.toString());
                } catch (Exception e) {
                    return def;
                }
            }
        } else {
            return def;
        }
    }

    /**
     * 转型为布尔值
     * @param obj 原始对象
     * @param def 缺省值
     *
     * @return 布尔值
     */
    public static boolean to(Object obj, boolean def) {
        if (obj != null) {
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            } else if (obj instanceof Integer) {
                return ((Integer) obj).intValue() == 1;
            } else if (obj instanceof Long) {
                return ((Long) obj).longValue() == 1;
            } else if (obj instanceof Double) {
                return ((Double) obj).doubleValue() == 1;
            } else if (obj instanceof Date) {
                return ((Date) obj).getTime() == 1;
            } else {
                String str = obj.toString().toUpperCase();
                return str.equalsIgnoreCase("TRUE") || str.equalsIgnoreCase("YES") || str.equals("1");
            }
        } else {
            return def;
        }
    }

    /**
     * 转型为字符串
     * @param obj 原始对象
     * @param def 缺省值
     *
     * @return 字符串
     */
    public static String to(Object obj, String def) {
        if (obj != null) {
            return obj.toString();
        } else {
            return def;
        }
    }

}
