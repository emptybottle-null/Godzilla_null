/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception.util;

import java.io.Serializable;
import java.util.Locale;

public interface Localizable
extends Serializable {
    public String getSourceString();

    public String getLocalizedString(Locale var1);
}

