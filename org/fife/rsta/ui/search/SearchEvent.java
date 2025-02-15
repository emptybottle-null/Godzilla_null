/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import java.util.EventObject;
import org.fife.ui.rtextarea.SearchContext;

public class SearchEvent
extends EventObject {
    private SearchContext context;
    private Type type;

    public SearchEvent(Object source, Type type, SearchContext context) {
        super(source);
        this.type = type;
        this.context = context;
    }

    public Type getType() {
        return this.type;
    }

    public SearchContext getSearchContext() {
        return this.context;
    }

    public static enum Type {
        MARK_ALL,
        FIND,
        REPLACE,
        REPLACE_ALL;

    }
}

