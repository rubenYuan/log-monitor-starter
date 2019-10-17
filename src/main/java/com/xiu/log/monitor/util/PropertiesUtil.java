package com.xiu.log.monitor.util;

import java.util.*;

/**
 * Author:MR.Xiu
 * Description:
 * Date:Created in 6:49 下午 2019/9/23
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
public class PropertiesUtil {
    private static Map<String, ResourceBundle> cache = new HashMap<>();

    public PropertiesUtil() {
    }

    public static String getValue(String resources, String key) {
        String value = "";
        try {
            ResourceBundle rb = cache.get(resources);
            if (rb == null) {
                rb = ResourceBundle.getBundle(resources);
                cache.put(resources, rb);
            }
            value = rb.getString(key);
        } catch (MissingResourceException var4) {
            var4.printStackTrace();
        }
        return value;
    }

    public static String getValue(String resources, String key, boolean notCached) {
        if (notCached) {
            clear(key);
        }

        return getValue(resources, key);
    }

    public static String getValue(Locale local, String resources, String key) {
        String value = "";
        try {
            ResourceBundle rb = (ResourceBundle)cache.get(resources + local.getDisplayName());
            if (rb == null) {
                rb = ResourceBundle.getBundle(resources, local);
                cache.put(resources + local.getDisplayName(), rb);
            }

            value = rb.getString(key);
        } catch (MissingResourceException var5) {
            var5.printStackTrace();
        }

        return value;
    }

    public static boolean getBoolean(String resources, String key) {
        String value = "";

        try {
            ResourceBundle rb = (ResourceBundle)cache.get(resources);
            if (rb == null) {
                rb = ResourceBundle.getBundle(resources);
                cache.put(resources, rb);
            }

            value = rb.getString(key);
        } catch (MissingResourceException var4) {
            var4.printStackTrace();
        }

        return isTrue(value);
    }

    public static boolean getBoolean(Locale local, String resources, String key) {
        String value = "";

        try {
            ResourceBundle rb = (ResourceBundle)cache.get(resources + local.getDisplayName());
            if (rb == null) {
                rb = ResourceBundle.getBundle(resources, local);
                cache.put(resources + local.getDisplayName(), rb);
            }

            value = rb.getString(key);
        } catch (MissingResourceException var5) {
            var5.printStackTrace();
        }

        return isTrue(value);
    }

    private static void clearAll() {
        cache.clear();
    }

    private static void clear(String key) {
        cache.remove(key);
    }

    private static boolean isTrue(String value) {
        return "true".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "y".equalsIgnoreCase(value) || "是".equalsIgnoreCase(value);
    }
}
