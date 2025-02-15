/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.templates;

import java.io.Serializable;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public interface CodeTemplate
extends Cloneable,
Comparable<CodeTemplate>,
Serializable {
    public Object clone();

    public String getID();

    public void invoke(RSyntaxTextArea var1) throws BadLocationException;
}

