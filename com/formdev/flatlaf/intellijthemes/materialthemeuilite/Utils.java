/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Utils {
    static final Logger LOG = Logger.getLogger(FlatLaf.class.getName());

    Utils() {
    }

    static IntelliJTheme loadTheme(String name) {
        try {
            return new IntelliJTheme(Utils.class.getResourceAsStream("/com/formdev/flatlaf/intellijthemes/themes/material-theme-ui-lite/" + name));
        } catch (IOException ex) {
            String msg = "FlatLaf: Failed to load IntelliJ theme '" + name + "'";
            LOG.log(Level.SEVERE, msg, ex);
            throw new RuntimeException(msg, ex);
        }
    }
}

