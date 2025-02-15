/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.common.XMLNameHelper;
import org.apache.xmlbeans.xml.stream.XMLName;

public class QNameHelper {
    private static final Map WELL_KNOWN_PREFIXES = QNameHelper.buildWKP();
    private static final char[] hexdigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final int MAX_NAME_LENGTH = 64;
    public static final String URI_SHA1_PREFIX = "URI_SHA_1_";

    public static XMLName getXMLName(QName qname) {
        if (qname == null) {
            return null;
        }
        return XMLNameHelper.forLNS(qname.getLocalPart(), qname.getNamespaceURI());
    }

    public static QName forLNS(String localname, String uri) {
        if (uri == null) {
            uri = "";
        }
        return new QName(uri, localname);
    }

    public static QName forLN(String localname) {
        return new QName("", localname);
    }

    public static QName forPretty(String pretty, int offset) {
        int at = pretty.indexOf(64, offset);
        if (at < 0) {
            return new QName("", pretty.substring(offset));
        }
        return new QName(pretty.substring(at + 1), pretty.substring(offset, at));
    }

    public static String pretty(QName name) {
        if (name == null) {
            return "null";
        }
        if (name.getNamespaceURI() == null || name.getNamespaceURI().length() == 0) {
            return name.getLocalPart();
        }
        return name.getLocalPart() + "@" + name.getNamespaceURI();
    }

    private static boolean isSafe(int c) {
        if (c >= 97 && c <= 122) {
            return true;
        }
        if (c >= 65 && c <= 90) {
            return true;
        }
        return c >= 48 && c <= 57;
    }

    public static String hexsafe(String s) {
        int j;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (QNameHelper.isSafe(ch)) {
                result.append(ch);
                continue;
            }
            byte[] utf8 = null;
            try {
                utf8 = s.substring(i, i + 1).getBytes("UTF-8");
                for (j = 0; j < utf8.length; ++j) {
                    result.append('_');
                    result.append(hexdigits[utf8[j] >> 4 & 0xF]);
                    result.append(hexdigits[utf8[j] & 0xF]);
                }
                continue;
            } catch (UnsupportedEncodingException uee) {
                result.append("_BAD_UTF8_CHAR");
            }
        }
        if (result.length() <= 64) {
            return result.toString();
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] inputBytes = null;
            try {
                inputBytes = s.getBytes("UTF-8");
            } catch (UnsupportedEncodingException uee) {
                inputBytes = new byte[]{};
            }
            byte[] digest = md.digest(inputBytes);
            assert (digest.length == 20);
            result = new StringBuffer(URI_SHA1_PREFIX);
            for (j = 0; j < digest.length; ++j) {
                result.append(hexdigits[digest[j] >> 4 & 0xF]);
                result.append(hexdigits[digest[j] & 0xF]);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Using in a JDK without an SHA implementation");
        }
    }

    public static String hexsafedir(QName name) {
        if (name.getNamespaceURI() == null || name.getNamespaceURI().length() == 0) {
            return "_nons/" + QNameHelper.hexsafe(name.getLocalPart());
        }
        return QNameHelper.hexsafe(name.getNamespaceURI()) + "/" + QNameHelper.hexsafe(name.getLocalPart());
    }

    private static Map buildWKP() {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("http://www.w3.org/XML/1998/namespace", "xml");
        result.put("http://www.w3.org/2001/XMLSchema", "xs");
        result.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        result.put("http://schemas.xmlsoap.org/wsdl/", "wsdl");
        result.put("http://schemas.xmlsoap.org/soap/encoding/", "soapenc");
        result.put("http://schemas.xmlsoap.org/soap/envelope/", "soapenv");
        return Collections.unmodifiableMap(result);
    }

    public static String readable(SchemaType sType) {
        return QNameHelper.readable(sType, WELL_KNOWN_PREFIXES);
    }

    public static String readable(SchemaType sType, Map nsPrefix) {
        if (sType.getName() != null) {
            return QNameHelper.readable(sType.getName(), nsPrefix);
        }
        if (sType.isAttributeType()) {
            return "attribute type " + QNameHelper.readable(sType.getAttributeTypeAttributeName(), nsPrefix);
        }
        if (sType.isDocumentType()) {
            return "document type " + QNameHelper.readable(sType.getDocumentElementName(), nsPrefix);
        }
        if (sType.isNoType() || sType.getOuterType() == null) {
            return "invalid type";
        }
        SchemaType outerType = sType.getOuterType();
        SchemaField container = sType.getContainerField();
        if (outerType.isAttributeType()) {
            return "type of attribute " + QNameHelper.readable(container.getName(), nsPrefix);
        }
        if (outerType.isDocumentType()) {
            return "type of element " + QNameHelper.readable(container.getName(), nsPrefix);
        }
        if (container != null) {
            if (container.isAttribute()) {
                return "type of " + container.getName().getLocalPart() + " attribute in " + QNameHelper.readable(outerType, nsPrefix);
            }
            return "type of " + container.getName().getLocalPart() + " element in " + QNameHelper.readable(outerType, nsPrefix);
        }
        if (outerType.getBaseType() == sType) {
            return "base type of " + QNameHelper.readable(outerType, nsPrefix);
        }
        if (outerType.getSimpleVariety() == 3) {
            return "item type of " + QNameHelper.readable(outerType, nsPrefix);
        }
        if (outerType.getSimpleVariety() == 2) {
            return "member type " + sType.getAnonymousUnionMemberOrdinal() + " of " + QNameHelper.readable(outerType, nsPrefix);
        }
        return "inner type in " + QNameHelper.readable(outerType, nsPrefix);
    }

    public static String readable(QName name) {
        return QNameHelper.readable(name, WELL_KNOWN_PREFIXES);
    }

    public static String readable(QName name, Map prefixes) {
        if (name.getNamespaceURI().length() == 0) {
            return name.getLocalPart();
        }
        String prefix = (String)prefixes.get(name.getNamespaceURI());
        if (prefix != null) {
            return prefix + ":" + name.getLocalPart();
        }
        return name.getLocalPart() + " in namespace " + name.getNamespaceURI();
    }

    public static String suggestPrefix(String namespace) {
        String result = (String)WELL_KNOWN_PREFIXES.get(namespace);
        if (result != null) {
            return result;
        }
        int len = namespace.length();
        int i = namespace.lastIndexOf(47);
        if (i > 0 && i == namespace.length() - 1) {
            len = i;
            i = namespace.lastIndexOf(47, i - 1);
        }
        if (namespace.startsWith("www.", ++i)) {
            i += 4;
        }
        while (i < len && !XMLChar.isNCNameStart(namespace.charAt(i))) {
            ++i;
        }
        for (int end = i + 1; end < len; ++end) {
            if (XMLChar.isNCName(namespace.charAt(end)) && Character.isLetterOrDigit(namespace.charAt(end))) continue;
            len = end;
            break;
        }
        if (namespace.length() >= i + 3 && QNameHelper.startsWithXml(namespace, i)) {
            if (namespace.length() >= i + 4) {
                return "x" + Character.toLowerCase(namespace.charAt(i + 3));
            }
            return "ns";
        }
        if (len - i > 4) {
            len = QNameHelper.isVowel(namespace.charAt(i + 2)) && !QNameHelper.isVowel(namespace.charAt(i + 3)) ? i + 4 : i + 3;
        }
        if (len - i == 0) {
            return "ns";
        }
        return namespace.substring(i, len).toLowerCase();
    }

    private static boolean startsWithXml(String s, int i) {
        if (s.length() < i + 3) {
            return false;
        }
        if (s.charAt(i) != 'X' && s.charAt(i) != 'x') {
            return false;
        }
        if (s.charAt(i + 1) != 'M' && s.charAt(i + 1) != 'm') {
            return false;
        }
        return s.charAt(i + 2) == 'L' || s.charAt(i + 2) == 'l';
    }

    private static boolean isVowel(char ch) {
        switch (ch) {
            case 'A': 
            case 'E': 
            case 'I': 
            case 'O': 
            case 'U': 
            case 'a': 
            case 'e': 
            case 'i': 
            case 'o': 
            case 'u': {
                return true;
            }
        }
        return false;
    }

    public static String namespace(SchemaType sType) {
        while (sType != null) {
            if (sType.getName() != null) {
                return sType.getName().getNamespaceURI();
            }
            if (sType.getContainerField() != null && sType.getContainerField().getName().getNamespaceURI().length() > 0) {
                return sType.getContainerField().getName().getNamespaceURI();
            }
            sType = sType.getOuterType();
        }
        return "";
    }

    public static String getLocalPart(String qname) {
        int index = qname.indexOf(58);
        return index < 0 ? qname : qname.substring(index + 1);
    }

    public static String getPrefixPart(String qname) {
        int index = qname.indexOf(58);
        return index >= 0 ? qname.substring(0, index) : "";
    }
}

