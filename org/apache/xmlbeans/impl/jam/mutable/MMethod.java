/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.mutable.MInvokable;

public interface MMethod
extends JMethod,
MInvokable {
    public void setReturnType(String var1);

    public void setUnqualifiedReturnType(String var1);

    public void setReturnType(JClass var1);
}

