/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util;

import java.math.BigInteger;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.bouncycastle.util.Strings;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Properties {
    private static final ThreadLocal threadProperties = new ThreadLocal();

    private Properties() {
    }

    public static boolean isOverrideSet(String string) {
        try {
            String string2 = Properties.fetchProperty(string);
            if (string2 != null) {
                return "true".equals(Strings.toLowerCase(string2));
            }
            return false;
        } catch (AccessControlException accessControlException) {
            return false;
        }
    }

    public static boolean setThreadOverride(String string, boolean bl) {
        boolean bl2 = Properties.isOverrideSet(string);
        HashMap<String, String> hashMap = (HashMap<String, String>)threadProperties.get();
        if (hashMap == null) {
            hashMap = new HashMap<String, String>();
        }
        hashMap.put(string, bl ? "true" : "false");
        threadProperties.set(hashMap);
        return bl2;
    }

    public static boolean removeThreadOverride(String string) {
        boolean bl = Properties.isOverrideSet(string);
        Map map = (Map)threadProperties.get();
        if (map == null) {
            return false;
        }
        map.remove(string);
        if (map.isEmpty()) {
            threadProperties.remove();
        } else {
            threadProperties.set(map);
        }
        return bl;
    }

    public static BigInteger asBigInteger(String string) {
        String string2 = Properties.fetchProperty(string);
        if (string2 != null) {
            return new BigInteger(string2);
        }
        return null;
    }

    public static Set<String> asKeySet(String string) {
        HashSet<String> hashSet = new HashSet<String>();
        String string2 = Properties.fetchProperty(string);
        if (string2 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(string2, ",");
            while (stringTokenizer.hasMoreElements()) {
                hashSet.add(Strings.toLowerCase(stringTokenizer.nextToken()).trim());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private static String fetchProperty(final String string) {
        return (String)AccessController.doPrivileged(new PrivilegedAction(){

            public Object run() {
                Map map = (Map)threadProperties.get();
                if (map != null) {
                    return map.get(string);
                }
                return System.getProperty(string);
            }
        });
    }
}

