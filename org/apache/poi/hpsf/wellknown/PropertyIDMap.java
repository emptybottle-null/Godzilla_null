/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf.wellknown;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PropertyIDMap
implements Map<Long, String> {
    public static final int PID_TITLE = 2;
    public static final int PID_SUBJECT = 3;
    public static final int PID_AUTHOR = 4;
    public static final int PID_KEYWORDS = 5;
    public static final int PID_COMMENTS = 6;
    public static final int PID_TEMPLATE = 7;
    public static final int PID_LASTAUTHOR = 8;
    public static final int PID_REVNUMBER = 9;
    public static final int PID_EDITTIME = 10;
    public static final int PID_LASTPRINTED = 11;
    public static final int PID_CREATE_DTM = 12;
    public static final int PID_LASTSAVE_DTM = 13;
    public static final int PID_PAGECOUNT = 14;
    public static final int PID_WORDCOUNT = 15;
    public static final int PID_CHARCOUNT = 16;
    public static final int PID_THUMBNAIL = 17;
    public static final int PID_APPNAME = 18;
    public static final int PID_SECURITY = 19;
    public static final int PID_DICTIONARY = 0;
    public static final int PID_CODEPAGE = 1;
    public static final int PID_CATEGORY = 2;
    public static final int PID_PRESFORMAT = 3;
    public static final int PID_BYTECOUNT = 4;
    public static final int PID_LINECOUNT = 5;
    public static final int PID_PARCOUNT = 6;
    public static final int PID_SLIDECOUNT = 7;
    public static final int PID_NOTECOUNT = 8;
    public static final int PID_HIDDENCOUNT = 9;
    public static final int PID_MMCLIPCOUNT = 10;
    public static final int PID_SCALE = 11;
    public static final int PID_HEADINGPAIR = 12;
    public static final int PID_DOCPARTS = 13;
    public static final int PID_MANAGER = 14;
    public static final int PID_COMPANY = 15;
    public static final int PID_LINKSDIRTY = 16;
    public static final int PID_CCHWITHSPACES = 17;
    public static final int PID_HYPERLINKSCHANGED = 22;
    public static final int PID_VERSION = 23;
    public static final int PID_DIGSIG = 24;
    public static final int PID_CONTENTTYPE = 26;
    public static final int PID_CONTENTSTATUS = 27;
    public static final int PID_LANGUAGE = 28;
    public static final int PID_DOCVERSION = 29;
    public static final int PID_MAX = 31;
    public static final int PID_LOCALE = Integer.MIN_VALUE;
    public static final int PID_BEHAVIOUR = -2147483645;
    public static final String UNDEFINED = "[undefined]";
    private static PropertyIDMap summaryInformationProperties;
    private static final Object[][] summaryInformationIdValues;
    private static PropertyIDMap documentSummaryInformationProperties;
    private static final Object[][] documentSummaryInformationIdValues;
    private static PropertyIDMap fallbackProperties;
    private static final Object[][] fallbackIdValues;
    private final Map<Long, String> idMap;

    private PropertyIDMap(Object[][] idValues) {
        HashMap<Long, String> m = new HashMap<Long, String>(idValues.length);
        for (Object[] idValue : idValues) {
            m.put((Long)idValue[0], (String)idValue[1]);
        }
        this.idMap = Collections.unmodifiableMap(m);
    }

    public static synchronized PropertyIDMap getSummaryInformationProperties() {
        if (summaryInformationProperties == null) {
            summaryInformationProperties = new PropertyIDMap(summaryInformationIdValues);
        }
        return summaryInformationProperties;
    }

    public static synchronized PropertyIDMap getDocumentSummaryInformationProperties() {
        if (documentSummaryInformationProperties == null) {
            documentSummaryInformationProperties = new PropertyIDMap(documentSummaryInformationIdValues);
        }
        return documentSummaryInformationProperties;
    }

    public static synchronized PropertyIDMap getFallbackProperties() {
        if (fallbackProperties == null) {
            fallbackProperties = new PropertyIDMap(fallbackIdValues);
        }
        return fallbackProperties;
    }

    @Override
    public int size() {
        return this.idMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.idMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.idMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.idMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return this.idMap.get(key);
    }

    @Override
    public String put(Long key, String value) {
        return this.idMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return this.idMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends Long, ? extends String> m) {
        this.idMap.putAll(m);
    }

    @Override
    public void clear() {
        this.idMap.clear();
    }

    @Override
    public Set<Long> keySet() {
        return this.idMap.keySet();
    }

    @Override
    public Collection<String> values() {
        return this.idMap.values();
    }

    @Override
    public Set<Map.Entry<Long, String>> entrySet() {
        return this.idMap.entrySet();
    }

    public static void main(String[] args) {
        PropertyIDMap s1 = PropertyIDMap.getSummaryInformationProperties();
        PropertyIDMap s2 = PropertyIDMap.getDocumentSummaryInformationProperties();
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
    }

    static {
        summaryInformationIdValues = new Object[][]{{2L, "PID_TITLE"}, {3L, "PID_SUBJECT"}, {4L, "PID_AUTHOR"}, {5L, "PID_KEYWORDS"}, {6L, "PID_COMMENTS"}, {7L, "PID_TEMPLATE"}, {8L, "PID_LASTAUTHOR"}, {9L, "PID_REVNUMBER"}, {10L, "PID_EDITTIME"}, {11L, "PID_LASTPRINTED"}, {12L, "PID_CREATE_DTM"}, {13L, "PID_LASTSAVE_DTM"}, {14L, "PID_PAGECOUNT"}, {15L, "PID_WORDCOUNT"}, {16L, "PID_CHARCOUNT"}, {17L, "PID_THUMBNAIL"}, {18L, "PID_APPNAME"}, {19L, "PID_SECURITY"}};
        documentSummaryInformationIdValues = new Object[][]{{0L, "PID_DICTIONARY"}, {1L, "PID_CODEPAGE"}, {2L, "PID_CATEGORY"}, {3L, "PID_PRESFORMAT"}, {4L, "PID_BYTECOUNT"}, {5L, "PID_LINECOUNT"}, {6L, "PID_PARCOUNT"}, {7L, "PID_SLIDECOUNT"}, {8L, "PID_NOTECOUNT"}, {9L, "PID_HIDDENCOUNT"}, {10L, "PID_MMCLIPCOUNT"}, {11L, "PID_SCALE"}, {12L, "PID_HEADINGPAIR"}, {13L, "PID_DOCPARTS"}, {14L, "PID_MANAGER"}, {15L, "PID_COMPANY"}, {16L, "PID_LINKSDIRTY"}};
        fallbackIdValues = new Object[][]{{0L, "PID_DICTIONARY"}, {1L, "PID_CODEPAGE"}, {2L, "PID_CATEGORY"}, {3L, "PID_PRESFORMAT"}, {4L, "PID_BYTECOUNT"}, {5L, "PID_LINECOUNT"}, {6L, "PID_PARCOUNT"}, {7L, "PID_SLIDECOUNT"}, {8L, "PID_NOTECOUNT"}, {9L, "PID_HIDDENCOUNT"}, {10L, "PID_MMCLIPCOUNT"}, {11L, "PID_SCALE"}, {12L, "PID_HEADINGPAIR"}, {13L, "PID_DOCPARTS"}, {14L, "PID_MANAGER"}, {15L, "PID_COMPANY"}, {16L, "PID_LINKSDIRTY"}, {17L, "PID_CCHWITHSPACES"}, {22L, "PID_HYPERLINKSCHANGED"}, {23L, "PID_VERSION"}, {24L, "PID_DIGSIG"}, {26L, "PID_CONTENTTYPE"}, {27L, "PID_CONTENTSTATUS"}, {28L, "PID_LANGUAGE"}, {29L, "PID_DOCVERSION"}, {31L, "PID_MAX"}, {Integer.MIN_VALUE, "PID_LOCALE"}, {-2147483645L, "PID_BEHAVIOUR"}};
    }
}

