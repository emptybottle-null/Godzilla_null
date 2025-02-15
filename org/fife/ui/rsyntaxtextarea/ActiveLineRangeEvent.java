/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.util.EventObject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class ActiveLineRangeEvent
extends EventObject {
    private int min;
    private int max;

    public ActiveLineRangeEvent(RSyntaxTextArea source, int min, int max) {
        super(source);
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    public int getMin() {
        return this.min;
    }
}

