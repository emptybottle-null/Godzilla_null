/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.XMLChar;

public class XmlObjectList {
    private final XmlObject[] _objects;

    public XmlObjectList(int objectCount) {
        this._objects = new XmlObject[objectCount];
    }

    public boolean set(XmlObject o, int index) {
        if (this._objects[index] != null) {
            return false;
        }
        this._objects[index] = o;
        return true;
    }

    public boolean filled() {
        for (int i = 0; i < this._objects.length; ++i) {
            if (this._objects[i] != null) continue;
            return false;
        }
        return true;
    }

    public int unfilled() {
        for (int i = 0; i < this._objects.length; ++i) {
            if (this._objects[i] != null) continue;
            return i;
        }
        return -1;
    }

    public boolean equals(Object o) {
        if (!(o instanceof XmlObjectList)) {
            return false;
        }
        XmlObjectList other = (XmlObjectList)o;
        if (other._objects.length != this._objects.length) {
            return false;
        }
        for (int i = 0; i < this._objects.length; ++i) {
            if (this._objects[i] == null || other._objects[i] == null) {
                return false;
            }
            if (this._objects[i].valueEquals(other._objects[i])) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        int h = 0;
        for (int i = 0; i < this._objects.length; ++i) {
            if (this._objects[i] == null) continue;
            h = 31 * h + this._objects[i].valueHashCode();
        }
        return h;
    }

    private static String prettytrim(String s) {
        int start;
        int end;
        for (end = s.length(); end > 0 && XMLChar.isSpace(s.charAt(end - 1)); --end) {
        }
        for (start = 0; start < end && XMLChar.isSpace(s.charAt(start)); ++start) {
        }
        return s.substring(start, end);
    }

    public String toString() {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < this._objects.length; ++i) {
            if (i != 0) {
                b.append(" ");
            }
            b.append(XmlObjectList.prettytrim(((SimpleValue)this._objects[i]).getStringValue()));
        }
        return b.toString();
    }
}

