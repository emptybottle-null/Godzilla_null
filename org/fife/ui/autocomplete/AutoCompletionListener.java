/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.EventListener;
import org.fife.ui.autocomplete.AutoCompletionEvent;

public interface AutoCompletionListener
extends EventListener {
    public void autoCompleteUpdate(AutoCompletionEvent var1);
}

