/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.soap;

import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public final class SOAPArrayType {
    private QName _type;
    private int[] _ranks;
    private int[] _dimensions;
    private static int[] EMPTY_INT_ARRAY = new int[0];

    public boolean isSameRankAs(SOAPArrayType otherType) {
        if (this._ranks.length != otherType._ranks.length) {
            return false;
        }
        for (int i = 0; i < this._ranks.length; ++i) {
            if (this._ranks[i] == otherType._ranks[i]) continue;
            return false;
        }
        return this._dimensions.length == otherType._dimensions.length;
    }

    public static int[] parseSoap11Index(String inbraces) {
        if (!(inbraces = XmlWhitespace.collapse(inbraces, 3)).startsWith("[") || !inbraces.endsWith("]")) {
            throw new IllegalArgumentException("Misformed SOAP 1.1 index: must be contained in braces []");
        }
        return SOAPArrayType.internalParseCommaIntString(inbraces.substring(1, inbraces.length() - 1));
    }

    private static int[] internalParseCommaIntString(String csl) {
        ArrayList<String> dimStrings = new ArrayList<String>();
        int i = 0;
        while (true) {
            int j;
            if ((j = csl.indexOf(44, i)) < 0) break;
            dimStrings.add(csl.substring(i, j));
            i = j + 1;
        }
        dimStrings.add(csl.substring(i));
        int[] result = new int[dimStrings.size()];
        i = 0;
        Iterator it = dimStrings.iterator();
        while (it.hasNext()) {
            String dimString = XmlWhitespace.collapse((String)it.next(), 3);
            if (dimString.equals("*") || dimString.equals("")) {
                result[i] = -1;
            } else {
                try {
                    result[i] = Integer.parseInt(dimString);
                } catch (Exception e) {
                    throw new XmlValueOutOfRangeException("Malformed integer in SOAP array index");
                }
            }
            ++i;
        }
        return result;
    }

    public SOAPArrayType(String s, PrefixResolver m) {
        String uri;
        int firstbrace = s.indexOf(91);
        if (firstbrace < 0) {
            throw new XmlValueOutOfRangeException();
        }
        String firstpart = XmlWhitespace.collapse(s.substring(0, firstbrace), 3);
        int firstcolon = firstpart.indexOf(58);
        String prefix = "";
        if (firstcolon >= 0) {
            prefix = firstpart.substring(0, firstcolon);
        }
        if ((uri = m.getNamespaceForPrefix(prefix)) == null) {
            throw new XmlValueOutOfRangeException();
        }
        this._type = QNameHelper.forLNS(firstpart.substring(firstcolon + 1), uri);
        this.initDimensions(s, firstbrace);
    }

    public SOAPArrayType(QName name, String dimensions) {
        int firstbrace = dimensions.indexOf(91);
        if (firstbrace < 0) {
            this._type = name;
            this._ranks = EMPTY_INT_ARRAY;
            dimensions = XmlWhitespace.collapse(dimensions, 3);
            String[] dimStrings = dimensions.split(" ");
            for (int i = 0; i < dimStrings.length; ++i) {
                String dimString = dimStrings[i];
                if (dimString.equals("*")) {
                    this._dimensions[i] = -1;
                    continue;
                }
                try {
                    this._dimensions[i] = Integer.parseInt(dimStrings[i]);
                    continue;
                } catch (Exception e) {
                    throw new XmlValueOutOfRangeException();
                }
            }
        } else {
            this._type = name;
            this.initDimensions(dimensions, firstbrace);
        }
    }

    public SOAPArrayType(SOAPArrayType nested, int[] dimensions) {
        this._type = nested._type;
        this._ranks = new int[nested._ranks.length + 1];
        System.arraycopy(nested._ranks, 0, this._ranks, 0, nested._ranks.length);
        this._ranks[this._ranks.length - 1] = nested._dimensions.length;
        this._dimensions = new int[dimensions.length];
        System.arraycopy(dimensions, 0, this._dimensions, 0, dimensions.length);
    }

    private void initDimensions(String s, int firstbrace) {
        ArrayList<String> braces = new ArrayList<String>();
        int lastbrace = -1;
        int i = firstbrace;
        while (i >= 0) {
            lastbrace = s.indexOf(93, i);
            if (lastbrace < 0) {
                throw new XmlValueOutOfRangeException();
            }
            braces.add(s.substring(i + 1, lastbrace));
            i = s.indexOf(91, lastbrace);
        }
        String trailer = s.substring(lastbrace + 1);
        if (!XmlWhitespace.isAllSpace(trailer)) {
            throw new XmlValueOutOfRangeException();
        }
        this._ranks = new int[braces.size() - 1];
        for (int i2 = 0; i2 < this._ranks.length; ++i2) {
            String commas = (String)braces.get(i2);
            int commacount = 0;
            for (int j = 0; j < commas.length(); ++j) {
                char ch = commas.charAt(j);
                if (ch == ',') {
                    ++commacount;
                    continue;
                }
                if (XmlWhitespace.isSpace(ch)) continue;
                throw new XmlValueOutOfRangeException();
            }
            this._ranks[i2] = commacount + 1;
        }
        this._dimensions = SOAPArrayType.internalParseCommaIntString((String)braces.get(braces.size() - 1));
    }

    public QName getQName() {
        return this._type;
    }

    public int[] getRanks() {
        int[] result = new int[this._ranks.length];
        System.arraycopy(this._ranks, 0, result, 0, result.length);
        return result;
    }

    public int[] getDimensions() {
        int[] result = new int[this._dimensions.length];
        System.arraycopy(this._dimensions, 0, result, 0, result.length);
        return result;
    }

    public boolean containsNestedArrays() {
        return this._ranks.length > 0;
    }

    public String soap11DimensionString() {
        return this.soap11DimensionString(this._dimensions);
    }

    public String soap11DimensionString(int[] actualDimensions) {
        int i;
        StringBuffer sb = new StringBuffer();
        for (i = 0; i < this._ranks.length; ++i) {
            sb.append('[');
            for (int j = 1; j < this._ranks[i]; ++j) {
                sb.append(',');
            }
            sb.append(']');
        }
        sb.append('[');
        for (i = 0; i < actualDimensions.length; ++i) {
            if (i > 0) {
                sb.append(',');
            }
            if (actualDimensions[i] < 0) continue;
            sb.append(actualDimensions[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    private SOAPArrayType() {
    }

    public static SOAPArrayType newSoap12Array(QName itemType, String arraySize) {
        int[] ranks = EMPTY_INT_ARRAY;
        arraySize = XmlWhitespace.collapse(arraySize, 3);
        String[] dimStrings = arraySize.split(" ");
        int[] dimensions = new int[dimStrings.length];
        for (int i = 0; i < dimStrings.length; ++i) {
            String dimString = dimStrings[i];
            if (i == 0 && dimString.equals("*")) {
                dimensions[i] = -1;
                continue;
            }
            try {
                dimensions[i] = Integer.parseInt(dimStrings[i]);
                continue;
            } catch (Exception e) {
                throw new XmlValueOutOfRangeException();
            }
        }
        SOAPArrayType sot = new SOAPArrayType();
        sot._ranks = ranks;
        sot._type = itemType;
        sot._dimensions = dimensions;
        return sot;
    }

    public String soap12DimensionString(int[] actualDimensions) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < actualDimensions.length; ++i) {
            if (i > 0) {
                sb.append(' ');
            }
            if (actualDimensions[i] < 0) continue;
            sb.append(actualDimensions[i]);
        }
        return sb.toString();
    }

    public SOAPArrayType nestedArrayType() {
        if (!this.containsNestedArrays()) {
            throw new IllegalStateException();
        }
        SOAPArrayType result = new SOAPArrayType();
        result._type = this._type;
        result._ranks = new int[this._ranks.length - 1];
        System.arraycopy(this._ranks, 0, result._ranks, 0, result._ranks.length);
        result._dimensions = new int[this._ranks[this._ranks.length - 1]];
        for (int i = 0; i < result._dimensions.length; ++i) {
            result._dimensions[i] = -1;
        }
        return result;
    }

    public int hashCode() {
        return this._type.hashCode() + this._dimensions.length + this._ranks.length + (this._dimensions.length == 0 ? 0 : this._dimensions[0]);
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
        SOAPArrayType sat = (SOAPArrayType)obj;
        if (!this._type.equals(sat._type)) {
            return false;
        }
        if (this._ranks.length != sat._ranks.length) {
            return false;
        }
        if (this._dimensions.length != sat._dimensions.length) {
            return false;
        }
        for (i = 0; i < this._ranks.length; ++i) {
            if (this._ranks[i] == sat._ranks[i]) continue;
            return false;
        }
        for (i = 0; i < this._dimensions.length; ++i) {
            if (this._dimensions[i] == sat._dimensions[i]) continue;
            return false;
        }
        return true;
    }
}

