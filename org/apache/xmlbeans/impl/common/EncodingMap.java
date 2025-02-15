/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;

public class EncodingMap {
    private static final HashMap _iana_to_java = new HashMap();
    private static final HashMap _java_to_iana = new HashMap();

    public static String getJava2IANAMapping(String java) {
        String iana = (String)_java_to_iana.get(java.toUpperCase());
        if (iana != null) {
            return iana;
        }
        if (Charset.isSupported(java)) {
            try {
                iana = Charset.forName(java).name();
                return iana;
            } catch (IllegalArgumentException iae) {
                return null;
            }
        }
        return null;
    }

    public static String getIANA2JavaMapping(String iana) {
        String java = (String)_iana_to_java.get(iana.toUpperCase());
        if (java != null) {
            return java;
        }
        if (Charset.isSupported(iana)) {
            return iana;
        }
        return null;
    }

    private EncodingMap() {
    }

    private static final void addMapping(String java, String iana, boolean isDefault) {
        assert (!_iana_to_java.containsKey(iana));
        assert (java.toUpperCase().equals(java));
        assert (iana.toUpperCase().equals(iana));
        _iana_to_java.put(iana, java);
        if (isDefault) {
            assert (!_java_to_iana.containsKey(java));
            _java_to_iana.put(java, iana);
        }
    }

    private static final boolean completeMappings() {
        HashMap m = new HashMap();
        Iterator i = _iana_to_java.keySet().iterator();
        while (i.hasNext()) {
            m.put(_iana_to_java.get(i.next()), null);
        }
        for (Object k : m.keySet()) {
            assert (_java_to_iana.containsKey(k)) : k;
        }
        return true;
    }

    static {
        EncodingMap.addMapping("ASCII", "ANSI_X3.4-1986", false);
        EncodingMap.addMapping("ASCII", "ASCII", true);
        EncodingMap.addMapping("ASCII", "CP367", false);
        EncodingMap.addMapping("ASCII", "CSASCII", false);
        EncodingMap.addMapping("ASCII", "IBM-367", false);
        EncodingMap.addMapping("ASCII", "IBM367", false);
        EncodingMap.addMapping("ASCII", "ISO-IR-6", false);
        EncodingMap.addMapping("ASCII", "ISO646-US", false);
        EncodingMap.addMapping("ASCII", "ISO_646.IRV:1991", false);
        EncodingMap.addMapping("ASCII", "US", false);
        EncodingMap.addMapping("ASCII", "US-ASCII", false);
        EncodingMap.addMapping("BIG5", "BIG5", true);
        EncodingMap.addMapping("BIG5", "CSBIG5", false);
        EncodingMap.addMapping("CP037", "CP037", false);
        EncodingMap.addMapping("CP037", "CSIBM037", false);
        EncodingMap.addMapping("CP037", "EBCDIC-CP-CA", false);
        EncodingMap.addMapping("CP037", "EBCDIC-CP-NL", false);
        EncodingMap.addMapping("CP037", "EBCDIC-CP-US", true);
        EncodingMap.addMapping("CP037", "EBCDIC-CP-WT", false);
        EncodingMap.addMapping("CP037", "IBM-37", false);
        EncodingMap.addMapping("CP037", "IBM037", false);
        EncodingMap.addMapping("CP1026", "CP1026", false);
        EncodingMap.addMapping("CP1026", "CSIBM1026", false);
        EncodingMap.addMapping("CP1026", "IBM-1026", false);
        EncodingMap.addMapping("CP1026", "IBM1026", true);
        EncodingMap.addMapping("CP1047", "CP1047", false);
        EncodingMap.addMapping("CP1047", "IBM-1047", false);
        EncodingMap.addMapping("CP1047", "IBM1047", true);
        EncodingMap.addMapping("CP1140", "CCSID01140", false);
        EncodingMap.addMapping("CP1140", "CP01140", false);
        EncodingMap.addMapping("CP1140", "IBM-1140", false);
        EncodingMap.addMapping("CP1140", "IBM01140", true);
        EncodingMap.addMapping("CP1141", "CCSID01141", false);
        EncodingMap.addMapping("CP1141", "CP01141", false);
        EncodingMap.addMapping("CP1141", "IBM-1141", false);
        EncodingMap.addMapping("CP1141", "IBM01141", true);
        EncodingMap.addMapping("CP1142", "CCSID01142", false);
        EncodingMap.addMapping("CP1142", "CP01142", false);
        EncodingMap.addMapping("CP1142", "IBM-1142", false);
        EncodingMap.addMapping("CP1142", "IBM01142", true);
        EncodingMap.addMapping("CP1143", "CCSID01143", false);
        EncodingMap.addMapping("CP1143", "CP01143", false);
        EncodingMap.addMapping("CP1143", "IBM-1143", false);
        EncodingMap.addMapping("CP1143", "IBM01143", true);
        EncodingMap.addMapping("CP1144", "CCSID01144", false);
        EncodingMap.addMapping("CP1144", "CP01144", false);
        EncodingMap.addMapping("CP1144", "IBM-1144", false);
        EncodingMap.addMapping("CP1144", "IBM01144", true);
        EncodingMap.addMapping("CP1145", "CCSID01145", false);
        EncodingMap.addMapping("CP1145", "CP01145", false);
        EncodingMap.addMapping("CP1145", "IBM-1145", false);
        EncodingMap.addMapping("CP1145", "IBM01145", true);
        EncodingMap.addMapping("CP1146", "CCSID01146", false);
        EncodingMap.addMapping("CP1146", "CP01146", false);
        EncodingMap.addMapping("CP1146", "IBM-1146", false);
        EncodingMap.addMapping("CP1146", "IBM01146", true);
        EncodingMap.addMapping("CP1147", "CCSID01147", false);
        EncodingMap.addMapping("CP1147", "CP01147", false);
        EncodingMap.addMapping("CP1147", "IBM-1147", false);
        EncodingMap.addMapping("CP1147", "IBM01147", true);
        EncodingMap.addMapping("CP1148", "CCSID01148", false);
        EncodingMap.addMapping("CP1148", "CP01148", false);
        EncodingMap.addMapping("CP1148", "IBM-1148", false);
        EncodingMap.addMapping("CP1148", "IBM01148", true);
        EncodingMap.addMapping("CP1149", "CCSID01149", false);
        EncodingMap.addMapping("CP1149", "CP01149", false);
        EncodingMap.addMapping("CP1149", "IBM-1149", false);
        EncodingMap.addMapping("CP1149", "IBM01149", true);
        EncodingMap.addMapping("CP1250", "WINDOWS-1250", true);
        EncodingMap.addMapping("CP1251", "WINDOWS-1251", true);
        EncodingMap.addMapping("CP1252", "WINDOWS-1252", true);
        EncodingMap.addMapping("CP1253", "WINDOWS-1253", true);
        EncodingMap.addMapping("CP1254", "WINDOWS-1254", true);
        EncodingMap.addMapping("CP1255", "WINDOWS-1255", true);
        EncodingMap.addMapping("CP1256", "WINDOWS-1256", true);
        EncodingMap.addMapping("CP1257", "WINDOWS-1257", true);
        EncodingMap.addMapping("CP1258", "WINDOWS-1258", true);
        EncodingMap.addMapping("CP273", "CP273", false);
        EncodingMap.addMapping("CP273", "CSIBM273", false);
        EncodingMap.addMapping("CP273", "IBM-273", false);
        EncodingMap.addMapping("CP273", "IBM273", true);
        EncodingMap.addMapping("CP277", "CP277", false);
        EncodingMap.addMapping("CP277", "CSIBM277", false);
        EncodingMap.addMapping("CP277", "EBCDIC-CP-DK", true);
        EncodingMap.addMapping("CP277", "EBCDIC-CP-NO", false);
        EncodingMap.addMapping("CP277", "IBM-277", false);
        EncodingMap.addMapping("CP277", "IBM277", false);
        EncodingMap.addMapping("CP278", "CP278", false);
        EncodingMap.addMapping("CP278", "CSIBM278", false);
        EncodingMap.addMapping("CP278", "EBCDIC-CP-FI", true);
        EncodingMap.addMapping("CP278", "EBCDIC-CP-SE", false);
        EncodingMap.addMapping("CP278", "IBM-278", false);
        EncodingMap.addMapping("CP278", "IBM278", false);
        EncodingMap.addMapping("CP280", "CP280", false);
        EncodingMap.addMapping("CP280", "CSIBM280", false);
        EncodingMap.addMapping("CP280", "EBCDIC-CP-IT", true);
        EncodingMap.addMapping("CP280", "IBM-280", false);
        EncodingMap.addMapping("CP280", "IBM280", false);
        EncodingMap.addMapping("CP284", "CP284", false);
        EncodingMap.addMapping("CP284", "CSIBM284", false);
        EncodingMap.addMapping("CP284", "EBCDIC-CP-ES", true);
        EncodingMap.addMapping("CP284", "IBM-284", false);
        EncodingMap.addMapping("CP284", "IBM284", false);
        EncodingMap.addMapping("CP285", "CP285", false);
        EncodingMap.addMapping("CP285", "CSIBM285", false);
        EncodingMap.addMapping("CP285", "EBCDIC-CP-GB", true);
        EncodingMap.addMapping("CP285", "IBM-285", false);
        EncodingMap.addMapping("CP285", "IBM285", false);
        EncodingMap.addMapping("CP290", "CP290", false);
        EncodingMap.addMapping("CP290", "CSIBM290", false);
        EncodingMap.addMapping("CP290", "EBCDIC-JP-KANA", true);
        EncodingMap.addMapping("CP290", "IBM-290", false);
        EncodingMap.addMapping("CP290", "IBM290", false);
        EncodingMap.addMapping("CP297", "CP297", false);
        EncodingMap.addMapping("CP297", "CSIBM297", false);
        EncodingMap.addMapping("CP297", "EBCDIC-CP-FR", true);
        EncodingMap.addMapping("CP297", "IBM-297", false);
        EncodingMap.addMapping("CP297", "IBM297", false);
        EncodingMap.addMapping("CP420", "CP420", false);
        EncodingMap.addMapping("CP420", "CSIBM420", false);
        EncodingMap.addMapping("CP420", "EBCDIC-CP-AR1", true);
        EncodingMap.addMapping("CP420", "IBM-420", false);
        EncodingMap.addMapping("CP420", "IBM420", false);
        EncodingMap.addMapping("CP424", "CP424", false);
        EncodingMap.addMapping("CP424", "CSIBM424", false);
        EncodingMap.addMapping("CP424", "EBCDIC-CP-HE", true);
        EncodingMap.addMapping("CP424", "IBM-424", false);
        EncodingMap.addMapping("CP424", "IBM424", false);
        EncodingMap.addMapping("CP437", "437", false);
        EncodingMap.addMapping("CP437", "CP437", false);
        EncodingMap.addMapping("CP437", "CSPC8CODEPAGE437", false);
        EncodingMap.addMapping("CP437", "IBM-437", false);
        EncodingMap.addMapping("CP437", "IBM437", true);
        EncodingMap.addMapping("CP500", "CP500", false);
        EncodingMap.addMapping("CP500", "CSIBM500", false);
        EncodingMap.addMapping("CP500", "EBCDIC-CP-BE", false);
        EncodingMap.addMapping("CP500", "EBCDIC-CP-CH", true);
        EncodingMap.addMapping("CP500", "IBM-500", false);
        EncodingMap.addMapping("CP500", "IBM500", false);
        EncodingMap.addMapping("CP775", "CP775", false);
        EncodingMap.addMapping("CP775", "CSPC775BALTIC", false);
        EncodingMap.addMapping("CP775", "IBM-775", false);
        EncodingMap.addMapping("CP775", "IBM775", true);
        EncodingMap.addMapping("CP850", "850", false);
        EncodingMap.addMapping("CP850", "CP850", false);
        EncodingMap.addMapping("CP850", "CSPC850MULTILINGUAL", false);
        EncodingMap.addMapping("CP850", "IBM-850", false);
        EncodingMap.addMapping("CP850", "IBM850", true);
        EncodingMap.addMapping("CP852", "852", false);
        EncodingMap.addMapping("CP852", "CP852", false);
        EncodingMap.addMapping("CP852", "CSPCP852", false);
        EncodingMap.addMapping("CP852", "IBM-852", false);
        EncodingMap.addMapping("CP852", "IBM852", true);
        EncodingMap.addMapping("CP855", "855", false);
        EncodingMap.addMapping("CP855", "CP855", false);
        EncodingMap.addMapping("CP855", "CSIBM855", false);
        EncodingMap.addMapping("CP855", "IBM-855", false);
        EncodingMap.addMapping("CP855", "IBM855", true);
        EncodingMap.addMapping("CP857", "857", false);
        EncodingMap.addMapping("CP857", "CP857", false);
        EncodingMap.addMapping("CP857", "CSIBM857", false);
        EncodingMap.addMapping("CP857", "IBM-857", false);
        EncodingMap.addMapping("CP857", "IBM857", true);
        EncodingMap.addMapping("CP858", "CCSID00858", false);
        EncodingMap.addMapping("CP858", "CP00858", false);
        EncodingMap.addMapping("CP858", "IBM-858", false);
        EncodingMap.addMapping("CP858", "IBM00858", true);
        EncodingMap.addMapping("CP860", "860", false);
        EncodingMap.addMapping("CP860", "CP860", false);
        EncodingMap.addMapping("CP860", "CSIBM860", false);
        EncodingMap.addMapping("CP860", "IBM-860", false);
        EncodingMap.addMapping("CP860", "IBM860", true);
        EncodingMap.addMapping("CP861", "861", false);
        EncodingMap.addMapping("CP861", "CP-IS", false);
        EncodingMap.addMapping("CP861", "CP861", false);
        EncodingMap.addMapping("CP861", "CSIBM861", false);
        EncodingMap.addMapping("CP861", "IBM-861", false);
        EncodingMap.addMapping("CP861", "IBM861", true);
        EncodingMap.addMapping("CP862", "862", false);
        EncodingMap.addMapping("CP862", "CP862", false);
        EncodingMap.addMapping("CP862", "CSPC862LATINHEBREW", false);
        EncodingMap.addMapping("CP862", "IBM-862", false);
        EncodingMap.addMapping("CP862", "IBM862", true);
        EncodingMap.addMapping("CP863", "863", false);
        EncodingMap.addMapping("CP863", "CP863", false);
        EncodingMap.addMapping("CP863", "CSIBM863", false);
        EncodingMap.addMapping("CP863", "IBM-863", false);
        EncodingMap.addMapping("CP863", "IBM863", true);
        EncodingMap.addMapping("CP864", "CP864", false);
        EncodingMap.addMapping("CP864", "CSIBM864", false);
        EncodingMap.addMapping("CP864", "IBM-864", false);
        EncodingMap.addMapping("CP864", "IBM864", true);
        EncodingMap.addMapping("CP865", "865", false);
        EncodingMap.addMapping("CP865", "CP865", false);
        EncodingMap.addMapping("CP865", "CSIBM865", false);
        EncodingMap.addMapping("CP865", "IBM-865", false);
        EncodingMap.addMapping("CP865", "IBM865", true);
        EncodingMap.addMapping("CP866", "866", false);
        EncodingMap.addMapping("CP866", "CP866", false);
        EncodingMap.addMapping("CP866", "CSIBM866", false);
        EncodingMap.addMapping("CP866", "IBM-866", false);
        EncodingMap.addMapping("CP866", "IBM866", true);
        EncodingMap.addMapping("CP868", "CP-AR", false);
        EncodingMap.addMapping("CP868", "CP868", false);
        EncodingMap.addMapping("CP868", "CSIBM868", false);
        EncodingMap.addMapping("CP868", "IBM-868", false);
        EncodingMap.addMapping("CP868", "IBM868", true);
        EncodingMap.addMapping("CP869", "CP-GR", false);
        EncodingMap.addMapping("CP869", "CP869", false);
        EncodingMap.addMapping("CP869", "CSIBM869", false);
        EncodingMap.addMapping("CP869", "IBM-869", false);
        EncodingMap.addMapping("CP869", "IBM869", true);
        EncodingMap.addMapping("CP870", "CP870", false);
        EncodingMap.addMapping("CP870", "CSIBM870", false);
        EncodingMap.addMapping("CP870", "EBCDIC-CP-ROECE", true);
        EncodingMap.addMapping("CP870", "EBCDIC-CP-YU", false);
        EncodingMap.addMapping("CP870", "IBM-870", false);
        EncodingMap.addMapping("CP870", "IBM870", false);
        EncodingMap.addMapping("CP871", "CP871", false);
        EncodingMap.addMapping("CP871", "CSIBM871", false);
        EncodingMap.addMapping("CP871", "EBCDIC-CP-IS", true);
        EncodingMap.addMapping("CP871", "IBM-871", false);
        EncodingMap.addMapping("CP871", "IBM871", false);
        EncodingMap.addMapping("CP918", "CP918", false);
        EncodingMap.addMapping("CP918", "CSIBM918", false);
        EncodingMap.addMapping("CP918", "EBCDIC-CP-AR2", true);
        EncodingMap.addMapping("CP918", "IBM-918", false);
        EncodingMap.addMapping("CP918", "IBM918", false);
        EncodingMap.addMapping("CP924", "CCSID00924", false);
        EncodingMap.addMapping("CP924", "CP00924", false);
        EncodingMap.addMapping("CP924", "EBCDIC-LATIN9--EURO", false);
        EncodingMap.addMapping("CP924", "IBM-924", false);
        EncodingMap.addMapping("CP924", "IBM00924", true);
        EncodingMap.addMapping("CP936", "GBK", true);
        EncodingMap.addMapping("CP936", "CP936", false);
        EncodingMap.addMapping("CP936", "MS936", false);
        EncodingMap.addMapping("CP936", "WINDOWS-936", false);
        EncodingMap.addMapping("EUCJIS", "CSEUCPKDFMTJAPANESE", false);
        EncodingMap.addMapping("EUCJIS", "EUC-JP", true);
        EncodingMap.addMapping("EUCJIS", "EXTENDED_UNIX_CODE_PACKED_FORMAT_FOR_JAPANESE", false);
        EncodingMap.addMapping("GB18030", "GB18030", true);
        EncodingMap.addMapping("GB2312", "CSGB2312", false);
        EncodingMap.addMapping("GB2312", "GB2312", true);
        EncodingMap.addMapping("ISO2022CN", "ISO-2022-CN", true);
        EncodingMap.addMapping("ISO2022KR", "CSISO2022KR", false);
        EncodingMap.addMapping("ISO2022KR", "ISO-2022-KR", true);
        EncodingMap.addMapping("ISO8859_1", "CP819", false);
        EncodingMap.addMapping("ISO8859_1", "CSISOLATIN1", false);
        EncodingMap.addMapping("ISO8859_1", "IBM-819", false);
        EncodingMap.addMapping("ISO8859_1", "IBM819", false);
        EncodingMap.addMapping("ISO8859_1", "ISO-8859-1", true);
        EncodingMap.addMapping("ISO8859_1", "ISO-IR-100", false);
        EncodingMap.addMapping("ISO8859_1", "ISO_8859-1", false);
        EncodingMap.addMapping("ISO8859_1", "L1", false);
        EncodingMap.addMapping("ISO8859_1", "LATIN1", false);
        EncodingMap.addMapping("ISO8859_2", "CSISOLATIN2", false);
        EncodingMap.addMapping("ISO8859_2", "ISO-8859-2", true);
        EncodingMap.addMapping("ISO8859_2", "ISO-IR-101", false);
        EncodingMap.addMapping("ISO8859_2", "ISO_8859-2", false);
        EncodingMap.addMapping("ISO8859_2", "L2", false);
        EncodingMap.addMapping("ISO8859_2", "LATIN2", false);
        EncodingMap.addMapping("ISO8859_3", "CSISOLATIN3", false);
        EncodingMap.addMapping("ISO8859_3", "ISO-8859-3", true);
        EncodingMap.addMapping("ISO8859_3", "ISO-IR-109", false);
        EncodingMap.addMapping("ISO8859_3", "ISO_8859-3", false);
        EncodingMap.addMapping("ISO8859_3", "L3", false);
        EncodingMap.addMapping("ISO8859_3", "LATIN3", false);
        EncodingMap.addMapping("ISO8859_4", "CSISOLATIN4", false);
        EncodingMap.addMapping("ISO8859_4", "ISO-8859-4", true);
        EncodingMap.addMapping("ISO8859_4", "ISO-IR-110", false);
        EncodingMap.addMapping("ISO8859_4", "ISO_8859-4", false);
        EncodingMap.addMapping("ISO8859_4", "L4", false);
        EncodingMap.addMapping("ISO8859_4", "LATIN4", false);
        EncodingMap.addMapping("ISO8859_5", "CSISOLATINCYRILLIC", false);
        EncodingMap.addMapping("ISO8859_5", "CYRILLIC", false);
        EncodingMap.addMapping("ISO8859_5", "ISO-8859-5", true);
        EncodingMap.addMapping("ISO8859_5", "ISO-IR-144", false);
        EncodingMap.addMapping("ISO8859_5", "ISO_8859-5", false);
        EncodingMap.addMapping("ISO8859_6", "ARABIC", false);
        EncodingMap.addMapping("ISO8859_6", "ASMO-708", false);
        EncodingMap.addMapping("ISO8859_6", "CSISOLATINARABIC", false);
        EncodingMap.addMapping("ISO8859_6", "ECMA-114", false);
        EncodingMap.addMapping("ISO8859_6", "ISO-8859-6", true);
        EncodingMap.addMapping("ISO8859_6", "ISO-IR-127", false);
        EncodingMap.addMapping("ISO8859_6", "ISO_8859-6", false);
        EncodingMap.addMapping("ISO8859_7", "CSISOLATINGREEK", false);
        EncodingMap.addMapping("ISO8859_7", "ECMA-118", false);
        EncodingMap.addMapping("ISO8859_7", "ELOT_928", false);
        EncodingMap.addMapping("ISO8859_7", "GREEK", false);
        EncodingMap.addMapping("ISO8859_7", "GREEK8", false);
        EncodingMap.addMapping("ISO8859_7", "ISO-8859-7", true);
        EncodingMap.addMapping("ISO8859_7", "ISO-IR-126", false);
        EncodingMap.addMapping("ISO8859_7", "ISO_8859-7", false);
        EncodingMap.addMapping("ISO8859_8", "CSISOLATINHEBREW", false);
        EncodingMap.addMapping("ISO8859_8", "HEBREW", false);
        EncodingMap.addMapping("ISO8859_8", "ISO-8859-8", true);
        EncodingMap.addMapping("ISO8859_8", "ISO-8859-8-I", false);
        EncodingMap.addMapping("ISO8859_8", "ISO-IR-138", false);
        EncodingMap.addMapping("ISO8859_8", "ISO_8859-8", false);
        EncodingMap.addMapping("ISO8859_9", "CSISOLATIN5", false);
        EncodingMap.addMapping("ISO8859_9", "ISO-8859-9", true);
        EncodingMap.addMapping("ISO8859_9", "ISO-IR-148", false);
        EncodingMap.addMapping("ISO8859_9", "ISO_8859-9", false);
        EncodingMap.addMapping("ISO8859_9", "L5", false);
        EncodingMap.addMapping("ISO8859_9", "LATIN5", false);
        EncodingMap.addMapping("JIS", "CSISO2022JP", false);
        EncodingMap.addMapping("JIS", "ISO-2022-JP", true);
        EncodingMap.addMapping("JIS0201", "CSISO13JISC6220JP", false);
        EncodingMap.addMapping("JIS0201", "X0201", true);
        EncodingMap.addMapping("JIS0208", "CSISO87JISX0208", false);
        EncodingMap.addMapping("JIS0208", "ISO-IR-87", false);
        EncodingMap.addMapping("JIS0208", "X0208", true);
        EncodingMap.addMapping("JIS0208", "X0208DBIJIS_X0208-1983", false);
        EncodingMap.addMapping("JIS0212", "CSISO159JISX02121990", false);
        EncodingMap.addMapping("JIS0212", "ISO-IR-159", true);
        EncodingMap.addMapping("JIS0212", "X0212", false);
        EncodingMap.addMapping("KOI8_R", "CSKOI8R", false);
        EncodingMap.addMapping("KOI8_R", "KOI8-R", true);
        EncodingMap.addMapping("KSC5601", "EUC-KR", true);
        EncodingMap.addMapping("MS932", "CSWINDOWS31J", false);
        EncodingMap.addMapping("MS932", "WINDOWS-31J", true);
        EncodingMap.addMapping("SJIS", "CSSHIFTJIS", false);
        EncodingMap.addMapping("SJIS", "MS_KANJI", false);
        EncodingMap.addMapping("SJIS", "SHIFT_JIS", true);
        EncodingMap.addMapping("TIS620", "TIS-620", true);
        EncodingMap.addMapping("UNICODE", "UTF-16", true);
        EncodingMap.addMapping("UTF-16BE", "UTF-16BE", true);
        EncodingMap.addMapping("UTF-16BE", "UTF_16BE", false);
        EncodingMap.addMapping("ISO-10646-UCS-2", "ISO-10646-UCS-2", true);
        EncodingMap.addMapping("UTF-16LE", "UTF-16LE", true);
        EncodingMap.addMapping("UTF-16LE", "UTF_16LE", false);
        EncodingMap.addMapping("UTF8", "UTF-8", true);
        assert (EncodingMap.completeMappings());
    }
}

