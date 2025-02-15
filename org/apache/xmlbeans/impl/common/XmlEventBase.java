/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import org.apache.xmlbeans.xml.stream.XMLEvent;
import org.apache.xmlbeans.xml.stream.events.ElementTypeNames;

public abstract class XmlEventBase
implements XMLEvent {
    private int _type;

    public XmlEventBase() {
    }

    public XmlEventBase(int type) {
        this._type = type;
    }

    public void setType(int type) {
        this._type = type;
    }

    @Override
    public int getType() {
        return this._type;
    }

    @Override
    public String getTypeAsString() {
        return ElementTypeNames.getName(this._type);
    }

    @Override
    public boolean isStartElement() {
        return this._type == 2;
    }

    @Override
    public boolean isEndElement() {
        return this._type == 4;
    }

    @Override
    public boolean isEntityReference() {
        return this._type == 8192;
    }

    @Override
    public boolean isStartPrefixMapping() {
        return this._type == 1024;
    }

    @Override
    public boolean isEndPrefixMapping() {
        return this._type == 2048;
    }

    @Override
    public boolean isChangePrefixMapping() {
        return this._type == 4096;
    }

    @Override
    public boolean isProcessingInstruction() {
        return this._type == 8;
    }

    @Override
    public boolean isCharacterData() {
        return this._type == 16;
    }

    @Override
    public boolean isSpace() {
        return this._type == 64;
    }

    @Override
    public boolean isNull() {
        return this._type == 128;
    }

    @Override
    public boolean isStartDocument() {
        return this._type == 256;
    }

    @Override
    public boolean isEndDocument() {
        return this._type == 512;
    }
}

