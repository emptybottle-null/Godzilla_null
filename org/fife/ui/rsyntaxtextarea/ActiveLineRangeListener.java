/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.util.EventListener;
import org.fife.ui.rsyntaxtextarea.ActiveLineRangeEvent;

public interface ActiveLineRangeListener
extends EventListener {
    public void activeLineRangeChanged(ActiveLineRangeEvent var1);
}

