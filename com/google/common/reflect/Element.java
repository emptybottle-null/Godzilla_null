/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.checkerframework.checker.nullness.qual.Nullable;

class Element
extends AccessibleObject
implements Member {
    private final AccessibleObject accessibleObject;
    private final Member member;

    <M extends AccessibleObject> Element(M member) {
        Preconditions.checkNotNull(member);
        this.accessibleObject = member;
        this.member = (Member)((Object)member);
    }

    public TypeToken<?> getOwnerType() {
        return TypeToken.of(this.getDeclaringClass());
    }

    @Override
    public final boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return this.accessibleObject.isAnnotationPresent(annotationClass);
    }

    public final <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return this.accessibleObject.getAnnotation(annotationClass);
    }

    @Override
    public final Annotation[] getAnnotations() {
        return this.accessibleObject.getAnnotations();
    }

    @Override
    public final Annotation[] getDeclaredAnnotations() {
        return this.accessibleObject.getDeclaredAnnotations();
    }

    @Override
    public final void setAccessible(boolean flag) throws SecurityException {
        this.accessibleObject.setAccessible(flag);
    }

    @Override
    public final boolean isAccessible() {
        return this.accessibleObject.isAccessible();
    }

    @Override
    public Class<?> getDeclaringClass() {
        return this.member.getDeclaringClass();
    }

    @Override
    public final String getName() {
        return this.member.getName();
    }

    @Override
    public final int getModifiers() {
        return this.member.getModifiers();
    }

    @Override
    public final boolean isSynthetic() {
        return this.member.isSynthetic();
    }

    public final boolean isPublic() {
        return Modifier.isPublic(this.getModifiers());
    }

    public final boolean isProtected() {
        return Modifier.isProtected(this.getModifiers());
    }

    public final boolean isPackagePrivate() {
        return !this.isPrivate() && !this.isPublic() && !this.isProtected();
    }

    public final boolean isPrivate() {
        return Modifier.isPrivate(this.getModifiers());
    }

    public final boolean isStatic() {
        return Modifier.isStatic(this.getModifiers());
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this.getModifiers());
    }

    public final boolean isAbstract() {
        return Modifier.isAbstract(this.getModifiers());
    }

    public final boolean isNative() {
        return Modifier.isNative(this.getModifiers());
    }

    public final boolean isSynchronized() {
        return Modifier.isSynchronized(this.getModifiers());
    }

    final boolean isVolatile() {
        return Modifier.isVolatile(this.getModifiers());
    }

    final boolean isTransient() {
        return Modifier.isTransient(this.getModifiers());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Element) {
            Element that = (Element)obj;
            return this.getOwnerType().equals(that.getOwnerType()) && this.member.equals(that.member);
        }
        return false;
    }

    public int hashCode() {
        return this.member.hashCode();
    }

    public String toString() {
        return this.member.toString();
    }
}

