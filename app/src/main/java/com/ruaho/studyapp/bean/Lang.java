package com.ruaho.studyapp.bean;

/**
 * Created by jinyunyang on 15/3/5.
 */

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 这些帮助函数让 Java 的某些常用功能变得更简单
 *
 * @author Jerry Li
 */
public class Lang {
    private static final String TAG = "LANG";
    private static String SEPARATOR = ",";

    /** 下面代码用于将36位的UUID字符串转为22位的字符串，提升系统运行效率 */
    private static final char[] CHAR_MAP;

    static {
        CHAR_MAP = new char[64];
        for (int i = 0; i < 10; i++) {
            CHAR_MAP[i] = (char) ('0' + i);
        }
        for (int i = 10; i < 36; i++) {
            CHAR_MAP[i] = (char) ('a' + i - 10);
        }
        for (int i = 36; i < 62; i++) {
            CHAR_MAP[i] = (char) ('A' + i - 36);
        }
        CHAR_MAP[62] = '_';
        CHAR_MAP[63] = '-';
    }

    /** log */
//    private static Log log = LogFactory.getLog(Lang.class);

    /**
     * 私有 构建体方法
     */
    private Lang() {
    }

    /**
     * 得到随机的22位UUID
     * @return 22位UUID
     */
    public static String getUUID() {
        StringBuilder sb = new StringBuilder("0");
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        sb.append(uuid);
        uuid = hexTo64(sb.toString());
        uuid = uuid.replaceAll("_", randomAlphanumeric(2));
        uuid = uuid.replaceAll("-", randomAlphanumeric(2));
        return uuid;
    }

    /**
     * 将16进制字符串转换为64进制
     * @param hex 16进制字符串
     * @return 64进制字符串
     */
    private static String hexTo64(String hex) {
        StringBuilder r = new StringBuilder();
        int index = 0;
        final int size = 3;
        int[] buff = new int[size];
        int l = hex.length();
        for (int i = 0; i < l; i++) {
            index = i % size;
            buff[index] = Integer.parseInt("" + hex.charAt(i), 16);
            if (index == 2) {
                r.append(CHAR_MAP[buff[0] << 2 | buff[1] >>> 2]);
                r.append(CHAR_MAP[(buff[1] & size) << 4 | buff[2]]);
            }
        }
        return r.toString();
    }

    public static String randomAlphanumeric (int count) {
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        int start = ' ';
        int end = 'z' + 1;

        char[] buffer = new char[count];
        int gap = end - start;

        Random random = new Random();

        while (count-- != 0) {
            char ch = (char) (random.nextInt(gap) + start);
            if (Character.isLetter(ch)
                    ||  Character.isDigit(ch)
                    )
            {
                buffer[count] = ch;
            } else {
                count++;
            }
        }
        return new String(buffer);
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
