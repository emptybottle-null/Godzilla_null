/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.lang.reflect.Modifier;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JElement;
import org.apache.xmlbeans.impl.jam.JMember;
import org.apache.xmlbeans.impl.jam.internal.elements.AnnotatedElementImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementImpl;
import org.apache.xmlbeans.impl.jam.mutable.MMember;

public abstract class MemberImpl
extends AnnotatedElementImpl
implements MMember {
    private int mModifiers = 0;

    protected MemberImpl(ElementImpl parent) {
        super(parent);
    }

    protected MemberImpl(ElementContext ctx) {
        super(ctx);
    }

    @Override
    public JClass getContainingClass() {
        JElement p = this.getParent();
        if (p instanceof JClass) {
            return (JClass)p;
        }
        if (p instanceof JMember) {
            return ((JMember)p).getContainingClass();
        }
        return null;
    }

    @Override
    public int getModifiers() {
        return this.mModifiers;
    }

    @Override
    public boolean isPackagePrivate() {
        return !this.isPrivate() && !this.isPublic() && !this.isProtected();
    }

    @Override
    public boolean isPrivate() {
        return Modifier.isPrivate(this.getModifiers());
    }

    @Override
    public boolean isProtected() {
        return Modifier.isProtected(this.getModifiers());
    }

    @Override
    public boolean isPublic() {
        return Modifier.isPublic(this.getModifiers());
    }

    @Override
    public void setModifiers(int modifiers) {
        this.mModifiers = modifiers;
    }
}

