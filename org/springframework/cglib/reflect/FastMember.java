/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.reflect;

import java.lang.reflect.Member;
import org.springframework.cglib.reflect.FastClass;

public abstract class FastMember {
    protected FastClass fc;
    protected Member member;
    protected int index;

    protected FastMember(FastClass fc, Member member, int index) {
        this.fc = fc;
        this.member = member;
        this.index = index;
    }

    public abstract Class[] getParameterTypes();

    public abstract Class[] getExceptionTypes();

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.member.getName();
    }

    public Class getDeclaringClass() {
        return this.fc.getJavaClass();
    }

    public int getModifiers() {
        return this.member.getModifiers();
    }

    public String toString() {
        return this.member.toString();
    }

    public int hashCode() {
        return this.member.hashCode();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof FastMember)) {
            return false;
        }
        return this.member.equals(((FastMember)o).member);
    }
}

