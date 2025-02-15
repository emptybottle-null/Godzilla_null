/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.parser;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.impl.jam.internal.parser.ParamStruct;
import org.apache.xmlbeans.impl.jam.mutable.MInvokable;

public class ParamStructPool {
    private static final boolean VERBOSE = true;
    private List mList = new ArrayList();
    private int mLength = 0;

    public void setParametersOn(MInvokable e) {
        for (int i = 0; i < this.mLength; ++i) {
            ParamStruct struct = (ParamStruct)this.mList.get(i);
            struct.createParameter(e);
        }
    }

    public void add(String type, String name) {
        ++this.mLength;
        if (this.mLength >= this.mList.size()) {
            this.mList.add(new ParamStruct(type, name));
        } else {
            ((ParamStruct)this.mList.get(this.mLength)).init(type, name);
        }
    }

    public void clear() {
        this.mLength = 0;
    }
}

