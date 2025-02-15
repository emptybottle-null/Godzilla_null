/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.inst2xsd;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy;
import org.apache.xmlbeans.impl.inst2xsd.XsdGenStrategy;
import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.inst2xsd.util.Type;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;

public class VenetianBlindStrategy
extends RussianDollStrategy
implements XsdGenStrategy {
    @Override
    protected void checkIfReferenceToGlobalTypeIsNeeded(Element elem, TypeSystemHolder typeSystemHolder, Inst2XsdOptions options) {
        Type elemType = elem.getType();
        QName elemName = elem.getName();
        if (elemType.isGlobal()) {
            return;
        }
        if (elemType.isComplexType()) {
            int i = 0;
            while (true) {
                elemType.setName(new QName(elemName.getNamespaceURI(), elemName.getLocalPart() + "Type" + (i == 0 ? "" : "" + i)));
                Type candidate = typeSystemHolder.getGlobalType(elemType.getName());
                if (candidate == null) {
                    elemType.setGlobal(true);
                    typeSystemHolder.addGlobalType(elemType);
                    break;
                }
                if (this.compatibleTypes(candidate, elemType)) {
                    this.combineTypes(candidate, elemType, options);
                    elem.setType(candidate);
                    break;
                }
                ++i;
            }
        }
    }

    private boolean compatibleTypes(Type elemType, Type candidate) {
        if (elemType == candidate) {
            return true;
        }
        return true;
    }
}

