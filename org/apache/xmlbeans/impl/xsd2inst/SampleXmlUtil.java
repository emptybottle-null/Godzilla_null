/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xsd2inst;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateBuilder;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationBuilder;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDuration;
import org.apache.xmlbeans.XmlGDay;
import org.apache.xmlbeans.XmlGMonth;
import org.apache.xmlbeans.XmlGMonthDay;
import org.apache.xmlbeans.XmlGYear;
import org.apache.xmlbeans.XmlGYearMonth;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlTime;
import org.apache.xmlbeans.impl.util.Base64;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

public class SampleXmlUtil {
    private boolean _soapEnc;
    private static final int MAX_ELEMENTS = 1000;
    private int _nElements;
    Random _picker = new Random(1L);
    public static final String[] WORDS = new String[]{"ipsa", "iovis", "rapidum", "iaculata", "e", "nubibus", "ignem", "disiecitque", "rates", "evertitque", "aequora", "ventis", "illum", "exspirantem", "transfixo", "pectore", "flammas", "turbine", "corripuit", "scopuloque", "infixit", "acuto", "ast", "ego", "quae", "divum", "incedo", "regina", "iovisque", "et", "soror", "et", "coniunx", "una", "cum", "gente", "tot", "annos", "bella", "gero", "et", "quisquam", "numen", "iunonis", "adorat", "praeterea", "aut", "supplex", "aris", "imponet", "honorem", "talia", "flammato", "secum", "dea", "corde", "volutans", "nimborum", "in", "patriam", "loca", "feta", "furentibus", "austris", "aeoliam", "venit", "hic", "vasto", "rex", "aeolus", "antro", "luctantis", "ventos", "tempestatesque", "sonoras", "imperio", "premit", "ac", "vinclis", "et", "carcere", "frenat", "illi", "indignantes", "magno", "cum", "murmure", "montis", "circum", "claustra", "fremunt", "celsa", "sedet", "aeolus", "arce", "sceptra", "tenens", "mollitque", "animos", "et", "temperat", "iras", "ni", "faciat", "maria", "ac", "terras", "caelumque", "profundum", "quippe", "ferant", "rapidi", "secum", "verrantque", "per", "auras", "sed", "pater", "omnipotens", "speluncis", "abdidit", "atris", "hoc", "metuens", "molemque", "et", "montis", "insuper", "altos", "imposuit", "regemque", "dedit", "qui", "foedere", "certo", "et", "premere", "et", "laxas", "sciret", "dare", "iussus", "habenas"};
    private static final String[] DNS1 = new String[]{"corp", "your", "my", "sample", "company", "test", "any"};
    private static final String[] DNS2 = new String[]{"com", "org", "com", "gov", "org", "com", "org", "com", "edu"};
    private static final QName HREF = new QName("href");
    private static final QName ID = new QName("id");
    private static final QName XSI_TYPE = new QName("http://www.w3.org/2001/XMLSchema-instance", "type");
    private static final QName ENC_ARRAYTYPE = new QName("http://schemas.xmlsoap.org/soap/encoding/", "arrayType");
    private static final QName ENC_OFFSET = new QName("http://schemas.xmlsoap.org/soap/encoding/", "offset");
    private static final Set SKIPPED_SOAP_ATTRS = new HashSet<QName>(Arrays.asList(HREF, ID, ENC_OFFSET));
    private ArrayList _typeStack = new ArrayList();

    private SampleXmlUtil(boolean soapEnc) {
        this._soapEnc = soapEnc;
    }

    public static String createSampleForType(SchemaType sType) {
        XmlObject object = XmlObject.Factory.newInstance();
        XmlCursor cursor = object.newCursor();
        cursor.toNextToken();
        new SampleXmlUtil(false).createSampleForType(sType, cursor);
        XmlOptions options = new XmlOptions();
        options.put("SAVE_PRETTY_PRINT");
        options.put((Object)"SAVE_PRETTY_PRINT_INDENT", 2);
        options.put("SAVE_AGGRESSIVE_NAMESPACES");
        String result = object.xmlText(options);
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void createSampleForType(SchemaType stype, XmlCursor xmlc) {
        if (this._typeStack.contains(stype)) {
            return;
        }
        this._typeStack.add(stype);
        try {
            if (stype.isSimpleType() || stype.isURType()) {
                this.processSimpleType(stype, xmlc);
                return;
            }
            this.processAttributes(stype, xmlc);
            switch (stype.getContentType()) {
                case 0: 
                case 1: {
                    return;
                }
                case 2: {
                    this.processSimpleType(stype, xmlc);
                    return;
                }
                case 4: {
                    xmlc.insertChars(this.pick(WORDS) + " ");
                    if (stype.getContentModel() != null) {
                        this.processParticle(stype.getContentModel(), xmlc, true);
                    }
                    xmlc.insertChars(this.pick(WORDS));
                    return;
                }
                case 3: {
                    if (stype.getContentModel() == null) return;
                    this.processParticle(stype.getContentModel(), xmlc, false);
                    return;
                }
            }
            return;
        } finally {
            this._typeStack.remove(this._typeStack.size() - 1);
        }
    }

    private void processSimpleType(SchemaType stype, XmlCursor xmlc) {
        String sample = this.sampleDataForSimpleType(stype);
        xmlc.insertChars(sample);
    }

    private String sampleDataForSimpleType(SchemaType sType) {
        if (XmlObject.type.equals(sType)) {
            return "anyType";
        }
        if (XmlAnySimpleType.type.equals(sType)) {
            return "anySimpleType";
        }
        if (sType.getSimpleVariety() == 3) {
            SchemaType itemType = sType.getListItemType();
            StringBuffer sb = new StringBuffer();
            int length = this.pickLength(sType);
            if (length > 0) {
                sb.append(this.sampleDataForSimpleType(itemType));
            }
            for (int i = 1; i < length; ++i) {
                sb.append(' ');
                sb.append(this.sampleDataForSimpleType(itemType));
            }
            return sb.toString();
        }
        if (sType.getSimpleVariety() == 2) {
            SchemaType[] possibleTypes = sType.getUnionConstituentTypes();
            if (possibleTypes.length == 0) {
                return "";
            }
            return this.sampleDataForSimpleType(possibleTypes[this.pick(possibleTypes.length)]);
        }
        XmlAnySimpleType[] enumValues = sType.getEnumerationValues();
        if (enumValues != null && enumValues.length > 0) {
            return enumValues[this.pick(enumValues.length)].getStringValue();
        }
        switch (sType.getPrimitiveType().getBuiltinTypeCode()) {
            default: {
                return "";
            }
            case 1: 
            case 2: {
                return "anything";
            }
            case 3: {
                return this.pick(2) == 0 ? "true" : "false";
            }
            case 4: {
                String result = null;
                try {
                    result = new String(Base64.encode(this.formatToLength(this.pick(WORDS), sType).getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    // empty catch block
                }
                return result;
            }
            case 5: {
                return HexBin.encode(this.formatToLength(this.pick(WORDS), sType));
            }
            case 6: {
                return this.formatToLength("http://www." + this.pick(DNS1) + "." + this.pick(DNS2) + "/" + this.pick(WORDS) + "/" + this.pick(WORDS), sType);
            }
            case 7: {
                return this.formatToLength("qname", sType);
            }
            case 8: {
                return this.formatToLength("notation", sType);
            }
            case 9: {
                return "1.5E2";
            }
            case 10: {
                return "1.051732E7";
            }
            case 11: {
                switch (this.closestBuiltin(sType).getBuiltinTypeCode()) {
                    case 25: {
                        return this.formatDecimal("1", sType);
                    }
                    case 33: {
                        return this.formatDecimal("5", sType);
                    }
                    case 26: {
                        return this.formatDecimal("2", sType);
                    }
                    case 34: {
                        return this.formatDecimal("6", sType);
                    }
                    case 24: {
                        return this.formatDecimal("3", sType);
                    }
                    case 32: {
                        return this.formatDecimal("7", sType);
                    }
                    case 23: {
                        return this.formatDecimal("10", sType);
                    }
                    case 31: {
                        return this.formatDecimal("11", sType);
                    }
                    case 22: {
                        return this.formatDecimal("100", sType);
                    }
                    case 27: {
                        return this.formatDecimal("-200", sType);
                    }
                    case 28: {
                        return this.formatDecimal("-201", sType);
                    }
                    case 29: {
                        return this.formatDecimal("200", sType);
                    }
                    case 30: {
                        return this.formatDecimal("201", sType);
                    }
                }
                return this.formatDecimal("1000.00", sType);
            }
            case 12: {
                String result;
                switch (this.closestBuiltin(sType).getBuiltinTypeCode()) {
                    case 12: 
                    case 35: {
                        result = "string";
                        break;
                    }
                    case 36: {
                        result = "token";
                        break;
                    }
                    default: {
                        result = "string";
                    }
                }
                return this.formatToLength(result, sType);
            }
            case 13: {
                return this.formatDuration(sType);
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
        }
        return this.formatDate(sType);
    }

    private int pick(int n) {
        return this._picker.nextInt(n);
    }

    private String pick(String[] a) {
        return a[this.pick(a.length)];
    }

    private String pick(String[] a, int count) {
        if (count <= 0) {
            return "";
        }
        int i = this.pick(a.length);
        StringBuffer sb = new StringBuffer(a[i]);
        while (count-- > 0) {
            if (++i >= a.length) {
                i = 0;
            }
            sb.append(' ');
            sb.append(a[i]);
        }
        return sb.toString();
    }

    private String pickDigits(int digits) {
        StringBuffer sb = new StringBuffer();
        while (digits-- > 0) {
            sb.append(Integer.toString(this.pick(10)));
        }
        return sb.toString();
    }

    private int pickLength(SchemaType sType) {
        XmlInteger length = (XmlInteger)sType.getFacet(0);
        if (length != null) {
            return length.getBigIntegerValue().intValue();
        }
        XmlInteger min = (XmlInteger)sType.getFacet(1);
        XmlInteger max = (XmlInteger)sType.getFacet(2);
        int minInt = min == null ? 0 : min.getBigIntegerValue().intValue();
        int maxInt = max == null ? Integer.MAX_VALUE : max.getBigIntegerValue().intValue();
        if (minInt == 0 && maxInt >= 1) {
            minInt = 1;
        }
        if (maxInt > minInt + 2) {
            maxInt = minInt + 2;
        }
        if (maxInt < minInt) {
            maxInt = minInt;
        }
        return minInt + this.pick(maxInt - minInt);
    }

    private String formatToLength(String s, SchemaType sType) {
        String result = s;
        try {
            SimpleValue max;
            SimpleValue min = (SimpleValue)((Object)sType.getFacet(0));
            if (min == null) {
                min = (SimpleValue)((Object)sType.getFacet(1));
            }
            if (min != null) {
                int len = min.getIntValue();
                while (result.length() < len) {
                    result = result + result;
                }
            }
            if ((max = (SimpleValue)((Object)sType.getFacet(0))) == null) {
                max = (SimpleValue)((Object)sType.getFacet(2));
            }
            if (max != null) {
                int len = max.getIntValue();
                if (result.length() > len) {
                    result = result.substring(0, len);
                }
            }
        } catch (Exception e) {
            // empty catch block
        }
        return result;
    }

    private String formatDecimal(String start, SchemaType sType) {
        BigDecimal increment;
        BigDecimal result = new BigDecimal(start);
        XmlDecimal xmlD = (XmlDecimal)sType.getFacet(4);
        BigDecimal min = xmlD != null ? xmlD.getBigDecimalValue() : null;
        xmlD = (XmlDecimal)sType.getFacet(5);
        BigDecimal max = xmlD != null ? xmlD.getBigDecimalValue() : null;
        boolean minInclusive = true;
        boolean maxInclusive = true;
        xmlD = (XmlDecimal)sType.getFacet(3);
        if (xmlD != null) {
            BigDecimal minExcl = xmlD.getBigDecimalValue();
            if (min == null || min.compareTo(minExcl) < 0) {
                min = minExcl;
                minInclusive = false;
            }
        }
        if ((xmlD = (XmlDecimal)sType.getFacet(6)) != null) {
            BigDecimal maxExcl = xmlD.getBigDecimalValue();
            if (max == null || max.compareTo(maxExcl) > 0) {
                max = maxExcl;
                maxInclusive = false;
            }
        }
        xmlD = (XmlDecimal)sType.getFacet(7);
        int totalDigits = -1;
        if (xmlD != null) {
            totalDigits = xmlD.getBigDecimalValue().intValue();
            StringBuffer sb = new StringBuffer(totalDigits);
            for (int i = 0; i < totalDigits; ++i) {
                sb.append('9');
            }
            BigDecimal digitsLimit = new BigDecimal(sb.toString());
            if (max != null && max.compareTo(digitsLimit) > 0) {
                max = digitsLimit;
                maxInclusive = true;
            }
            digitsLimit = digitsLimit.negate();
            if (min != null && min.compareTo(digitsLimit) < 0) {
                min = digitsLimit;
                minInclusive = true;
            }
        }
        int sigMin = min == null ? 1 : result.compareTo(min);
        int sigMax = max == null ? -1 : result.compareTo(max);
        boolean minOk = sigMin > 0 || sigMin == 0 && minInclusive;
        boolean maxOk = sigMax < 0 || sigMax == 0 && maxInclusive;
        xmlD = (XmlDecimal)sType.getFacet(8);
        int fractionDigits = -1;
        if (xmlD == null) {
            increment = new BigDecimal(1);
        } else {
            fractionDigits = xmlD.getBigDecimalValue().intValue();
            if (fractionDigits > 0) {
                StringBuffer sb = new StringBuffer("0.");
                for (int i = 1; i < fractionDigits; ++i) {
                    sb.append('0');
                }
                sb.append('1');
                increment = new BigDecimal(sb.toString());
            } else {
                increment = new BigDecimal(1.0);
            }
        }
        if (!minOk || !maxOk) {
            if (minOk && !maxOk) {
                result = maxInclusive ? max : max.subtract(increment);
            } else if (!minOk && maxOk) {
                result = minInclusive ? min : min.add(increment);
            }
        }
        int digits = 0;
        BigDecimal ONE = new BigDecimal(BigInteger.ONE);
        BigDecimal n = result;
        while (n.abs().compareTo(ONE) >= 0) {
            n = n.movePointLeft(1);
            ++digits;
        }
        if (fractionDigits > 0) {
            result = totalDigits >= 0 ? result.setScale(Math.max(fractionDigits, totalDigits - digits)) : result.setScale(fractionDigits);
        } else if (fractionDigits == 0) {
            result = result.setScale(0);
        }
        return result.toString();
    }

    private String formatDuration(SchemaType sType) {
        XmlDuration d = (XmlDuration)sType.getFacet(4);
        GDuration minInclusive = null;
        if (d != null) {
            minInclusive = d.getGDurationValue();
        }
        d = (XmlDuration)sType.getFacet(5);
        GDuration maxInclusive = null;
        if (d != null) {
            maxInclusive = d.getGDurationValue();
        }
        d = (XmlDuration)sType.getFacet(3);
        GDuration minExclusive = null;
        if (d != null) {
            minExclusive = d.getGDurationValue();
        }
        d = (XmlDuration)sType.getFacet(6);
        GDuration maxExclusive = null;
        if (d != null) {
            maxExclusive = d.getGDurationValue();
        }
        GDurationBuilder gdurb = new GDurationBuilder();
        gdurb.setSecond(this.pick(800000));
        gdurb.setMonth(this.pick(20));
        if (minInclusive != null) {
            if (gdurb.getYear() < minInclusive.getYear()) {
                gdurb.setYear(minInclusive.getYear());
            }
            if (gdurb.getMonth() < minInclusive.getMonth()) {
                gdurb.setMonth(minInclusive.getMonth());
            }
            if (gdurb.getDay() < minInclusive.getDay()) {
                gdurb.setDay(minInclusive.getDay());
            }
            if (gdurb.getHour() < minInclusive.getHour()) {
                gdurb.setHour(minInclusive.getHour());
            }
            if (gdurb.getMinute() < minInclusive.getMinute()) {
                gdurb.setMinute(minInclusive.getMinute());
            }
            if (gdurb.getSecond() < minInclusive.getSecond()) {
                gdurb.setSecond(minInclusive.getSecond());
            }
            if (gdurb.getFraction().compareTo(minInclusive.getFraction()) < 0) {
                gdurb.setFraction(minInclusive.getFraction());
            }
        }
        if (maxInclusive != null) {
            if (gdurb.getYear() > maxInclusive.getYear()) {
                gdurb.setYear(maxInclusive.getYear());
            }
            if (gdurb.getMonth() > maxInclusive.getMonth()) {
                gdurb.setMonth(maxInclusive.getMonth());
            }
            if (gdurb.getDay() > maxInclusive.getDay()) {
                gdurb.setDay(maxInclusive.getDay());
            }
            if (gdurb.getHour() > maxInclusive.getHour()) {
                gdurb.setHour(maxInclusive.getHour());
            }
            if (gdurb.getMinute() > maxInclusive.getMinute()) {
                gdurb.setMinute(maxInclusive.getMinute());
            }
            if (gdurb.getSecond() > maxInclusive.getSecond()) {
                gdurb.setSecond(maxInclusive.getSecond());
            }
            if (gdurb.getFraction().compareTo(maxInclusive.getFraction()) > 0) {
                gdurb.setFraction(maxInclusive.getFraction());
            }
        }
        if (minExclusive != null) {
            if (gdurb.getYear() <= minExclusive.getYear()) {
                gdurb.setYear(minExclusive.getYear() + 1);
            }
            if (gdurb.getMonth() <= minExclusive.getMonth()) {
                gdurb.setMonth(minExclusive.getMonth() + 1);
            }
            if (gdurb.getDay() <= minExclusive.getDay()) {
                gdurb.setDay(minExclusive.getDay() + 1);
            }
            if (gdurb.getHour() <= minExclusive.getHour()) {
                gdurb.setHour(minExclusive.getHour() + 1);
            }
            if (gdurb.getMinute() <= minExclusive.getMinute()) {
                gdurb.setMinute(minExclusive.getMinute() + 1);
            }
            if (gdurb.getSecond() <= minExclusive.getSecond()) {
                gdurb.setSecond(minExclusive.getSecond() + 1);
            }
            if (gdurb.getFraction().compareTo(minExclusive.getFraction()) <= 0) {
                gdurb.setFraction(minExclusive.getFraction().add(new BigDecimal(0.001)));
            }
        }
        if (maxExclusive != null) {
            if (gdurb.getYear() > maxExclusive.getYear()) {
                gdurb.setYear(maxExclusive.getYear());
            }
            if (gdurb.getMonth() > maxExclusive.getMonth()) {
                gdurb.setMonth(maxExclusive.getMonth());
            }
            if (gdurb.getDay() > maxExclusive.getDay()) {
                gdurb.setDay(maxExclusive.getDay());
            }
            if (gdurb.getHour() > maxExclusive.getHour()) {
                gdurb.setHour(maxExclusive.getHour());
            }
            if (gdurb.getMinute() > maxExclusive.getMinute()) {
                gdurb.setMinute(maxExclusive.getMinute());
            }
            if (gdurb.getSecond() > maxExclusive.getSecond()) {
                gdurb.setSecond(maxExclusive.getSecond());
            }
            if (gdurb.getFraction().compareTo(maxExclusive.getFraction()) > 0) {
                gdurb.setFraction(maxExclusive.getFraction());
            }
        }
        gdurb.normalize();
        return gdurb.toString();
    }

    private String formatDate(SchemaType sType) {
        XmlCalendar c;
        GDateBuilder gdateb = new GDateBuilder(new Date(1000L * (long)this.pick(31536000) + (30L + (long)this.pick(20)) * 365L * 24L * 60L * 60L * 1000L));
        GDate min = null;
        GDate max = null;
        switch (sType.getPrimitiveType().getBuiltinTypeCode()) {
            case 14: {
                XmlAnySimpleType x = (XmlDateTime)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlDateTime)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlDateTime)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlDateTime)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
            case 15: {
                XmlAnySimpleType x = (XmlTime)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlTime)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlTime)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlTime)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
            case 16: {
                XmlAnySimpleType x = (XmlDate)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlDate)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlDate)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlDate)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
            case 17: {
                XmlAnySimpleType x = (XmlGYearMonth)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGYearMonth)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGYearMonth)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlGYearMonth)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
            case 18: {
                XmlAnySimpleType x = (XmlGYear)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGYear)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGYear)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlGYear)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
            case 19: {
                XmlAnySimpleType x = (XmlGMonthDay)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGMonthDay)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGMonthDay)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlGMonthDay)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
            case 20: {
                XmlAnySimpleType x = (XmlGDay)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGDay)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGDay)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlGDay)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
            case 21: {
                XmlAnySimpleType x = (XmlGMonth)sType.getFacet(4);
                if (x != null) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGMonth)sType.getFacet(3)) != null && (min == null || min.compareToGDate(x.getGDateValue()) <= 0)) {
                    min = x.getGDateValue();
                }
                if ((x = (XmlGMonth)sType.getFacet(5)) != null) {
                    max = x.getGDateValue();
                }
                if ((x = (XmlGMonth)sType.getFacet(6)) == null || max != null && max.compareToGDate(x.getGDateValue()) < 0) break;
                max = x.getGDateValue();
                break;
            }
        }
        if (min != null && max == null) {
            if (min.compareToGDate(gdateb) >= 0) {
                c = gdateb.getCalendar();
                ((Calendar)c).add(11, this.pick(8));
                gdateb = new GDateBuilder(c);
            }
        } else if (min == null && max != null) {
            if (max.compareToGDate(gdateb) <= 0) {
                c = gdateb.getCalendar();
                ((Calendar)c).add(11, 0 - this.pick(8));
                gdateb = new GDateBuilder(c);
            }
        } else if (min != null && max != null && (min.compareToGDate(gdateb) >= 0 || max.compareToGDate(gdateb) <= 0)) {
            c = min.getCalendar();
            XmlCalendar cmax = max.getCalendar();
            ((Calendar)c).add(11, 1);
            if (c.after(cmax)) {
                ((Calendar)c).add(11, -1);
                ((Calendar)c).add(12, 1);
                if (c.after(cmax)) {
                    ((Calendar)c).add(12, -1);
                    ((Calendar)c).add(13, 1);
                    if (c.after(cmax)) {
                        ((Calendar)c).add(13, -1);
                        ((Calendar)c).add(14, 1);
                        if (c.after(cmax)) {
                            ((Calendar)c).add(14, -1);
                        }
                    }
                }
            }
            gdateb = new GDateBuilder(c);
        }
        gdateb.setBuiltinTypeCode(sType.getPrimitiveType().getBuiltinTypeCode());
        if (this.pick(2) == 0) {
            gdateb.clearTimeZone();
        }
        return gdateb.toString();
    }

    private SchemaType closestBuiltin(SchemaType sType) {
        while (!sType.isBuiltinType()) {
            sType = sType.getBaseType();
        }
        return sType;
    }

    public static QName crackQName(String qName) {
        String name;
        String ns;
        int index = qName.lastIndexOf(58);
        if (index >= 0) {
            ns = qName.substring(0, index);
            name = qName.substring(index + 1);
        } else {
            ns = "";
            name = qName;
        }
        return new QName(ns, name);
    }

    private void processParticle(SchemaParticle sp, XmlCursor xmlc, boolean mixed) {
        int loop = this.determineMinMaxForSample(sp, xmlc);
        block7: while (loop-- > 0) {
            switch (sp.getParticleType()) {
                case 4: {
                    this.processElement(sp, xmlc, mixed);
                    continue block7;
                }
                case 3: {
                    this.processSequence(sp, xmlc, mixed);
                    continue block7;
                }
                case 2: {
                    this.processChoice(sp, xmlc, mixed);
                    continue block7;
                }
                case 1: {
                    this.processAll(sp, xmlc, mixed);
                    continue block7;
                }
                case 5: {
                    this.processWildCard(sp, xmlc, mixed);
                    continue block7;
                }
            }
        }
    }

    private int determineMinMaxForSample(SchemaParticle sp, XmlCursor xmlc) {
        int maxOccurs;
        int minOccurs = sp.getIntMinOccurs();
        if (minOccurs == (maxOccurs = sp.getIntMaxOccurs())) {
            return minOccurs;
        }
        int result = minOccurs;
        if (result == 0 && this._nElements < 1000) {
            result = 1;
        }
        if (sp.getParticleType() != 4) {
            return result;
        }
        if (sp.getMaxOccurs() == null) {
            if (minOccurs == 0) {
                xmlc.insertComment("Zero or more repetitions:");
            } else {
                xmlc.insertComment(minOccurs + " or more repetitions:");
            }
        } else if (sp.getIntMaxOccurs() > 1) {
            xmlc.insertComment(minOccurs + " to " + String.valueOf(sp.getMaxOccurs()) + " repetitions:");
        } else {
            xmlc.insertComment("Optional:");
        }
        return result;
    }

    private String getItemNameOrType(SchemaParticle sp, XmlCursor xmlc) {
        String elementOrTypeName = null;
        elementOrTypeName = sp.getParticleType() == 4 ? "Element (" + sp.getName().getLocalPart() + ")" : this.printParticleType(sp.getParticleType());
        return elementOrTypeName;
    }

    private void processElement(SchemaParticle sp, XmlCursor xmlc, boolean mixed) {
        SchemaLocalElement element = (SchemaLocalElement)((Object)sp);
        if (this._soapEnc) {
            xmlc.insertElement(element.getName().getLocalPart());
        } else {
            xmlc.insertElement(element.getName().getLocalPart(), element.getName().getNamespaceURI());
        }
        ++this._nElements;
        xmlc.toPrevToken();
        this.createSampleForType(element.getType(), xmlc);
        xmlc.toNextToken();
    }

    private void moveToken(int numToMove, XmlCursor xmlc) {
        for (int i = 0; i < Math.abs(numToMove); ++i) {
            if (numToMove < 0) {
                xmlc.toPrevToken();
                continue;
            }
            xmlc.toNextToken();
        }
    }

    private static final String formatQName(XmlCursor xmlc, QName qName) {
        XmlCursor parent = xmlc.newCursor();
        parent.toParent();
        String prefix = parent.prefixForNamespace(qName.getNamespaceURI());
        parent.dispose();
        String name = prefix == null || prefix.length() == 0 ? qName.getLocalPart() : prefix + ":" + qName.getLocalPart();
        return name;
    }

    private void processAttributes(SchemaType stype, XmlCursor xmlc) {
        QName typeName;
        if (this._soapEnc && (typeName = stype.getName()) != null) {
            xmlc.insertAttributeWithValue(XSI_TYPE, SampleXmlUtil.formatQName(xmlc, typeName));
        }
        SchemaProperty[] attrProps = stype.getAttributeProperties();
        for (int i = 0; i < attrProps.length; ++i) {
            SchemaProperty attr = attrProps[i];
            if (this._soapEnc) {
                if (SKIPPED_SOAP_ATTRS.contains(attr.getName())) continue;
                if (ENC_ARRAYTYPE.equals(attr.getName())) {
                    SOAPArrayType arrayType = ((SchemaWSDLArrayType)((Object)stype.getAttributeModel().getAttribute(attr.getName()))).getWSDLArrayType();
                    if (arrayType == null) continue;
                    xmlc.insertAttributeWithValue(attr.getName(), SampleXmlUtil.formatQName(xmlc, arrayType.getQName()) + arrayType.soap11DimensionString());
                    continue;
                }
            }
            String defaultValue = attr.getDefaultText();
            xmlc.insertAttributeWithValue(attr.getName(), defaultValue == null ? this.sampleDataForSimpleType(attr.getType()) : defaultValue);
        }
    }

    private void processSequence(SchemaParticle sp, XmlCursor xmlc, boolean mixed) {
        SchemaParticle[] spc = sp.getParticleChildren();
        for (int i = 0; i < spc.length; ++i) {
            this.processParticle(spc[i], xmlc, mixed);
            if (!mixed || i >= spc.length - 1) continue;
            xmlc.insertChars(this.pick(WORDS));
        }
    }

    private void processChoice(SchemaParticle sp, XmlCursor xmlc, boolean mixed) {
        SchemaParticle[] spc = sp.getParticleChildren();
        xmlc.insertComment("You have a CHOICE of the next " + String.valueOf(spc.length) + " items at this level");
        for (int i = 0; i < spc.length; ++i) {
            this.processParticle(spc[i], xmlc, mixed);
        }
    }

    private void processAll(SchemaParticle sp, XmlCursor xmlc, boolean mixed) {
        SchemaParticle[] spc = sp.getParticleChildren();
        for (int i = 0; i < spc.length; ++i) {
            this.processParticle(spc[i], xmlc, mixed);
            if (!mixed || i >= spc.length - 1) continue;
            xmlc.insertChars(this.pick(WORDS));
        }
    }

    private void processWildCard(SchemaParticle sp, XmlCursor xmlc, boolean mixed) {
        xmlc.insertComment("You may enter ANY elements at this point");
        xmlc.insertElement("AnyElement");
    }

    private static QName getClosestName(SchemaType sType) {
        while (sType.getName() == null) {
            sType = sType.getBaseType();
        }
        return sType.getName();
    }

    private String printParticleType(int particleType) {
        StringBuffer returnParticleType = new StringBuffer();
        returnParticleType.append("Schema Particle Type: ");
        switch (particleType) {
            case 1: {
                returnParticleType.append("ALL\n");
                break;
            }
            case 2: {
                returnParticleType.append("CHOICE\n");
                break;
            }
            case 4: {
                returnParticleType.append("ELEMENT\n");
                break;
            }
            case 3: {
                returnParticleType.append("SEQUENCE\n");
                break;
            }
            case 5: {
                returnParticleType.append("WILDCARD\n");
                break;
            }
            default: {
                returnParticleType.append("Schema Particle Type Unknown");
            }
        }
        return returnParticleType.toString();
    }
}

