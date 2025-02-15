/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.net.URL;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.parser.ExtendedHyperlinkListener;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;

public interface Parser {
    public ExtendedHyperlinkListener getHyperlinkListener();

    public URL getImageBase();

    public boolean isEnabled();

    public ParseResult parse(RSyntaxDocument var1, String var2);
}

