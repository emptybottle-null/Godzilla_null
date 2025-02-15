/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.inst2xsd;

import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy;
import org.apache.xmlbeans.impl.inst2xsd.XsdGenStrategy;
import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;

public class SalamiSliceStrategy
extends RussianDollStrategy
implements XsdGenStrategy {
    @Override
    protected void checkIfElementReferenceIsNeeded(Element child, String parentNamespace, TypeSystemHolder typeSystemHolder, Inst2XsdOptions options) {
        Element referencedElem = new Element();
        referencedElem.setGlobal(true);
        referencedElem.setName(child.getName());
        referencedElem.setType(child.getType());
        if (child.isNillable()) {
            referencedElem.setNillable(true);
            child.setNillable(false);
        }
        referencedElem = this.addGlobalElement(referencedElem, typeSystemHolder, options);
        child.setRef(referencedElem);
    }
}

