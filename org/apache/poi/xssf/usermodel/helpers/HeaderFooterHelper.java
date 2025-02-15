/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

public class HeaderFooterHelper {
    private static final String HeaderFooterEntity_L = "&L";
    private static final String HeaderFooterEntity_C = "&C";
    private static final String HeaderFooterEntity_R = "&R";
    public static final String HeaderFooterEntity_File = "&F";
    public static final String HeaderFooterEntity_Date = "&D";
    public static final String HeaderFooterEntity_Time = "&T";

    public String getLeftSection(String string) {
        return this.getParts(string)[0];
    }

    public String getCenterSection(String string) {
        return this.getParts(string)[1];
    }

    public String getRightSection(String string) {
        return this.getParts(string)[2];
    }

    public String setLeftSection(String string, String newLeft) {
        String[] parts = this.getParts(string);
        parts[0] = newLeft;
        return this.joinParts(parts);
    }

    public String setCenterSection(String string, String newCenter) {
        String[] parts = this.getParts(string);
        parts[1] = newCenter;
        return this.joinParts(parts);
    }

    public String setRightSection(String string, String newRight) {
        String[] parts = this.getParts(string);
        parts[2] = newRight;
        return this.joinParts(parts);
    }

    private String[] getParts(String string) {
        String[] parts = new String[]{"", "", ""};
        if (string == null) {
            return parts;
        }
        int lAt = 0;
        int cAt = 0;
        int rAt = 0;
        while ((lAt = string.indexOf(HeaderFooterEntity_L)) > -2 && (cAt = string.indexOf(HeaderFooterEntity_C)) > -2 && (rAt = string.indexOf(HeaderFooterEntity_R)) > -2 && (lAt > -1 || cAt > -1 || rAt > -1)) {
            if (rAt > cAt && rAt > lAt) {
                parts[2] = string.substring(rAt + HeaderFooterEntity_R.length());
                string = string.substring(0, rAt);
                continue;
            }
            if (cAt > rAt && cAt > lAt) {
                parts[1] = string.substring(cAt + HeaderFooterEntity_C.length());
                string = string.substring(0, cAt);
                continue;
            }
            parts[0] = string.substring(lAt + HeaderFooterEntity_L.length());
            string = string.substring(0, lAt);
        }
        return parts;
    }

    private String joinParts(String[] parts) {
        return this.joinParts(parts[0], parts[1], parts[2]);
    }

    private String joinParts(String l, String c, String r) {
        StringBuilder ret = new StringBuilder(64);
        if (c.length() > 0) {
            ret.append(HeaderFooterEntity_C);
            ret.append(c);
        }
        if (l.length() > 0) {
            ret.append(HeaderFooterEntity_L);
            ret.append(l);
        }
        if (r.length() > 0) {
            ret.append(HeaderFooterEntity_R);
            ret.append(r);
        }
        return ret.toString();
    }
}

