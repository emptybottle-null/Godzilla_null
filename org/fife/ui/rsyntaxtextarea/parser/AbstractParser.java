/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.net.URL;
import org.fife.ui.rsyntaxtextarea.parser.ExtendedHyperlinkListener;
import org.fife.ui.rsyntaxtextarea.parser.Parser;

public abstract class AbstractParser
implements Parser {
    private boolean enabled;
    private ExtendedHyperlinkListener linkListener;

    protected AbstractParser() {
        this.setEnabled(true);
    }

    @Override
    public ExtendedHyperlinkListener getHyperlinkListener() {
        return this.linkListener;
    }

    @Override
    public URL getImageBase() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setHyperlinkListener(ExtendedHyperlinkListener listener) {
        this.linkListener = listener;
    }
}

