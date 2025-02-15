/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.layout;

import com.jgoodies.forms.layout.FormLayout;
import java.awt.Container;
import java.util.List;

public interface Size {
    public int maximumSize(Container var1, List var2, FormLayout.Measure var3, FormLayout.Measure var4, FormLayout.Measure var5);

    public boolean compressible();

    public String encode();
}

