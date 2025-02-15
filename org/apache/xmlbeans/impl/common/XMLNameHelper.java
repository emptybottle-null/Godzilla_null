/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.UnsupportedEncodingException;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XmlNameImpl;
import org.apache.xmlbeans.xml.stream.XMLName;

public class XMLNameHelper {
    private static final char[] hexdigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static QName getQName(XMLName xmlName) {
        if (xmlName == null) {
            return null;
        }
        return QNameHelper.forLNS(xmlName.getLocalName(), xmlName.getNamespaceUri());
    }

    public static XMLName forLNS(String localname, String uri) {
        if (uri == null) {
            uri = "";
        }
        return new XmlNameImpl(uri, localname);
    }

    public static XMLName forLN(String localname) {
        return new XmlNameImpl("", localname);
    }

    public static XMLName forPretty(String pretty, int offset) {
        int at = pretty.indexOf(64, offset);
        if (at < 0) {
            return new XmlNameImpl("", pretty.substring(offset));
        }
        return new XmlNameImpl(pretty.substring(at + 1), pretty.substring(offset, at));
    }

    public static String pretty(XMLName name) {
        if (name == null) {
            return "null";
        }
        if (name.getNamespaceUri() == null || name.getNamespaceUri().length() == 0) {
            return name.getLocalName();
        }
        return name.getLocalName() + "@" + name.getNamespaceUri();
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
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (XMLNameHelper.isSafe(ch)) {
                result.append(ch);
                continue;
            }
            byte[] utf8 = null;
            try {
                utf8 = s.substring(i, i + 1).getBytes("UTF-8");
                for (int j = 0; j < utf8.length; ++j) {
                    result.append('_');
                    result.append(hexdigits[utf8[j] >> 4 & 0xF]);
                    result.append(hexdigits[utf8[j] & 0xF]);
                }
                continue;
            } catch (UnsupportedEncodingException uee) {
                result.append("_BAD_UTF8_CHAR");
            }
        }
        return result.toString();
    }

    public static String hexsafedir(XMLName name) {
        if (name.getNamespaceUri() == null || name.getNamespaceUri().length() == 0) {
            return "_nons/" + XMLNameHelper.hexsafe(name.getLocalName());
        }
        return XMLNameHelper.hexsafe(name.getNamespaceUri()) + "/" + XMLNameHelper.hexsafe(name.getLocalName());
    }
}

