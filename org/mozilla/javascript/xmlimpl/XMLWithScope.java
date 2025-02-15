/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.XMLLibImpl;
import org.mozilla.javascript.xmlimpl.XMLList;

final class XMLWithScope
extends NativeWith {
    private static final long serialVersionUID = -696429282095170887L;
    private XMLLibImpl lib;
    private int _currIndex;
    private XMLList _xmlList;
    private XMLObject _dqPrototype;

    XMLWithScope(XMLLibImpl lib, Scriptable parent, XMLObject prototype) {
        super(parent, prototype);
        this.lib = lib;
    }

    void initAsDotQuery() {
        XMLList xl;
        XMLObject prototype = (XMLObject)this.getPrototype();
        this._currIndex = 0;
        this._dqPrototype = prototype;
        if (prototype instanceof XMLList && (xl = (XMLList)prototype).length() > 0) {
            this.setPrototype((Scriptable)xl.get(0, null));
        }
        this._xmlList = this.lib.newXMLList();
    }

    @Override
    protected Object updateDotQuery(boolean value) {
        XMLObject seed = this._dqPrototype;
        XMLList xmlL = this._xmlList;
        if (seed instanceof XMLList) {
            XMLList orgXmlL = (XMLList)seed;
            int idx = this._currIndex;
            if (value) {
                xmlL.addToList(orgXmlL.get(idx, null));
            }
            if (++idx < orgXmlL.length()) {
                this._currIndex = idx;
                this.setPrototype((Scriptable)orgXmlL.get(idx, null));
                return null;
            }
        } else if (value) {
            xmlL.addToList(seed);
        }
        return xmlL;
    }
}

