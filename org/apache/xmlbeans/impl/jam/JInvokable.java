/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JMember;
import org.apache.xmlbeans.impl.jam.JParameter;

public interface JInvokable
extends JMember {
    public JParameter[] getParameters();

    public JClass[] getExceptionTypes();
}

