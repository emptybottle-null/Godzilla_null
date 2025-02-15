/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.internal;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.base.Strings;
import com.jgoodies.common.internal.StringAndIconResourceAccessor;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.Icon;

public final class ResourceBundleAccessor
implements StringAndIconResourceAccessor {
    private final ResourceBundle bundle;

    public ResourceBundleAccessor(ResourceBundle bundle) {
        this.bundle = Preconditions.checkNotNull(bundle, "The %1$s must not be null.", "resource bundle");
    }

    @Override
    public Icon getIcon(String key) {
        return (Icon)this.bundle.getObject(key);
    }

    @Override
    public String getString(String key, Object ... args) {
        try {
            return Strings.get(this.bundle.getString(key), args);
        } catch (MissingResourceException mre) {
            return key;
        }
    }
}

