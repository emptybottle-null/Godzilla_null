/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type;

import org.springframework.lang.Nullable;

public interface ClassMetadata {
    public String getClassName();

    public boolean isInterface();

    public boolean isAnnotation();

    public boolean isAbstract();

    default public boolean isConcrete() {
        return !this.isInterface() && !this.isAbstract();
    }

    public boolean isFinal();

    public boolean isIndependent();

    default public boolean hasEnclosingClass() {
        return this.getEnclosingClassName() != null;
    }

    @Nullable
    public String getEnclosingClassName();

    default public boolean hasSuperClass() {
        return this.getSuperClassName() != null;
    }

    @Nullable
    public String getSuperClassName();

    public String[] getInterfaceNames();

    public String[] getMemberClassNames();
}

