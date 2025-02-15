/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import javax.swing.event.HyperlinkEvent;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DescWindowCallback;

public interface ExternalURLHandler {
    public void urlClicked(HyperlinkEvent var1, Completion var2, DescWindowCallback var3);
}

