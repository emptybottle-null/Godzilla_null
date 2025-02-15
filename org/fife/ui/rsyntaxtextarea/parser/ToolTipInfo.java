/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.net.URL;
import javax.swing.event.HyperlinkListener;

public class ToolTipInfo {
    private String text;
    private HyperlinkListener listener;
    private URL imageBase;

    public ToolTipInfo(String text, HyperlinkListener listener) {
        this(text, listener, null);
    }

    public ToolTipInfo(String text, HyperlinkListener l, URL imageBase) {
        this.text = text;
        this.listener = l;
        this.imageBase = imageBase;
    }

    public HyperlinkListener getHyperlinkListener() {
        return this.listener;
    }

    public URL getImageBase() {
        return this.imageBase;
    }

    public String getToolTipText() {
        return this.text;
    }
}

