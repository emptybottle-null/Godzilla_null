/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.util.HashMap;
import org.apache.xmlbeans.XmlException;

public class XmlOptionCharEscapeMap {
    public static final int PREDEF_ENTITY = 0;
    public static final int DECIMAL = 1;
    public static final int HEXADECIMAL = 2;
    private HashMap _charMap = new HashMap();
    private static final HashMap _predefEntities = new HashMap();

    public boolean containsChar(char ch) {
        return this._charMap.containsKey(new Character(ch));
    }

    public void addMapping(char ch, int mode) throws XmlException {
        Character theChar = new Character(ch);
        switch (mode) {
            case 0: {
                String replString = (String)_predefEntities.get(theChar);
                if (replString == null) {
                    throw new XmlException("XmlOptionCharEscapeMap.addMapping(): the PREDEF_ENTITY mode can only be used for the following characters: <, >, &, \" and '");
                }
                this._charMap.put(theChar, replString);
                break;
            }
            case 1: {
                this._charMap.put(theChar, "&#" + ch + ";");
                break;
            }
            case 2: {
                String hexCharPoint = Integer.toHexString(ch);
                this._charMap.put(theChar, "&#x" + hexCharPoint + ";");
                break;
            }
            default: {
                throw new XmlException("XmlOptionCharEscapeMap.addMapping(): mode must be PREDEF_ENTITY, DECIMAL or HEXADECIMAL");
            }
        }
    }

    public void addMappings(char ch1, char ch2, int mode) throws XmlException {
        if (ch1 > ch2) {
            throw new XmlException("XmlOptionCharEscapeMap.addMappings(): ch1 must be <= ch2");
        }
        for (char c = ch1; c <= ch2; c = (char)(c + '\u0001')) {
            this.addMapping(c, mode);
        }
    }

    public String getEscapedString(char ch) {
        return (String)this._charMap.get(new Character(ch));
    }

    static {
        _predefEntities.put(new Character('<'), "&lt;");
        _predefEntities.put(new Character('>'), "&gt;");
        _predefEntities.put(new Character('&'), "&amp;");
        _predefEntities.put(new Character('\''), "&apos;");
        _predefEntities.put(new Character('\"'), "&quot;");
    }
}

