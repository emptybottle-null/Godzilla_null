/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateBuilder;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.impl.common.InvalidLexicalValueException;
import org.apache.xmlbeans.impl.util.Base64;
import org.apache.xmlbeans.impl.util.HexBin;

public final class XsTypeConverter {
    private static final String POS_INF_LEX = "INF";
    private static final String NEG_INF_LEX = "-INF";
    private static final String NAN_LEX = "NaN";
    private static final char NAMESPACE_SEP = ':';
    private static final String EMPTY_PREFIX = "";
    private static final BigDecimal DECIMAL__ZERO = new BigDecimal(0.0);
    private static final String[] URI_CHARS_TO_BE_REPLACED = new String[]{" ", "{", "}", "|", "\\", "^", "[", "]", "`"};
    private static final String[] URI_CHARS_REPLACED_WITH = new String[]{"%20", "%7b", "%7d", "%7c", "%5c", "%5e", "%5b", "%5d", "%60"};
    private static final char[] CH_ZEROS = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};

    public static float lexFloat(CharSequence cs) throws NumberFormatException {
        String v = ((Object)cs).toString();
        try {
            char ch;
            if (cs.length() > 0 && ((ch = cs.charAt(cs.length() - 1)) == 'f' || ch == 'F') && cs.charAt(cs.length() - 2) != 'N') {
                throw new NumberFormatException("Invalid char '" + ch + "' in float.");
            }
            return Float.parseFloat(v);
        } catch (NumberFormatException e) {
            if (v.equals(POS_INF_LEX)) {
                return Float.POSITIVE_INFINITY;
            }
            if (v.equals(NEG_INF_LEX)) {
                return Float.NEGATIVE_INFINITY;
            }
            if (v.equals(NAN_LEX)) {
                return Float.NaN;
            }
            throw e;
        }
    }

    public static float lexFloat(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexFloat(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid float: " + cs;
            errors.add(XmlError.forMessage(msg));
            return Float.NaN;
        }
    }

    public static String printFloat(float value) {
        if (value == Float.POSITIVE_INFINITY) {
            return POS_INF_LEX;
        }
        if (value == Float.NEGATIVE_INFINITY) {
            return NEG_INF_LEX;
        }
        if (Float.isNaN(value)) {
            return NAN_LEX;
        }
        return Float.toString(value);
    }

    public static double lexDouble(CharSequence cs) throws NumberFormatException {
        String v = ((Object)cs).toString();
        try {
            char ch;
            if (cs.length() > 0 && ((ch = cs.charAt(cs.length() - 1)) == 'd' || ch == 'D')) {
                throw new NumberFormatException("Invalid char '" + ch + "' in double.");
            }
            return Double.parseDouble(v);
        } catch (NumberFormatException e) {
            if (v.equals(POS_INF_LEX)) {
                return Double.POSITIVE_INFINITY;
            }
            if (v.equals(NEG_INF_LEX)) {
                return Double.NEGATIVE_INFINITY;
            }
            if (v.equals(NAN_LEX)) {
                return Double.NaN;
            }
            throw e;
        }
    }

    public static double lexDouble(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexDouble(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid double: " + cs;
            errors.add(XmlError.forMessage(msg));
            return Double.NaN;
        }
    }

    public static String printDouble(double value) {
        if (value == Double.POSITIVE_INFINITY) {
            return POS_INF_LEX;
        }
        if (value == Double.NEGATIVE_INFINITY) {
            return NEG_INF_LEX;
        }
        if (Double.isNaN(value)) {
            return NAN_LEX;
        }
        return Double.toString(value);
    }

    public static BigDecimal lexDecimal(CharSequence cs) throws NumberFormatException {
        String v = ((Object)cs).toString();
        return new BigDecimal(XsTypeConverter.trimTrailingZeros(v));
    }

    public static BigDecimal lexDecimal(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexDecimal(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid long: " + cs;
            errors.add(XmlError.forMessage(msg));
            return DECIMAL__ZERO;
        }
    }

    public static String printDecimal(BigDecimal value) {
        String intStr = value.unscaledValue().toString();
        int scale = value.scale();
        if (scale == 0 || value.longValue() == 0L && scale < 0) {
            return intStr;
        }
        int begin = value.signum() < 0 ? 1 : 0;
        int delta = scale;
        StringBuffer result = new StringBuffer(intStr.length() + 1 + Math.abs(scale));
        if (begin == 1) {
            result.append('-');
        }
        if (scale > 0) {
            if ((delta -= intStr.length() - begin) >= 0) {
                result.append("0.");
                while (delta > CH_ZEROS.length) {
                    result.append(CH_ZEROS);
                    delta -= CH_ZEROS.length;
                }
                result.append(CH_ZEROS, 0, delta);
                result.append(intStr.substring(begin));
            } else {
                delta = begin - delta;
                result.append(intStr.substring(begin, delta));
                result.append('.');
                result.append(intStr.substring(delta));
            }
        } else {
            result.append(intStr.substring(begin));
            while (delta < -CH_ZEROS.length) {
                result.append(CH_ZEROS);
                delta += CH_ZEROS.length;
            }
            result.append(CH_ZEROS, 0, -delta);
        }
        return result.toString();
    }

    public static BigInteger lexInteger(CharSequence cs) throws NumberFormatException {
        if (cs.length() > 1 && cs.charAt(0) == '+' && cs.charAt(1) == '-') {
            throw new NumberFormatException("Illegal char sequence '+-'");
        }
        String v = ((Object)cs).toString();
        return new BigInteger(XsTypeConverter.trimInitialPlus(v));
    }

    public static BigInteger lexInteger(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexInteger(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid long: " + cs;
            errors.add(XmlError.forMessage(msg));
            return BigInteger.ZERO;
        }
    }

    public static String printInteger(BigInteger value) {
        return value.toString();
    }

    public static long lexLong(CharSequence cs) throws NumberFormatException {
        String v = ((Object)cs).toString();
        return Long.parseLong(XsTypeConverter.trimInitialPlus(v));
    }

    public static long lexLong(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexLong(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid long: " + cs;
            errors.add(XmlError.forMessage(msg));
            return 0L;
        }
    }

    public static String printLong(long value) {
        return Long.toString(value);
    }

    public static short lexShort(CharSequence cs) throws NumberFormatException {
        return XsTypeConverter.parseShort(cs);
    }

    public static short lexShort(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexShort(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid short: " + cs;
            errors.add(XmlError.forMessage(msg));
            return 0;
        }
    }

    public static String printShort(short value) {
        return Short.toString(value);
    }

    public static int lexInt(CharSequence cs) throws NumberFormatException {
        return XsTypeConverter.parseInt(cs);
    }

    public static int lexInt(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexInt(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid int:" + cs;
            errors.add(XmlError.forMessage(msg));
            return 0;
        }
    }

    public static String printInt(int value) {
        return Integer.toString(value);
    }

    public static byte lexByte(CharSequence cs) throws NumberFormatException {
        return XsTypeConverter.parseByte(cs);
    }

    public static byte lexByte(CharSequence cs, Collection errors) {
        try {
            return XsTypeConverter.lexByte(cs);
        } catch (NumberFormatException e) {
            String msg = "invalid byte: " + cs;
            errors.add(XmlError.forMessage(msg));
            return 0;
        }
    }

    public static String printByte(byte value) {
        return Byte.toString(value);
    }

    public static boolean lexBoolean(CharSequence v) {
        switch (v.length()) {
            case 1: {
                char c = v.charAt(0);
                if ('0' == c) {
                    return false;
                }
                if ('1' != c) break;
                return true;
            }
            case 4: {
                if ('t' != v.charAt(0) || 'r' != v.charAt(1) || 'u' != v.charAt(2) || 'e' != v.charAt(3)) break;
                return true;
            }
            case 5: {
                if ('f' != v.charAt(0) || 'a' != v.charAt(1) || 'l' != v.charAt(2) || 's' != v.charAt(3) || 'e' != v.charAt(4)) break;
                return false;
            }
        }
        String msg = "invalid boolean: " + v;
        throw new InvalidLexicalValueException(msg);
    }

    public static boolean lexBoolean(CharSequence value, Collection errors) {
        try {
            return XsTypeConverter.lexBoolean(value);
        } catch (InvalidLexicalValueException e) {
            errors.add(XmlError.forMessage(e.getMessage()));
            return false;
        }
    }

    public static String printBoolean(boolean value) {
        return value ? "true" : "false";
    }

    public static String lexString(CharSequence cs, Collection errors) {
        String v = ((Object)cs).toString();
        return v;
    }

    public static String lexString(CharSequence lexical_value) {
        return ((Object)lexical_value).toString();
    }

    public static String printString(String value) {
        return value;
    }

    public static QName lexQName(CharSequence charSeq, NamespaceContext nscontext) {
        String uri;
        String localname;
        String prefix;
        int firstcolon;
        boolean hasFirstCollon = false;
        for (firstcolon = 0; firstcolon < charSeq.length(); ++firstcolon) {
            if (charSeq.charAt(firstcolon) != ':') continue;
            hasFirstCollon = true;
            break;
        }
        if (hasFirstCollon) {
            prefix = ((Object)charSeq.subSequence(0, firstcolon)).toString();
            localname = ((Object)charSeq.subSequence(firstcolon + 1, charSeq.length())).toString();
            if (firstcolon == 0) {
                throw new InvalidLexicalValueException("invalid xsd:QName '" + ((Object)charSeq).toString() + "'");
            }
        } else {
            prefix = EMPTY_PREFIX;
            localname = ((Object)charSeq).toString();
        }
        if ((uri = nscontext.getNamespaceURI(prefix)) == null) {
            if (prefix != null && prefix.length() > 0) {
                throw new InvalidLexicalValueException("Can't resolve prefix: " + prefix);
            }
            uri = EMPTY_PREFIX;
        }
        return new QName(uri, localname);
    }

    public static QName lexQName(String xsd_qname, Collection errors, NamespaceContext nscontext) {
        try {
            return XsTypeConverter.lexQName(xsd_qname, nscontext);
        } catch (InvalidLexicalValueException e) {
            errors.add(XmlError.forMessage(e.getMessage()));
            int idx = xsd_qname.indexOf(58);
            return new QName(null, xsd_qname.substring(idx));
        }
    }

    public static String printQName(QName qname, NamespaceContext nsContext, Collection errors) {
        String prefix;
        String uri = qname.getNamespaceURI();
        assert (uri != null);
        if (uri.length() > 0) {
            prefix = nsContext.getPrefix(uri);
            if (prefix == null) {
                String msg = "NamespaceContext does not provide prefix for namespaceURI " + uri;
                errors.add(XmlError.forMessage(msg));
            }
        } else {
            prefix = null;
        }
        return XsTypeConverter.getQNameString(uri, qname.getLocalPart(), prefix);
    }

    public static String getQNameString(String uri, String localpart, String prefix) {
        if (prefix != null && uri != null && uri.length() > 0 && prefix.length() > 0) {
            return prefix + ':' + localpart;
        }
        return localpart;
    }

    public static GDate lexGDate(CharSequence charSeq) {
        return new GDate(charSeq);
    }

    public static GDate lexGDate(String xsd_gdate, Collection errors) {
        try {
            return XsTypeConverter.lexGDate(xsd_gdate);
        } catch (IllegalArgumentException e) {
            errors.add(XmlError.forMessage(e.getMessage()));
            return new GDateBuilder().toGDate();
        }
    }

    public static String printGDate(GDate gdate, Collection errors) {
        return gdate.toString();
    }

    public static XmlCalendar lexDateTime(CharSequence v) {
        GDateSpecification value = XsTypeConverter.getGDateValue(v, 14);
        return value.getCalendar();
    }

    public static String printDateTime(Calendar c) {
        return XsTypeConverter.printDateTime(c, 14);
    }

    public static String printTime(Calendar c) {
        return XsTypeConverter.printDateTime(c, 15);
    }

    public static String printDate(Calendar c) {
        return XsTypeConverter.printDateTime(c, 16);
    }

    public static String printDate(Date d) {
        GDateSpecification value = XsTypeConverter.getGDateValue(d, 16);
        return ((Object)value).toString();
    }

    public static String printDateTime(Calendar c, int type_code) {
        GDateSpecification value = XsTypeConverter.getGDateValue(c, type_code);
        return ((Object)value).toString();
    }

    public static String printDateTime(Date c) {
        GDateSpecification value = XsTypeConverter.getGDateValue(c, 14);
        return ((Object)value).toString();
    }

    public static CharSequence printHexBinary(byte[] val) {
        return HexBin.bytesToString(val);
    }

    public static byte[] lexHexBinary(CharSequence lexical_value) {
        byte[] buf = HexBin.decode(((Object)lexical_value).toString().getBytes());
        if (buf != null) {
            return buf;
        }
        throw new InvalidLexicalValueException("invalid hexBinary value");
    }

    public static CharSequence printBase64Binary(byte[] val) {
        byte[] bytes = Base64.encode(val);
        return new String(bytes);
    }

    public static byte[] lexBase64Binary(CharSequence lexical_value) {
        byte[] buf = Base64.decode(((Object)lexical_value).toString().getBytes());
        if (buf != null) {
            return buf;
        }
        throw new InvalidLexicalValueException("invalid base64Binary value");
    }

    public static GDateSpecification getGDateValue(Date d, int builtin_type_code) {
        GDateBuilder gDateBuilder = new GDateBuilder(d);
        gDateBuilder.setBuiltinTypeCode(builtin_type_code);
        GDate value = gDateBuilder.toGDate();
        return value;
    }

    public static GDateSpecification getGDateValue(Calendar c, int builtin_type_code) {
        GDateBuilder gDateBuilder = new GDateBuilder(c);
        gDateBuilder.setBuiltinTypeCode(builtin_type_code);
        GDate value = gDateBuilder.toGDate();
        return value;
    }

    public static GDateSpecification getGDateValue(CharSequence v, int builtin_type_code) {
        GDateBuilder gDateBuilder = new GDateBuilder(v);
        gDateBuilder.setBuiltinTypeCode(builtin_type_code);
        GDate value = gDateBuilder.toGDate();
        return value;
    }

    private static String trimInitialPlus(String xml) {
        if (xml.length() > 0 && xml.charAt(0) == '+') {
            return xml.substring(1);
        }
        return xml;
    }

    private static String trimTrailingZeros(String xsd_decimal) {
        int last_point;
        int last_char_idx = xsd_decimal.length() - 1;
        if (xsd_decimal.charAt(last_char_idx) == '0' && (last_point = xsd_decimal.lastIndexOf(46)) >= 0) {
            for (int idx = last_char_idx; idx > last_point; --idx) {
                if (xsd_decimal.charAt(idx) == '0') continue;
                return xsd_decimal.substring(0, idx + 1);
            }
            return xsd_decimal.substring(0, last_point);
        }
        return xsd_decimal;
    }

    private static int parseInt(CharSequence cs) {
        return XsTypeConverter.parseIntXsdNumber(cs, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static short parseShort(CharSequence cs) {
        return (short)XsTypeConverter.parseIntXsdNumber(cs, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    private static byte parseByte(CharSequence cs) {
        return (byte)XsTypeConverter.parseIntXsdNumber(cs, -128, 127);
    }

    private static int parseIntXsdNumber(CharSequence ch, int min_value, int max_value) {
        int limit2;
        int limit;
        int length = ch.length();
        if (length < 1) {
            throw new NumberFormatException("For input string: \"" + ((Object)ch).toString() + "\"");
        }
        int sign = 1;
        int result = 0;
        int start = 0;
        char c = ch.charAt(0);
        if (c == '-') {
            ++start;
            limit = min_value / 10;
            limit2 = -(min_value % 10);
        } else if (c == '+') {
            ++start;
            sign = -1;
            limit = -(max_value / 10);
            limit2 = max_value % 10;
        } else {
            sign = -1;
            limit = -(max_value / 10);
            limit2 = max_value % 10;
        }
        for (int i = 0; i < length - start; ++i) {
            c = ch.charAt(i + start);
            int v = Character.digit(c, 10);
            if (v < 0) {
                throw new NumberFormatException("For input string: \"" + ((Object)ch).toString() + "\"");
            }
            if (result < limit || result == limit && v > limit2) {
                throw new NumberFormatException("For input string: \"" + ((Object)ch).toString() + "\"");
            }
            result = result * 10 - v;
        }
        return sign * result;
    }

    public static CharSequence printAnyURI(CharSequence val) {
        return val;
    }

    public static CharSequence lexAnyURI(CharSequence lexical_value) {
        StringBuffer s = new StringBuffer(((Object)lexical_value).toString());
        for (int ic = 0; ic < URI_CHARS_TO_BE_REPLACED.length; ++ic) {
            int i = 0;
            while ((i = s.indexOf(URI_CHARS_TO_BE_REPLACED[ic], i)) >= 0) {
                s.replace(i, i + 1, URI_CHARS_REPLACED_WITH[ic]);
                i += 3;
            }
        }
        try {
            URI.create(s.toString());
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException("invalid anyURI value: " + lexical_value, e);
        }
        return lexical_value;
    }
}

