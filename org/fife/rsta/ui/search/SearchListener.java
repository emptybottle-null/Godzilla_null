/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import java.util.EventListener;
import org.fife.rsta.ui.search.SearchEvent;

public interface SearchListener
extends EventListener {
    public void searchEvent(SearchEvent var1);

    public String getSelectedText();
}

