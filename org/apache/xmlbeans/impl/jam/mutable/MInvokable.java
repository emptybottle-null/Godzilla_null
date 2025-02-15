/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JInvokable;
import org.apache.xmlbeans.impl.jam.mutable.MMember;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;

public interface MInvokable
extends JInvokable,
MMember {
    public void addException(String var1);

    public void addException(JClass var1);

    public void removeException(String var1);

    public void removeException(JClass var1);

    public MParameter addNewParameter();

    public void removeParameter(MParameter var1);

    public MParameter[] getMutableParameters();
}

