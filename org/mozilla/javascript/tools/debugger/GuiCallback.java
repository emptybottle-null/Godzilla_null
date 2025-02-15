/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import org.mozilla.javascript.tools.debugger.Dim;

public interface GuiCallback {
    public void updateSourceText(Dim.SourceInfo var1);

    public void enterInterrupt(Dim.StackFrame var1, String var2, String var3);

    public boolean isGuiEventThread();

    public void dispatchNextGuiEvent() throws InterruptedException;
}

