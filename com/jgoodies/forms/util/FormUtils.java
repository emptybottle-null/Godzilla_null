/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.util;

import com.jgoodies.common.base.SystemUtils;
import com.jgoodies.forms.util.DefaultUnitConverter;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

public final class FormUtils {
    private static LookAndFeel cachedLookAndFeel;
    private static Boolean cachedIsLafAqua;

    private FormUtils() {
    }

    public static boolean isLafAqua() {
        FormUtils.ensureValidCache();
        if (cachedIsLafAqua == null) {
            cachedIsLafAqua = SystemUtils.isLafAqua();
        }
        return cachedIsLafAqua;
    }

    public static void clearLookAndFeelBasedCaches() {
        cachedIsLafAqua = null;
        DefaultUnitConverter.getInstance().clearCache();
    }

    static void ensureValidCache() {
        LookAndFeel currentLookAndFeel = UIManager.getLookAndFeel();
        if (currentLookAndFeel != cachedLookAndFeel) {
            FormUtils.clearLookAndFeelBasedCaches();
            cachedLookAndFeel = currentLookAndFeel;
        }
    }
}

