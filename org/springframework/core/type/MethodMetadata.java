/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type;

import org.springframework.core.type.AnnotatedTypeMetadata;

public interface MethodMetadata
extends AnnotatedTypeMetadata {
    public String getMethodName();

    public String getDeclaringClassName();

    public String getReturnTypeName();

    public boolean isAbstract();

    public boolean isStatic();

    public boolean isFinal();

    public boolean isOverridable();
}

