/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.parser;

import org.apache.xmlbeans.impl.jam.mutable.MInvokable;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;

class ParamStruct {
    private String mName;
    private String mType;

    public ParamStruct(String type, String name) {
        this.init(type, name);
    }

    public void init(String type, String name) {
        this.mType = type;
        this.mName = name;
    }

    public MParameter createParameter(MInvokable e) {
        if (e == null) {
            throw new IllegalArgumentException("null invokable");
        }
        MParameter param = e.addNewParameter();
        param.setSimpleName(this.mName);
        param.setUnqualifiedType(this.mType);
        return param;
    }
}

