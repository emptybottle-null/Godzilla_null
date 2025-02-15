/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Graphics;
import org.fife.ui.autocomplete.Completion;

public interface JavaSourceCompletion
extends Completion {
    public boolean equals(Object var1);

    public void rendererText(Graphics var1, int var2, int var3, boolean var4);
}

