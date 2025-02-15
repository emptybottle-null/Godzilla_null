/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.internal;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.internal.StringResourceAccessor;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class StringResourceAccess {
    public static String getResourceString(StringResourceAccessor accessor, String key, Object ... args) {
        Preconditions.checkNotNull(accessor, "To use the internationalization support you must provide a ResourceBundle, ResourceMap, or a StringResourceAccessor. See #resources.");
        try {
            return accessor.getString(key, args);
        } catch (MissingResourceException ex) {
            Logger.getLogger(StringResourceAccess.class.getName()).log(Level.WARNING, "Missing internationalized text", ex);
            return key;
        }
    }
}

