/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JParameter;
import org.apache.xmlbeans.impl.jam.mutable.MMember;

public interface MParameter
extends JParameter,
MMember {
    public void setType(String var1);

    public void setType(JClass var1);

    public void setUnqualifiedType(String var1);
}

