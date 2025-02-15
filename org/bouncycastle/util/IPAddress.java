/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util;

public class IPAddress {
    public static boolean isValid(String string) {
        return IPAddress.isValidIPv4(string) || IPAddress.isValidIPv6(string);
    }

    public static boolean isValidWithNetMask(String string) {
        return IPAddress.isValidIPv4WithNetmask(string) || IPAddress.isValidIPv6WithNetmask(string);
    }

    public static boolean isValidIPv4(String string) {
        int n;
        if (string.length() == 0) {
            return false;
        }
        int n2 = 0;
        String string2 = string + ".";
        int n3 = 0;
        while (n3 < string2.length() && (n = string2.indexOf(46, n3)) > n3) {
            int n4;
            if (n2 == 4) {
                return false;
            }
            try {
                n4 = Integer.parseInt(string2.substring(n3, n));
            } catch (NumberFormatException numberFormatException) {
                return false;
            }
            if (n4 < 0 || n4 > 255) {
                return false;
            }
            n3 = n + 1;
            ++n2;
        }
        return n2 == 4;
    }

    public static boolean isValidIPv4WithNetmask(String string) {
        int n = string.indexOf("/");
        String string2 = string.substring(n + 1);
        return n > 0 && IPAddress.isValidIPv4(string.substring(0, n)) && (IPAddress.isValidIPv4(string2) || IPAddress.isMaskValue(string2, 32));
    }

    public static boolean isValidIPv6WithNetmask(String string) {
        int n = string.indexOf("/");
        String string2 = string.substring(n + 1);
        return n > 0 && IPAddress.isValidIPv6(string.substring(0, n)) && (IPAddress.isValidIPv6(string2) || IPAddress.isMaskValue(string2, 128));
    }

    private static boolean isMaskValue(String string, int n) {
        try {
            int n2 = Integer.parseInt(string);
            return n2 >= 0 && n2 <= n;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    public static boolean isValidIPv6(String string) {
        int n;
        if (string.length() == 0) {
            return false;
        }
        int n2 = 0;
        String string2 = string + ":";
        boolean bl = false;
        int n3 = 0;
        while (n3 < string2.length() && (n = string2.indexOf(58, n3)) >= n3) {
            if (n2 == 8) {
                return false;
            }
            if (n3 != n) {
                String string3 = string2.substring(n3, n);
                if (n == string2.length() - 1 && string3.indexOf(46) > 0) {
                    if (!IPAddress.isValidIPv4(string3)) {
                        return false;
                    }
                    ++n2;
                } else {
                    int n4;
                    try {
                        n4 = Integer.parseInt(string2.substring(n3, n), 16);
                    } catch (NumberFormatException numberFormatException) {
                        return false;
                    }
                    if (n4 < 0 || n4 > 65535) {
                        return false;
                    }
                }
            } else {
                if (n != 1 && n != string2.length() - 1 && bl) {
                    return false;
                }
                bl = true;
            }
            n3 = n + 1;
            ++n2;
        }
        return n2 == 8 || bl;
    }
}

