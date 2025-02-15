/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception.util;

import java.util.Locale;
import org.apache.commons.math3.exception.util.Localizable;

public class DummyLocalizable
implements Localizable {
    private static final long serialVersionUID = 8843275624471387299L;
    private final String source;

    public DummyLocalizable(String source) {
        this.source = source;
    }

    public String getSourceString() {
        return this.source;
    }

    public String getLocalizedString(Locale locale) {
        return this.source;
    }

    public String toString() {
        return this.source;
    }
}

