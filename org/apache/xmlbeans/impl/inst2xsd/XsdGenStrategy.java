/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.inst2xsd;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;

public interface XsdGenStrategy {
    public void processDoc(XmlObject[] var1, Inst2XsdOptions var2, TypeSystemHolder var3);
}

