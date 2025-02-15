/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

import org.apache.xmlbeans.impl.soap.SOAPElement;

public interface SOAPHeaderElement
extends SOAPElement {
    public void setActor(String var1);

    public String getActor();

    public void setMustUnderstand(boolean var1);

    public boolean getMustUnderstand();
}

