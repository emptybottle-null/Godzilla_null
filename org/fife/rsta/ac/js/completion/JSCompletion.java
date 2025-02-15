/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import org.fife.rsta.ac.js.completion.JSCompletionUI;

public interface JSCompletion
extends JSCompletionUI {
    public String getLookupName();

    public String getType(boolean var1);

    public String getEnclosingClassName(boolean var1);
}

