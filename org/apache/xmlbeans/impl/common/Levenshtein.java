/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

public class Levenshtein {
    private static int minimum(int a, int b, int c) {
        int mi = a;
        if (b < mi) {
            mi = b;
        }
        if (c < mi) {
            mi = c;
        }
        return mi;
    }

    public static int distance(String s, String t) {
        int j;
        int i;
        int n = s.length();
        int m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        int[][] d = new int[n + 1][m + 1];
        for (i = 0; i <= n; ++i) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; ++j) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; ++i) {
            char s_i = s.charAt(i - 1);
            for (j = 1; j <= m; ++j) {
                char t_j = t.charAt(j - 1);
                int cost = s_i == t_j ? 0 : 1;
                d[i][j] = Levenshtein.minimum(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }
        return d[n][m];
    }
}

