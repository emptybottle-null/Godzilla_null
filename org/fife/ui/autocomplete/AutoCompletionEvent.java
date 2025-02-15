/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.EventObject;
import org.fife.ui.autocomplete.AutoCompletion;

public class AutoCompletionEvent
extends EventObject {
    private Type type;

    public AutoCompletionEvent(AutoCompletion source, Type type) {
        super(source);
        this.type = type;
    }

    public AutoCompletion getAutoCompletion() {
        return (AutoCompletion)this.getSource();
    }

    public Type getEventType() {
        return this.type;
    }

    public static enum Type {
        POPUP_SHOWN,
        POPUP_HIDDEN;

    }
}

